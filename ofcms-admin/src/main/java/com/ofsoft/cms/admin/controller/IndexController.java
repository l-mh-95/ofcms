package com.ofsoft.cms.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.ofsoft.cms.admin.core.config.AdminConst;
import com.ofsoft.cms.admin.core.config.ShiroUtils;
import com.ofsoft.cms.core.annotation.Action;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import com.jfinal.core.ActionKey;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.sanyka.weixin.utils.strutil.StringUtil;

/**
 * 页面配置
 * 
 * @author OF
 * @date 2018年1月2日
 */
@Action(path = "/")
public class IndexController extends BaseController {

	public void index() {
		render("/index.html");
	}

	// @RequiresPermissions(value = "123")

	public void login() {
		render("login.html");
	}

	public void help() {
		render("help.html");
	}

	public void getList() {
		render("getList.json");
	}

	/**
	 * 公共页面跳转请求处理
	 * 
	 * @param p
	 *            返回显示页面
	 */
	@ActionKey(value = "f")
	public void f() {
		String uuid = getPara("_fsUuid");
		String mode = getPara("_mode");
		StringBuilder sb = new StringBuilder();
		if (!StringUtil.isBlank(uuid)) {
			sb.append("?").append("_fsUuid = ").append(uuid.trim())
					.append("&_mode = ").append(mode);
		}
		String p = getPara("p");
		renderHTML(p);
	}

	/**
	 * 实际的登录代码 如果登录成功，跳转至首页；登录失败，则将失败信息反馈对用户
	 * 
	 * @return
	 */
	@ActionKey(value = "/dologin")
	public void dologin() {
		String msg = "处理成功";
		String userName = getParaJson("username");
		String password = getParaJson("password");
		String rememberMe = getParaJson("rememberMe");
		password = new Sha256Hash(password).toHex();
		UsernamePasswordToken token = new UsernamePasswordToken(userName,
				password);
		if (rememberMe != null && "on".contains(rememberMe)) {
			token.setRememberMe(true);
		}
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			if (subject.isAuthenticated()) {
				// CookieUtil.setLoginnameCookie(userName, response);
				rendSuccessJson(msg);
				return;
			} else {
				msg = "账户密码不正确!";
				rendFailedJson(msg);
				return;
			}
		} catch (IncorrectCredentialsException e) {
			msg = "登录密码错误. Password for account " + token.getPrincipal()
					+ " was incorrect.";
			e.printStackTrace();
			rendFailedJson(msg);
		} catch (ExcessiveAttemptsException e) {
			msg = "登录失败次数过多,账户锁定10分钟";
			rendFailedJson(msg);
		} catch (LockedAccountException e) {
			msg = "帐号已被锁定. The account for username " + token.getPrincipal()
					+ " was locked.";
			rendFailedJson(msg);
		} catch (DisabledAccountException e) {
			msg = "帐号已被禁用. The account for username " + token.getPrincipal()
					+ " was disabled.";
			rendFailedJson(msg);
		} catch (ExpiredCredentialsException e) {
			msg = "帐号已过期. the account for username " + token.getPrincipal()
					+ "  was expired.";
			rendFailedJson(msg);
		} catch (UnknownAccountException e) {
			System.out.println(e.getMessage());
			msg = "帐号不存在. There is no user with username of "
					+ token.getPrincipal();
			rendFailedJson(msg);
		}

	}

	/**
	 * 退出
	 */
	@ActionKey(value = "logout")
	public void logout() {
		logService(ShiroUtils.getSysUser().getUserId().toString(), ShiroUtils
				.getSysUser().getUserName(), "用户退出");
		SecurityUtils.getSubject().logout();
		// CookieUtil.setLogoutCookie(response);
		ShiroUtils.getSession().removeAttribute(AdminConst.USER_MENU_SESSION);
		redirect("/login.html");
	}

	@ActionKey(value = "main")
	public void main() {
		Map<String, Object> params = getParamsMap();
		try {
			// 公告
			/*SqlPara announceSql = Db
					.getSqlPara("system.announce.query", params);
			List<Record> announce = Db.find(announceSql);
			setAttr("announce", announce);
			// 订单统计
			Record order = Db.findFirst(Db.getSql("shop.order.order_count"));
			setAttr("order", order);
			// 订单月统计
			List<Record> order_month = Db.find(Db
					.getSql("shop.order.order_month_count"));
			setAttr("order_month", monthHandler(order_month));
			// 用户人数统计
			List<Record> user = Db.find(Db.getSql("shop.user.user_count"));
			setAttr("user", monthHandler(user));*/
			render("main.html");
		} catch (Exception e) {
			e.printStackTrace();
			render("main.html");
		}
	}

	public List<String> monthHandler(List<Record> user) {
		boolean flag = false;
		List<String> str = new ArrayList<String>();
		for (int i = 1; i <=12; i++) {
			for (Record map : user) {
				if (i == map.getInt("month")) {
					str.add(map.getStr("count"));
					flag = true;
					break;
				} else {
					flag = false;
				}
			}
			if (!flag) {
				str.add("0");
			}
		}
		return str;

	}
}

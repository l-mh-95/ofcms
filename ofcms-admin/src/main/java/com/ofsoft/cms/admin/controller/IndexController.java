package com.ofsoft.cms.admin.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.plugin.activerecord.Record;
import com.ofsoft.cms.admin.controller.system.SystemUtile;
import com.ofsoft.cms.core.annotation.Action;
import com.ofsoft.cms.core.config.AdminConst;
import com.ofsoft.cms.core.config.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 页面配置
 * 
 * @author OF
 * @date 2018年1月2日
 */
@Action(path = "/")
public class IndexController extends BaseController {
	@ActionKey(value = "/admin/index")
	public void index() {

		render("/admin/index.html");
	}

	// @RequiresPermissions(value = "123")
	@ActionKey(value = "/admin/login")
	public void login() {
		render("/admin/login.html");
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
	 *            返回显示页面
	 */
	@ActionKey(value = "/admin/f")
	public void f() {
		String uuid = getPara("_fsUuid");
		String mode = getPara("_mode");
		StringBuilder sb = new StringBuilder();
		if (!StringUtils.isBlank(uuid)) {
			sb.append("?").append("_fsUuid = ").append(uuid.trim())
					.append("&_mode = ").append(mode);
		}
		String p = getPara("p");
		setAttr("result",getParamsMap());
		renderHTML(p);
	}

	/**
	 * 实际的登录代码 如果登录成功，跳转至首页；登录失败，则将失败信息反馈对用户
	 * 
	 * @return
	 */
	@ActionKey(value = "/admin/dologin")
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
				//设置默认站点
				SystemUtile.setSite(getRequest());
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
	@ActionKey(value = "/admin/logout")
	public void logout() {
		logService(ShiroUtils.getSysUser().getUserId().toString(), ShiroUtils
				.getSysUser().getUserName(), "用户退出");
		SecurityUtils.getSubject().logout();
		// CookieUtil.setLogoutCookie(response);
		ShiroUtils.getSession().removeAttribute(AdminConst.USER_MENU_SESSION);
		redirect("/admin/login.html");
	}
	@ActionKey(value = "/admin/setSite")
	public void setSite() {
		SystemUtile.setSite(getPara("site_id"));
		redirect("/admin/index.html");
	}
	@ActionKey(value = "/admin/main")
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
			render("/admin/main.html");
		} catch (Exception e) {
			e.printStackTrace();
			render("/admin/main.html");
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

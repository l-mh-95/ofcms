package com.ofsoft.cms.admin.controller.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;
import com.ofsoft.cms.admin.core.config.AdminConst;
import com.ofsoft.cms.admin.core.config.ShiroUtils;

/**
 * 系统工具类
 * 
 * @author OF
 * @date 2018年1月15日
 */
public class SystemUtile {
	public final static String DEFAULT_ADMIN = "admin";

	/**
	 * 是否是管理员
	 * 
	 * @param loginName
	 *            登录名
	 * @return true/false
	 */
	public static boolean isAdmin() {
		String loginName = ShiroUtils.getSysUser().getLoginName();
		return isAdmin(loginName);
	}

	/**
	 * 获取参数
	 * 
	 * @param dictName
	 *            字典名
	 */
	public static List<Map<String, Object>> getDict(String dictName) {
		List<Record> list = CacheKit.get(AdminConst.SYSTEM,
				AdminConst.SYSTEM_DICT);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		if (list == null) {
			initDict();
		} else {
			for (Record r : list) {
				if (r.getStr("dict_name").equalsIgnoreCase(dictName)) {
					result.add(r.getColumns());
				}
			}
		}
		return result;
	}

	/**
	 * 获取某个参数
	 * 
	 * @param paramName
	 *            参数名
	 * 
	 *            <pre>
	 * 系统启动时自动加载数据
	 * </pre>
	 */
	public static String getParam(String paramName) {
		List<Record> list = CacheKit.get(AdminConst.SYSTEM,
				AdminConst.SYSTEM_PARAM);
		if (list == null) {
			list = initParam();
		} else {
			for (Record r : list) {
				if (r.getStr("param_name").equalsIgnoreCase(paramName)) {
					return r.getStr("param_value");
				}
			}
		}
		return "";
	}

	/**
	 * 是否是管理员
	 * 
	 * @param loginName
	 *            登录名
	 * @return true/false
	 */
	public static boolean isAdmin(String loginName) {
		String admin = PropKit.use(AdminConst.ADMIN_CONFIG).get("admin_user",
				DEFAULT_ADMIN);
		String[] admiins = admin.split(",");
		for (int i = 0; i < admiins.length; i++) {
			if (admiins[i].equalsIgnoreCase(loginName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 初始化参数
	 */
	public static void init() {
		// 缓存字典
		initDict();
		// 缓存参数
		initParam();
	}

	/**
	 * 缓存字典
	 * 
	 * @return
	 */
	public static List<Record> initDict() {
		List<Record> dict = Db.find(Db.getSqlPara("system.dict.query"));
		CacheKit.put(AdminConst.SYSTEM, AdminConst.SYSTEM_DICT, dict);
		return dict;
	}

	/**
	 * 缓存参数
	 * 
	 * @return
	 */
	public static List<Record> initParam() {
		List<Record> params = Db.find(Db.getSqlPara("system.param.query"));
		CacheKit.put(AdminConst.SYSTEM, AdminConst.SYSTEM_PARAM, params);
		return params;
	}
}

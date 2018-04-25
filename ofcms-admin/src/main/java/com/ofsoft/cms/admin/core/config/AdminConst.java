package com.ofsoft.cms.admin.core.config;

import com.jfinal.kit.PropKit;

public final class AdminConst {

	/**
	 * 正式环境
	 */
	public static final String WUXIAN_DOMIAN = PropKit.get("wirless_domain");

	/** 在线用户缓存  **/
	public static final String USER_ONLINE_KEY = "shiro-activeSessionCache";

	/** 把User的数据放到session中 **/
	public static final String USER_IN_SESSION = "syk_user";

	/** 就诊人信息存放到session中 **/
	public static final String USER_MENU_SESSION = "syk_user_menu";

	/** 就诊人收货地址放到session中 **/
	public static final String USER_ADDRESS_SESSION = "syk_user_address";

	/** 微信公众号信息存放到session中 **/
	public static final String USER_HOSP_SESSION = "syk_user_hosp";

	public final static String NET_ERROR_MSG = "请求接口失败，请检查网络，或者刷新重连";

	public final static String ADMIN_CONFIG = "conf/admin.properties";
	public final static String WEIXIN_CONFIG = "conf/weixin.sdk.properties";
	public final static String STRING_CONFIG = "classpath:conf/applicationContext.xml";
	public final static String ERROR_500 = "/comn/500.html";
	/** 业务缓存名 */
	public final static String API = "api";
	/** 医院缓存key */
	public final static String API_HOSP = "hosp";
	/** 系统缓存名 */
	public final static String SYSTEM = "system";
	/** 字典缓存key */
	public final static String SYSTEM_DICT = "dict";
	/** 参数缓存key */
	public final static String SYSTEM_PARAM = "param";
}

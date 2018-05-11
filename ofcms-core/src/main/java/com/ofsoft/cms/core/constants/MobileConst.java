package com.ofsoft.cms.core.constants;

import com.jfinal.kit.PropKit;

public final class MobileConst {

	/**
	 * 正式环境
	 */
	public static final String WUXIAN_DOMIAN = PropKit.get("wirless_domain");

	/** 接口域名key **/
	public static final String DOMAIN_KEY = "domain_url";

	/** 把User的数据放到session中 **/
	public static final String USER_IN_SESSION = "syk_user";

	/** 就诊人信息存放到session中 **/
	public static final String USER_FRIENDS_SESSION = "syk_user_friends";
	
	/** 就诊人收货地址放到session中**/
	public static final String USER_ADDRESS_SESSION = "syk_user_address";
	
	/** 微信公众号信息存放到session中 **/
	public static final String USER_HOSP_SESSION = "syk_user_hosp";

	public final static String NET_ERROR_MSG = "请求接口失败，请检查网络，或者刷新重连";

	public final static String MOBILE_CONFIG = "/conf/front.properties";
	public final static String WEIXIN_CONFIG = "/conf/weixin.sdk.properties";
	public final static String STRING_CONFIG = "classpath:conf/applicationContext.xml";
	public final static String ERROR_500 = "/comn/500.html";
	
	public final static String ADDRESS_IS_DEF = "1";

}

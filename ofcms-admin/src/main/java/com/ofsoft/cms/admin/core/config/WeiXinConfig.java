package com.ofsoft.cms.admin.core.config;

import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.ApiConfig;

/**
 * 微信公共配置
 * 
 * @author OF
 * @date 2017年12月4日
 */
public class WeiXinConfig {
	/**是否是开发模式*/
	private static boolean devMode = true;
	//是否是开启支付结果验证
	private static boolean checkSing = true;
	
	public static ApiConfig ac = new ApiConfig();
	public static String appid = PropKit.get("wx_app_id");
	public static String secret = PropKit.get("wx_app_secret");
	public static String mch_id = PropKit.get("wx_mch_id");
	public static String notify_url = PropKit.get("wx_notify_url");
	public static String secret_key = PropKit.get("wx_secret_key");
	public static  String ip = PropKit.get("wx_mch_ip");
	public static String body = PropKit.get("wx_mch_body");
	public static String totalAmount ="0.01";
	public static ApiConfig getApiConfig() {
		if (ac == null) {
			ac = new ApiConfig();
		}
		// 配置微信 API 相关常量
		ac.setToken(PropKit.get("wx_app_token"));
		ac.setAppId(PropKit.get("wx_app_id"));
		ac.setAppSecret(PropKit.get("wx_app_secret"));
		ac.setEncryptMessage(PropKit.getBoolean("encryptMessage", false));
		ac.setEncodingAesKey(PropKit.get("encodingAesKey",
				"setting it in config file"));
		return ac;
	}
	public static boolean isDevMode() {
		return devMode;
	}
	public static void setDevMode(boolean devMode) {
		WeiXinConfig.devMode = devMode;
	}
	public static boolean isCheckSing() {
		return checkSing;
	}
	public static void setCheckSing(boolean checkSing) {
		WeiXinConfig.checkSing = checkSing;
	}
 
}

package com.ofsoft.cms.core.config;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.jfinal.kit.HashKit;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.PaymentApi;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.ofsoft.cms.core.utils.Tools;

public class WechatPayKit {

	/**
	 * 初始化微信支付公共请求参数
	 * 
	 * @return 返回 公众号、商户号、 随机字符串
	 */
	public static Map<String, String> initPayParams() {
		Map<String, String> result = new HashMap<String, String>();
		// 微信公众号
		result.put("appid", WeiXinConfig.appid);
		// 微信商户号mch_id
		result.put("mch_id", WeiXinConfig.mch_id);
		// 设备号
		result.put("device_info", "WEB");
		// 后台通知地址
		result.put("notify_url", WeiXinConfig.notify_url);
		// 交易类型
		result.put("trade_type", "JSAPI");
		return result;
	}

	public static Map<String, String> request(Map<String, String> params) {
		params.put("nonce_str", getNonceStr());
		String sign = PaymentKit.createSign(params, WeiXinConfig.secret_key);
		params.put("sign", sign);
		String xmlStr = null;
		// 开发模式打印日志
		if (WeiXinConfig.isDevMode()) {
			System.out.println("wxpay reques :" + params);
			xmlStr = PaymentApi.pushOrder(params);
			System.out.println("wxpay repones :" + xmlStr);
		} else {
			xmlStr = PaymentApi.pushOrder(params);
		}
		Map<String, String> result = PaymentKit.xmlToMap(xmlStr);
		// 是否检查签名
		/*if (WeiXinConfig.isCheckSing()) {
			if ("SUCCESS".equals(result.get("result_code"))) {
				if (!PaymentKit.verifyNotify(result, WeiXinConfig.secret_key)) {
					System.out
							.println("wxpay check sing :return params sing FAIL ");
					result.put("return_msg", "return params sing FAIL");
					result.put("result_code", "FAIL");
				}
				if (WeiXinConfig.isDevMode()) {
					System.out.println("wxpay check sing :success");
				}
			}
		}*/
		return result;
	}

	public static Map<?, ?> WechatPay(String orderNo, String totalFee,
			String openId) {
		return WechatPay(orderNo, totalFee, openId, WeiXinConfig.ip, "支付");
	}

	public static Map<?, ?> WechatPay(String orderNo, String totalFee,
			String openId, String payType) {
		return WechatPay(orderNo, totalFee, openId, WeiXinConfig.ip, payType);
	}

	public static Map<?, ?> WechatPay(String orderNo, String totalFee,
			String openId, String ip, String payType) {
		Map<String, String> params = WechatPayKit.initPayParams();
		// 商户订单号，8-40位数字字母
		params.put("out_trade_no", orderNo);
		// 用户编号
		params.put("openid", openId);
		// 终端IP
		params.put("spbill_create_ip", ip);
		// 订单总金额，单位分
		params.put(
				"total_fee",
				Tools.int2Str(Tools.getBigDecimal(totalFee)
						.multiply(new BigDecimal(100)).intValue()));
		// 商品描述
		params.put("body", WeiXinConfig.body + businessType(payType));
		// 附加数据
		params.put("attach", payType);
		return WechatPayKit.request(params);
	}

	public enum payType {
		RED, GREEN, BLANK, YELLOW
	}

	public enum TradeType {
		JSAPI, NATIVE, APP, WAP, MWEB
	}

	/**
	 * 获取微信支付时间戳
	 * 
	 * @return
	 */
	public static String getNonceStr() {
		return new StringBuilder().append(System.currentTimeMillis() / 1000)
				.append("").toString();
	}

	/**
	 * 网页js签名
	 * 
	 * @param wxMap
	 * @return 签名
	 */
	public static String getJsSdkSign(Map<String, String> wxMap) {
		// 加密获取signature
		StringBuilder wxBaseString = new StringBuilder();
		for (Entry<String, String> param : wxMap.entrySet()) {
			wxBaseString.append(param.getKey()).append("=")
					.append(param.getValue()).append("&");
		}
		String wxSignString = wxBaseString.substring(0,
				wxBaseString.length() - 1);
		// signature
		return HashKit.sha1(wxSignString);
	}

	/**
	 * 微信支付订单签名
	 * 
	 * @param prepayId
	 *            订单号
	 * @return map
	 */
	public static Map<String, String> payOrderSign(String prepayId) {
		Map<String, String> packageParams = new HashMap<String, String>();
		packageParams.put("appId", WeiXinConfig.appid);
		packageParams.put("timeStamp", WechatPayKit.getNonceStr());
		packageParams.put("nonceStr", StrKit.getRandomUUID());
		packageParams.put("package", "prepay_id=" + prepayId);
		packageParams.put("signType", "MD5");
		String packageSign = PaymentKit.createSign(packageParams,
				WeiXinConfig.secret_key);
		packageParams.put("paySign", packageSign);
		return packageParams;
	}

	/**
	 * 支付类型转换
	 * 
	 * @param payType
	 * @return String
	 */
	public static String businessType(String payType) {
		String result = null;
		switch (payType) {
		case "1":
			result = "挂号";
			break;
		case "2":
			result = "自助缴费";
			break;
		case "3":
			result = "出院结算";
			break;
			//自助入院
		case "4":
			result = "押金";
			break;
		case "5":
			result = "日用用品";
			break;
		case "6":
			result = "营养套餐";
			break;
		case "7":
			result = "缴费";
			break;
		default:
			result = "支付";
			break;
		}
		return result;
	}
}

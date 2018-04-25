package com.ofsoft.cms.admin.core.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局返回码
 * 
 * @author OF
 * @date 2017年12月6日
 */
public class ErrorCode {
	private static final Map<String, String> errCodeToErrMsg = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("-200", "系统繁忙");
			put("200", "请求成功");
			put("3333", "网络超时!");
			put("9999", "处理失败请稍后重试!");
			put("1001", "查询日期不能为空!");
			put("1002", "报告编号不能为空!");
			put("1003", "住院编号不能为空!");
			put("1004", "分类编号不能为空!");
			put("1005", "该就诊人已存在!");
			put("1006", "支付业务类型不能为空!");
			//接口错误码
			put("2001", "open_id 不能为空!");
			put("2002", "医院编号不能为空!");
			put("2003", "就诊人编号不能为空!");
			put("2004", "身份证号不能为空!");
			put("2005", "该就诊人已存在!");
			put("2006", "小程序code 不能为空!");
			
			put("4001", "获取订单失败,请稍后重试!");
			put("4002", "获取票据失败,请稍后重试!");
			put("4003", "支付签名失败,请稍后重试!");
			
			
		}
	};

	/**
	 * 通过返回码获取返回信息
	 * 
	 * @param errCode
	 *            错误码
	 * @return {String}
	 */
	public static String get(String errCode) {
		String result = errCodeToErrMsg.get(errCode);
		return result != null ? result : "未知返回码：" + errCode;
	}
	 
}

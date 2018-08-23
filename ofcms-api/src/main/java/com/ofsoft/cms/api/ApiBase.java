package com.ofsoft.cms.api;

import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.PropKit;
import com.jfinal.render.JsonRender;
import com.jfinal.weixin.sdk.utils.JsonUtils;
import com.ofsoft.cms.core.annotation.Action;
import com.ofsoft.cms.core.spring.IocInterceptor;
import com.ofsoft.cms.core.utils.ResultUtil;
import com.ofsoft.cms.core.utils.okhttp.OkHttp;

import java.util.Map;

/**
 * 对外提供接口
 * 
 * @author OF
 * @date 2017年12月14日
 */
@Action(path = "/api")
@Before(IocInterceptor.class)
// @Before({ IocInterceptor.class, RestfulInterceptor.class })
public abstract class ApiBase extends Controller {
	protected void rendSuccessJson(Object data) {
		rendJson(ResultUtil.genSuccessResult(data));
	}

	protected void rendSuccessJson(Object data, Integer TotalPage,
			Integer PageNumber) {
		Map<String, Object> map = ResultUtil.genSuccessResult();
		map.put("data", data);
		map.put("total_page", TotalPage);
		map.put("page_size", PageNumber);
		rendJson(map);
	}

	protected void rendSuccessJson() {
		rendJson(ResultUtil.genSuccessResult());
	}

	protected void rendFailedJson() {
		rendJson(ResultUtil.genFailedResult("处理失败,请稍后重试!"));
	}

	protected void rendFailedJson(final String msg) {
		rendJson(ResultUtil.genFailedResult(msg));
	}

	protected void rendFailedJson(final int code, final String msg) {
		rendJson(ResultUtil.genFailedResult(code, msg));
	}

	protected void rendFailedJson(final String code, final String msg) {
		rendJson(ResultUtil.genFailedResult(code, msg));
	}

	protected void rendJson(Object json) {
		String agent = getRequest().getHeader("User-Agent");
		if (agent.contains("MSIE"))
			this.render(new JsonRender(json).forIE());
		else {
			this.render(new JsonRender(json));
		}
	}

	/**
	 * 获取json参数
	 * 
	 * @return json
	 */
	public String getParaJson() {
		return HttpKit.readData(getRequest());
	}

	/**
	 * 获取json参数
	 * 
	 * @param name
	 *            参数名
	 * @return 参数值
	 */
	public String getParaJson(String name) {
		return (String) getParaJsonMap().get(name);
	}

	@SuppressWarnings("rawtypes")
	public Map getParaJsonMap() {
		return JsonUtils.parse(getParaJson(), Map.class);
	}

	private static String jsCode2sessionUrl = "https://api.weixin.qq.com/sns/jscode2session";

	/**
	 * 获取sessionKey
	 * 
	 * @param jsCode
	 *            登录时获取的 code
	 * @return ApiResult
	 */
	public Map<String, Object> getSessionKey(String jsCode) {
		String url = jsCode2sessionUrl + "?appid="
				+ PropKit.get("wxa_app_id") + "&secret="     
				+ PropKit.get("wxa_app_secret") + "&js_code=" + jsCode
				+ "&grant_type=authorization_code";
		// 构造url
		String json = OkHttp.okHttpGet(url);
		return JSON.parseObject(json);
	}

}

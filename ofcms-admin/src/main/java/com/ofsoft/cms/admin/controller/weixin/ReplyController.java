package com.ofsoft.cms.admin.controller.weixin;

import java.util.List;

import com.ofsoft.cms.admin.controller.BaseController;
import com.ofsoft.cms.admin.controller.system.SystemUtile;
import com.ofsoft.cms.admin.core.config.ErrorCode;
import com.ofsoft.cms.core.annotation.Action;
import com.ofsoft.cms.core.utils.okhttp.OkHttp3;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * 微信默认回复
 * 
 * @author OF
 * @date 2018年3月15日
 */
@Action(path = "/weixin/reply")
public class ReplyController extends BaseController {
	public final static String SUCESS_CODE = "200";
	public final static String API_URL = "/api/v1/config/init.json";

	/**
	 * 首页
	 */
	public void index() {
		List<Record> list = Db.find(Db.getSqlPara("system.param.query_weixin"));
		setAttr("list", list);
		render("/weixin/reply/index.html");
	}

	public void config() {
		try {
			String result = OkHttp3.okHttpGet(
					SystemUtile.getParam("mobile_api_href") + API_URL, null);
			JSONObject data = JSON.parseObject(result);
			if (StringUtils.isBlank(result)) {
				rendFailedJson(ErrorCode.get("9999"));
				return;
			}
			if (SUCESS_CODE.equals(data.getString("code"))) {
				rendSuccessJson();
			} else {
				rendFailedJson(ErrorCode.get("9999"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			rendFailedJson(ErrorCode.get("9999"));
		}

	}
}

package com.ofsoft.cms.api.user;

import com.ofsoft.cms.core.annotation.Action;
import com.ofsoft.cms.api.ApiBase;


/**
 * 就诊人接口
 * 
 * @author OF
 * @date 2017年12月14日
 */
@Action(path = "/visit")
public class VisitApi extends ApiBase {

	/**
	 * 获取就诊人列表
	 */
	public void get() {
		String method = getRequest().getMethod();
		if (!"GET".equals(method)) {
			rendFailedJson("not is get method");
			return;
		}
		try {
//			List<UserFriendsInfo> list = userFriendsInfoService.queryList(
//					openId, hospNo);
//			rendSuccessJson(list);
		} catch (Exception e) {
			e.printStackTrace();
			rendFailedJson();
		}
	}
}

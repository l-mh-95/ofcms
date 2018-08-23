package com.ofsoft.cms.api.v2;

import com.ofsoft.cms.api.ApiBase;
import com.ofsoft.cms.core.annotation.Action;


/**
 * 就诊人接口
 * 
 * @author OF
 * @date 2017年12月14日
 */
@Action(path = "/user")
public class UserApi extends ApiBase {

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
			rendSuccessJson("测试");
		} catch (Exception e) {
			e.printStackTrace();
			rendFailedJson();
		}
	}
}

package com.ofsoft.cms.admin.controller.system;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.ofsoft.cms.admin.controller.BaseController;
import com.ofsoft.cms.core.annotation.Action;

/**
 * 系统设置
 * 
 * @author OF
 * @date 2018年1月8日
 */
@Action(path = "/system")
public class SystemController extends BaseController {

	public void index() {
		render("/help.html");
	}

	/**
	 * 公共查询方法
	 */
	public void query() {
		Page<Record> page = Db.paginate(getPageNum(), getPageSize(),
				"select * ", " from syk_sys_user");
		rendSuccessJson(page.getList(), page.getTotalRow(),
				page.getPageNumber());
	}
}

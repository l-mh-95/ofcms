package com.ofsoft.cms.admin.controller.system;

import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.ofsoft.cms.admin.controller.BaseController;
import com.ofsoft.cms.admin.core.config.ErrorCode;
import com.ofsoft.cms.admin.core.uitle.GenUtils;
import com.ofsoft.cms.core.annotation.Action;

/**
 * 系统代码生成
 * 
 * @author OF
 * @date 2018年1月22日
 */
@Action(path = "/system/generate", viewPath = "system/generate/")
public class SystemGenerateController extends BaseController {

	/**
	 * 公共查询方法
	 */
	public void query() {
		Page<Record> page = Db.paginate(getPageNum(), getPageSize(),
				"select * ", " from syk_sys_user");
		rendSuccessJson(page.getList(), page.getTotalRow(),
				page.getPageNumber());
	}

	/**
	 * 生成
	 */
	public void code() {
		try {
			Map<String, Object> params = getParamsMap();
			String tableName = getPara("table_name");
			String moduleName = getPara("module_name");
			String fuctionName = getPara("fuction_name");
			SqlPara sql = Db.getSqlPara("system.generate.column", params);
			List<Record> columnList = Db.find(sql);
			GenUtils.createSql(tableName, moduleName, fuctionName, columnList);
			rendSuccessJson();
		} catch (Exception e) {
			e.printStackTrace();
			rendFailedJson(ErrorCode.get("9999"));
		}
	}

	/**
	 * 创建表
	 */
	public void create() {
		try {
			String sql = getPara("sql");
			Db.update(sql);
			rendSuccessJson();
		} catch (Exception e) {
			e.printStackTrace();
			rendFailedJson(ErrorCode.get("9999"), e.getMessage());
		}
	}
}

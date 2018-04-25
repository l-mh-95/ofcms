package com.ofsoft.cms.admin.service;

import com.jfinal.plugin.activerecord.SqlPara;
import com.sanyka.weixin.utils.strutil.StringUtil;

public abstract class BaseService {
	/**
	 * 动态增加order by参数
	 */
	public void setPageOrderByParams(SqlPara sql, String field, String sort) {
		if (!StringUtil.isEmpty(field) && !StringUtil.isEmpty(sort)) {
			sql.setSql(sql.getSql().replace("order_sort", sort));
			sql.setSql(sql.getSql().replace("order_field", field));
		}
	}
}

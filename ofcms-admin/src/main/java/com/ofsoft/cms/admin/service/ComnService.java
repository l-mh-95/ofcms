package com.ofsoft.cms.admin.service;

import java.util.List;
import java.util.Map;

import com.ofsoft.cms.model.SysUser;
import org.springframework.stereotype.Service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;

/**
 * 公共service
 * 
 * @author OF
 * @date 2018年1月25日
 */
@Service("comnService")
public class ComnService extends BaseService {
	/**
	 * 公共查询
	 */
	public Page<Record> query(Map<String, Object> params, Integer pageNum,
			Integer pageSize, String field, String sort) {
		SqlPara sql = Db.getSqlPara(params.get("sqlid").toString(), params);
		setPageOrderByParams(sql, field, sort);
		Page<Record> page = Db.paginate(pageNum, pageSize, sql);
		return page;
	}

	/**
	 * 分布查询数据
	 * 
	 * @return
	 */
	public List<SysUser> queryCache() {
		// Db.paginateByCache(cacheName, key, pageNumber, pageSize, select,
		// sqlExceptSelect)
		Db.findByCache("", "", "");
		return SysUser.dao.find("select * from syk_sys_user");
	}
}

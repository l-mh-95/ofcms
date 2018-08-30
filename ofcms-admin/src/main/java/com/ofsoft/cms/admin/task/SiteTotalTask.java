package com.ofsoft.cms.admin.task;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.ofsoft.cms.admin.controller.system.SystemUtile;
import com.ofsoft.cms.core.utils.CalendarUtil;

import java.util.List;

public class SiteTotalTask extends BaseTask {

	@Override
	public void task( ) {
		List<Record> sites =  SystemUtile.getSitCache();
		for(Record site : sites) {
			Record record = new Record();
			record.set("site_id", site.get("site_id"));
			record.set("count_date", CalendarUtil.getNowTime("yyyy-MM-dd"));
			//判断是否存在
			Record isStatus = Db.findFirst(Db.getSqlPara("cms.count.query",record));
			if(isStatus != null){
				continue;
			}
			record.set("count_date", CalendarUtil.getDayOffsetDate("yyyy-MM-dd",-1));
			//Db.update(Db.getSqlPara("cms.count.update_total_count", record));
			Record count = Db.findFirst(Db.getSqlPara("cms.count.query",record));
			if(count == null){
				continue;
			}
			Db.update(Db.getSqlPara("cms.count.save_count", count));
		}
	}
}

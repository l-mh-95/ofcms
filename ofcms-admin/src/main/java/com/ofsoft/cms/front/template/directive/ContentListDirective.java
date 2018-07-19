package com.ofsoft.cms.front.template.directive;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.ofsoft.cms.front.template.freemarker.TagBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 内容列表标签
 * Created by OF on 2018/5/12.
 */
public class ContentListDirective extends TagBase {
    public static final String sqlid = "cms.content.list";
    public static final String sqlids = "cms.content.content_field";

    @Override
    public void onRender() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("site_id", getParam("site_id"));
        params.put("column_id", getParam("column_id"));
        List<Record> list = Db.find(Db.getSqlPara(sqlid, params));
        for (Record record : list) {
            params.put("content_id", record.get("content_id"));
            List<Record> sub = Db.find(Db.getSqlPara(sqlids, params));
            for (int i = 0; i < sub.size(); i++) {
                record.set(sub.get(i).getStr("name"), sub.get(i).getStr("value"));
            }
        }
        setVariable("content_list", list);
        renderBody();
    }
}

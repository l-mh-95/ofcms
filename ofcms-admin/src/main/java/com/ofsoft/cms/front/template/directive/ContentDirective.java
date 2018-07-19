package com.ofsoft.cms.front.template.directive;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.ofsoft.cms.front.template.freemarker.TagBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 内容标签
 * Created by OF on 2018/5/12.
 */
public class ContentDirective extends TagBase {
    public static final String sqlid = "cms.content.content_field";

    @Override
    public void onRender() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("site_id", getParam("site_id"));
        params.put("content_id", getParam("content_id"));
        List<Record> list = Db.find(Db.getSqlPara(sqlid, params));
        for (int i = 0; i < list.size(); i++) {
            params.put(list.get(i).getStr("name"), list.get(i).getStr("value"));
        }
        setVariable("content", params);
        renderBody();
    }
}

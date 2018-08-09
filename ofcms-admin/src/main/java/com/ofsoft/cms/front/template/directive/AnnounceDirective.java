package com.ofsoft.cms.front.template.directive;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.ofsoft.cms.front.template.freemarker.TagBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公告标签
 * Created by OF on 2018/8/9.
 */
public class AnnounceDirective extends TagBase {
    public static final String sqlid = "cms.announce.front_query";
    public static   int limit = 5;
    public static final int pageNum = 1;

    @Override
    public void onRender() {
        Map<String, Object> parmas = new HashMap<String, Object>();
        parmas.put("site_id", getParam("site_id"));
        //显示记录条数默认为空显示5条
        if (getParam("limit") == null) {
            parmas.put("limit", limit);
        } else {
            limit  = getParamToInt("limit");
        }
        List<Record> list = Db.find(Db.getSqlPara(sqlid, parmas));
        Page<Record> page = Db.paginate(pageNum, limit, Db.getSqlPara(sqlid, parmas));
        setVariable("announce", page.getList());
        renderBody();
    }
}

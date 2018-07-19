package com.ofsoft.cms.front.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.plugin.activerecord.Record;
import com.ofsoft.cms.core.annotation.Action;
import com.ofsoft.cms.core.config.FrontConst;
import com.ofsoft.cms.core.uitle.SiteUtile;

import java.util.Map;

/**
 * 页面配置
 *
 * @author OF
 * @date 2018年1月2日
 */
@Action()
public class IndexController extends BaseController {
    public void test() {
        render("/front/index.html");
    }

    /**
     * 首页页面
     */
    @ActionKey(value = "index")
    public void front() {
        render(FrontConst.TEMPLATE_PATE + SiteUtile.getTemplatePath() + "/index.html");
    }

    /**
     * 首页面配置
     */
    @ActionKey(value = "/")
    public void index() {
        render(FrontConst.TEMPLATE_PATE + SiteUtile.getTemplatePath() + "/index.html");
    }

    /**
     * 列表页面
     */
    @ActionKey(value = "/list")
    public void list() {
        render(FrontConst.TEMPLATE_PATE + SiteUtile.getTemplatePath() + "/list.html");
    }

    /**
     * 内容页面
     */
    @ActionKey(value = "/content")
    public void content() {
        String p = getRequest().getRequestURI();
        p = p.replace(getRequest().getContextPath(), "").replace("/content", "");
        render(FrontConst.TEMPLATE_PATE + SiteUtile.getTemplatePath() + p);
    }

    /**
     * 栏目页面
     */
    @ActionKey(value = "/column")
    public void column() {
        Map params = getParamsMap();
        String p = getRequest().getRequestURI();
        p = p.replace(getRequest().getContextPath(), "").replace("/column", "");
        String columnEnglish = p.substring(p.lastIndexOf("/")+1, p.lastIndexOf(".html"));
        //获取当前栏目
        params.put("site_id", SiteUtile.getSiteId());
        params.put("column_english", columnEnglish);
        Record record = SiteUtile.getColumn(params);
        setAttr("columns", record);
        setAttr("params", params);
        String templatePath = record.getStr("template_path");
        if(templatePath == null ) {
            templatePath = "";
        }
        render(FrontConst.TEMPLATE_PATE + SiteUtile.getTemplatePath() + templatePath + p);
    }

    /**
     * 普通页面
     */
    @ActionKey(value = "/page")
    public void news() {
        String p = getRequest().getRequestURI();
        p = p.replace(getRequest().getContextPath(), "").replace("/page", "");
        render(FrontConst.TEMPLATE_PATE + SiteUtile.getTemplatePath() + p);
    }
}

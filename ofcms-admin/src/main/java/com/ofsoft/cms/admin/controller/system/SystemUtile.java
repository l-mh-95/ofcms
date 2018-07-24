package com.ofsoft.cms.admin.controller.system;

import com.jfinal.core.JFinal;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.render.FreeMarkerRender;
import com.ofsoft.cms.admin.domain.TreeGird;
import com.ofsoft.cms.core.config.AdminConst;
import com.ofsoft.cms.core.config.ShiroUtils;
import com.ofsoft.cms.core.plugin.shiro.freemarker.ShiroTags;
import com.ofsoft.cms.core.utils.Tools;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统工具类
 *
 * @author OF
 * @date 2018年1月15日
 */
public class SystemUtile {
    public final static String DEFAULT_ADMIN = "admin";
    public final static String COLUMN_BASE = "/column/";
    public final static String FRONT_SUFFIX = ".html";

    /**
     * 是否是管理员
     *
     * @return true/false
     */
    public static boolean isAdmin() {
        String loginName = ShiroUtils.getSysUser().getLoginName();
        return isAdmin(loginName);
    }

    /**
     * 获取参数
     *
     * @param dictName 字典名
     */
    public static List<Map<String, Object>> getDictGroup(String dictName) {
        List<Record> list = getCache(AdminConst.SYSTEM, AdminConst.SYSTEM_DICT);
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if (list == null) {
            SystemUtile.initDict();
            return null;
        }
        for (Record r : list) {
            if (r.getStr("dict_group").equalsIgnoreCase(dictName)) {
                result.add(r.getColumns());
            }
        }
        return result;
    }

    /**
     * 获取参数
     *
     * @param dictName 字典名
     */
    public static String getDictToData(String group, String dictName) {
        List<Map<String, Object>> list = getDictGroup(group);
        if (list == null) {
            SystemUtile.initDict();
            return "";
        }
        for (Map<String, Object> r : list) {
            if (r.get("dict_value").toString().equalsIgnoreCase(dictName)) {
                return r.get("dict_name").toString();
            }
        }
        return "";
    }

    /**
     * 获取参数
     *
     * @param dictName 字典名
     */
    public static String getDict(String dictName) {
        List<Record> list = getCache(AdminConst.SYSTEM, AdminConst.SYSTEM_DICT);
        if (list == null) {
            SystemUtile.initDict();
            return "";
        }
        for (Record r : list) {
            if (r.getStr("dict_name").equalsIgnoreCase(dictName)) {
                return r.getStr("dict_value");
            }
        }
        return null;
    }

    /**
     * 获取所用字典
     *
     * @return
     */
    public static List<Record> getCache(String cacheName, Object key) {
        List<Record> list = CacheKit.get(cacheName, key);
        return list;
    }

    /**
     * 获取某个参数
     *
     * @param paramName 参数名
     *                  <p>
     *                  <pre>
     *                                                                                                                                                                                                                              系统启动时自动加载数据
     *                                                                                                                                                                                                                              </pre>
     */
    public static String getParam(String paramName) {
        List<Record> list = getCache(AdminConst.SYSTEM, AdminConst.SYSTEM_PARAM);
        if (list == null) {
            SystemUtile.initParam();
            return "";
        }
        for (Record r : list) {
            if (r.getStr("param_name").equalsIgnoreCase(paramName)) {
                return r.getStr("param_value");
            }
        }
        return "";
    }

    /**
     * 获取某个参数
     *
     * @param groupName 参数名
     */
    public static Map<String, Object> getParamGroup(String groupName) {
        List<Record> list = getCache(AdminConst.SYSTEM, AdminConst.SYSTEM_PARAM);
        if (list == null) {
            SystemUtile.initParam();
            return null;
        }
        Map<String, Object> data = new HashMap<String, Object>();
        for (Record r : list) {
            if (r.getStr("param_group").equalsIgnoreCase(groupName)) {
                data.put(r.getStr("param_name"), r.getStr("param_value"));
            }
        }
        return data;
    }

    /**
     * 是否是管理员
     *
     * @param loginName 登录名
     * @return true/false
     */
    public static boolean isAdmin(String loginName) {
        String admin = PropKit.use(AdminConst.ADMIN_CONFIG).get("admin_user",
                DEFAULT_ADMIN);
        String[] admiins = admin.split(",");
        for (int i = 0; i < admiins.length; i++) {
            if (admiins[i].equalsIgnoreCase(loginName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 初始化参数
     */
    public static void init() {
        // 缓存字典
        initDict();
        // 缓存参数
        initParam();
        // 缓存站点
        initSite();
    }

    /**
     * 缓存字典
     *
     * @return
     */
    public static List<Record> initDict() {
        List<Record> dict = Db.find(Db.getSqlPara("system.dict.query"));
        CacheKit.put(AdminConst.SYSTEM, AdminConst.SYSTEM_DICT, dict);
        return dict;
    }

    /**
     * 缓存参数
     *
     * @return
     */
    public static List<Record> initParam() {
        List<Record> params = Db.find(Db.getSqlPara("system.param.query"));
        CacheKit.put(AdminConst.SYSTEM, AdminConst.SYSTEM_PARAM, params);
        //全局参数初始化
        initParamCache();
        return params;
    }

    /**
     * 全局参数初始化
     */
    public static void initParamCache() {
        Configuration cf = FreeMarkerRender.getConfiguration();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("webroot", JFinal.me().getContextPath());
        map.put("reroot", JFinal.me().getContextPath() +  "/static" );
        map.put("http_image_url", SystemUtile.getParam("http_image_url"));
        map.put("system", SystemUtile.getParamGroup("system"));
        try {
            cf.setSharedVaribles(map);
            cf.setSharedVariable("tools", new Tools());
            cf.setSharedVariable("system_util", new SystemUtile());
            cf.setSharedVariable("shiro", new ShiroTags());
        } catch (TemplateModelException e) {
            e.printStackTrace();
        }
    }

    /**
     * 站点默认设置
     */
    public static void setSite(String siteId) {
        List<Record> records = getSitCache();
        if (records != null) {
            for (Record record : records) {
                if (!StringUtils.isBlank(siteId)) {
                    if (siteId.equals(record.getStr("site_id"))) {
                        ShiroUtils.setSessionAttribute(AdminConst.SITE_SESSION, record);
                    }
                } else if (AdminConst.DEF.equals(record.getStr("is_master"))) {
                    ShiroUtils.setSessionAttribute(AdminConst.SITE_SESSION, record);
                }
            }
        }
    }

    /**
     * 获取站点列表
     */
    public static List<Record> getSitCache() {
        List<Record> records = CacheKit.get(AdminConst.SYSTEM, AdminConst.SYSTEM_SITE);
        if (records == null) {
            records = initSite();
        }
        return records;
    }

    /**
     * 缓存站点
     *
     * @return
     */
    public static List<Record> initSite() {
        List<Record> records = Db.find(Db.getSqlPara("cms.site.query"));
        CacheKit.put(AdminConst.SYSTEM, AdminConst.SYSTEM_SITE, records);
        return records;
    }

    public static Record getDefualSitCache() {
        List<Record> records = getSitCache();
        if (records != null) {
            for (Record record : records) {
                if (AdminConst.DEF.equals(record.getStr("is_master"))) {
                    return record;
                }
            }
        }
        return null;
    }

    /**
     * 获取当前站点编号
     *
     * @return
     */
    public static String getSiteId() {
        return getSite().getStr("site_id");
    }

    /**
     * 获取当前站点模板路径
     *
     * @return
     */
    public static String getSiteTemplatePathName() {
        return getSite().getStr("template_path");
    }

    /**
     * 获取当前站点模板目录
     *
     * @return
     */
    public static String getSiteTemplatePath() {
        return getFrontTemplatePath() + getSite().getStr("template_path");
    }

    public static String getFrontTemplatePath() {
        return PathKit.getWebRootPath() + "/" + PropKit.use(AdminConst.ADMIN_CONFIG).get("template.loader_path") + "/front/";
    }

    /**
     * 获取当前站点
     *
     * @return
     */
    public static Record getSite() {
        return (Record) ShiroUtils.getSessionAttribute(AdminConst.SITE_SESSION);
    }

    /**
     * 站点默认设置
     */
    public static void setSite(HttpServletRequest request) {
        String serverName = request.getServerName();
        List<Record> list = getSitCache();
        Record site = null;
        for (Record record : list) {
            if (serverName.equals(record.getStr("domain_name"))) {
                site = record;
            }
        }
        //获取默认主网站
        if (site == null) {
            site = getDefualSitCache();
        }
        if (site != null) {
            ShiroUtils.setSessionAttribute(AdminConst.SITE_SESSION, site);
        }
    }

    public static String getSiteTemplateResourcePath() {
        return PathKit.getWebRootPath() + "/resource/" + getSite().getStr("template_path");
    }

    public static List<TreeGird> tree(List<Record> list, String parent) {
        List<TreeGird> result = new ArrayList<TreeGird>();
        for (Record r : list) {
            if (parent.equals(r.getStr("up_column_id"))) {
                List<TreeGird> sub = tree(list, (r.getStr("column_id")));
                result.add(new TreeGird(r.getStr("column_id"), r.getStr("column_name"), COLUMN_BASE + r.getStr("column_english") + FRONT_SUFFIX, r.getStr("template_path"), r.getStr("content_url"), sub));
            }
        }
        return result;
    }

    public static List<TreeGird> ColumnTree(List<Record> list, String parent) {
        List<TreeGird> result = new ArrayList<TreeGird>();
        for (Record r : list) {
            if (parent.equals(r.getStr("up_column_id"))) {
                List<TreeGird> sub = tree(list, (r.getStr("column_id")));
                result.add(new TreeGird(r.getStr("column_id"), r.getStr("column_name"), COLUMN_BASE + r.getStr("column_english") + FRONT_SUFFIX, r.getStr("template_path"), r.getStr("content_url"), sub));
            }
        }
        return result;
    }
}

package com.ofsoft.cms.admin.core.uitle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Record;
import com.ofsoft.cms.admin.core.config.AdminConst;
import com.ofsoft.cms.admin.core.plugin.freemarker.TempleteUtile;
import com.ofsoft.cms.admin.core.plugin.shiro.freemarker.ShiroTags;
import com.ofsoft.cms.admin.domain.GreatePathPojo;
import com.sanyka.weixin.utils.strutil.StringUtil;

import freemarker.template.TemplateException;

/**
 * 代码生成器 工具类
 */
public class GenUtils {
	public static String sqlTemplate = "comn.sql.html";
	public static String basePath = PropKit.use(AdminConst.ADMIN_CONFIG).get(
			"generate_base_path");

	/**
	 * 
	 * @param tableName
	 * @param moduleName
	 * @param fuctionName
	 * @param columnList
	 */
	public static void createSql(String tableName, String moduleName,
			String fuctionName, List<Record> columnList) {

		try {

			Map<String, Object> params = new HashMap<String, Object>();
			// 获取主键
			String tableKey = null;
			String tableKeyComment = null;
			for (Record r : columnList) {
				tableKey = r.get("column_key");
				if (StringUtil.isNotBlank(tableKey)
						&& "PRI".equalsIgnoreCase(tableKey)) {
					tableKey = r.get("column_name");
					tableKeyComment = r.get("column_comment");
					break;
				}
			}
			// 没有主键用第一个字段
			if (StringUtil.isNotBlank(tableKey)) {
				tableKey = columnList.get(0).get("column_name");
				tableKeyComment = columnList.get(0).get("column_comment");
			}
			params.put("table_name", tableName);
			params.put("module_name", moduleName);
			params.put("column_list", columnList);
			params.put("fuction_name", fuctionName);
			params.put("webroot", JFinal.me().getContextPath());
			params.put("shiro", new ShiroTags());
			params.put("table_key", tableKey);
			params.put("table_key_comment", tableKeyComment);

			List<GreatePathPojo> list = getTemplates(moduleName, fuctionName);
			for (GreatePathPojo gre : list) {
				File file = new File(gre.getTargetPath());
				file.createNewFile();
				TempleteUtile.process(gre.getTemplatePath(), params, file);
			}
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}

	}

	public static void createSql(String moduleName, String fuctionName) {
		createFile(basePath + "/resources/conf/sql/" + moduleName + "/"
				+ fuctionName + ".sql");
	}

	public static List<GreatePathPojo> getTemplates(String moduleName,
			String fuctionName) {
		List<GreatePathPojo> templates = new ArrayList<GreatePathPojo>();
		
		//创建目录
		File sql = new File( basePath
				+ "/resources/conf/sql/" + moduleName);
		if(!sql.exists()){
			sql.mkdirs();
		}
		// sql
		templates.add(new GreatePathPojo("comn.sql.html", basePath
				+ "/resources/conf/sql/" + moduleName + "/" + fuctionName
				+ ".sql"));
		//创建目录
		File page = new File( basePath
				+ "/webapp/WEB-INF/page/" + moduleName+ "/" + fuctionName);
		if(!page.exists()){
			page.mkdirs();
		}
		// 首页
		templates.add(new GreatePathPojo("index.html", basePath
				+ "/webapp/WEB-INF/page/" + moduleName + "/" + fuctionName
				+ "/index.html"));
		// 增加
		templates.add(new GreatePathPojo("add.html", basePath
				+ "/webapp/WEB-INF/page/" + moduleName + "/" + fuctionName
				+ "/add.html"));
		// 编辑
		templates.add(new GreatePathPojo("edit.html", basePath
				+ "/webapp/WEB-INF/page/" + moduleName + "/" + fuctionName
				+ "/edit.html"));
		return templates;
	}

	/**
	 * 创建文件
	 * 
	 * @param fileName
	 *            文件名
	 */
	public static void createFile(String fileName) {
		File file = new File(fileName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

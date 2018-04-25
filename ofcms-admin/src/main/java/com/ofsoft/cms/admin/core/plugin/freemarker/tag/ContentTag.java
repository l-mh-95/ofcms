package com.ofsoft.cms.admin.core.plugin.freemarker.tag;

import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;
import org.jdom.Content;

public class ContentTag extends BaseTag {
	
	public static final String TAG_NAME = "syk.content";

	@Override
	public void onRender() {

		BigInteger id = getParamToBigInteger("id");
		String slug = getParam("slug");

		if (id == null && StringUtils.isBlank(slug)) {
			renderText("");
			return;
		}

		Content content = null;
		if (id != null) {
//			content = ContentQuery.me().findById(id);
		}

		if (content == null && StringUtils.isNotBlank(slug)) {
//			content = ContentQuery.me().findBySlug(slug);
		}

		if (content == null) {
			renderText("");
			return;
		}

//		setVariable("content", content);
//		renderBody();
	}

}

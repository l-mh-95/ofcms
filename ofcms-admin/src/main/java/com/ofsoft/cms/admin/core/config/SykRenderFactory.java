package com.ofsoft.cms.admin.core.config;

import com.jfinal.render.Render;

public interface SykRenderFactory {
	public Render getJsonRender();

	public Render getSykJsonRender(String key, Object value);

	public Render getSykJsonRender(String[] attrs);

	public Render getSykJsonRender(String jsonText);

	public Render getSykJsonRender(Object object);

	public Render getSykQrcodeRender(String url);
}

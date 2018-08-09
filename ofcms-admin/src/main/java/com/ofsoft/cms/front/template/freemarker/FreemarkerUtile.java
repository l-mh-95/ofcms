package com.ofsoft.cms.front.template.freemarker;

import com.ofsoft.cms.front.template.directive.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by OF on 2018/5/13.
 */
public class FreemarkerUtile {

    public static Map initTemplate(){
        Map data = new HashMap();
        data.put("like",new likeDirective());
        data.put("column",new ColumnDirective());
        data.put("content",new ContentDirective());
        data.put("content_list",new ContentListDirective());
        data.put("ad",new AdDirective());
        data.put("announce",new AnnounceDirective());
        return  data;
    }
}

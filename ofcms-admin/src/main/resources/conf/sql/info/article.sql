#sql("query")
	select t.author,c.cat_name,t.creat_time,t.content_id,t.creat_time,t.is_show,t.article_desc,t.keywords,t.sort,t.status,t.thumbnail,t.title from syk_info_article t
	left join syk_info_category c on t.cat_id=c.cat_id
	 where t.status = '1'  
	#if (cat_id != "1" && cat_id?? ) and t.cat_id  = #para(cat_id)#end
	#if (title??) and t.title like concat('%', #para(title), '%')#end
	#if (sort?? && field) order by order_field order_sort #else order by t.creat_time desc  #end
#end

#sql("delete")
	update   syk_info_article set status = '0'  where content_id = #para(content_id)
#end
#sql("status")
	update   syk_info_article set is_show = #para(is_show) where content_id = #para(id)
#end

#sql("update")
	update  syk_info_article set cat_id = #para(cat_id),
	title = #para(title),
	thumbnail = #para(thumbnail),
	article_desc = #para(article_desc),
	is_show = #para(is_show),
	content = #para(content),
	keywords = #para(keywords),
	author = #para(author) where content_id = #para(content_id)
#end

#sql("detail")
	select t.author,t.cat_id,c.cat_name,t.creat_time,t.content_id,t.content,t.creat_time,t.is_show,t.article_desc,t.keywords,t.sort,t.status,t.thumbnail,t.title from syk_info_article t
	left join syk_info_category c on t.cat_id=c.cat_id   where t.content_id = #para(content_id)
#end

#sql("save")
	insert into syk_info_article (cat_id,title,thumbnail,article_desc,content, keywords,author,creat_time,is_show,status) values(#para(cat_id), #para(title), #para(thumbnail), #para(article_desc),#para(content),#para(keywords),#para(author),now(),#para(is_show),'1' )
#end
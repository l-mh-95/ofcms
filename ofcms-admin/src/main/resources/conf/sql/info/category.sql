#sql("query")
	select t.* from syk_info_category t where t.status = '1'  
	#if (cat_id??) and t.cat_id = , #para(cat_id) #end
	#if (cat_name??) and t.cat_name like concat('%', #para(cat_name), '%')#end
	#if (sort?? && field) order by order_field order_sort #end
#end

#sql("delete")
	update   syk_info_category set status = '0'  where cat_id = #para(cat_id)
#end

#sql("detail")
	select t.* from syk_info_category t where   t.cat_id = #para(cat_id)
#end

#sql("update")
	update  syk_info_category set  
	cat_name = #para(cat_name),
	cat_desc = #para(cat_desc),
	cat_image = #para(cat_image) , update_time = now() where cat_id = #para(cat_id)
#end

#sql("save")
	insert into syk_info_category (cat_name,cat_desc,cat_image,sort,is_show,create_time,status) values(#para(cat_name), #para(cat_desc), #para(cat_image), #para(sort),#para(is_show),now(),'1')
#end
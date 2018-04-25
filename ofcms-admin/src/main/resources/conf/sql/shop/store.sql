#sql("query")
	select t.shop_id,t.hosp_no,t.hosp_name,t.shop_name,t.shop_category,t.shop_address,t.shop_rank,t.shop_hours,t.promotions,t.shop_notice,t.status,t.remark from syk_shop_store t
	 where t.status = '1'  
	#if (hosp_no?? ) and t.hosp_no  = #para(hosp_no)#end
	#if (shop_id?? ) and t.shop_id  = #para(shop_id)#end
	#if (shop_name??) and t.shop_name like concat('%', #para(shop_name), '%')#end
	#if (sort?? && field) order by order_field order_sort #else order by t.shop_id desc  #end
#end

#sql("delete")
	update   syk_shop_store set status = '0'  where shop_id = #para(shop_id)
#end

#sql("update")
	update  syk_shop_store set  
	shop_name = #para(shop_name),
	shop_category = #para(shop_category),
	shop_address = #para(shop_address),
	shop_hours = #para(shop_hours)   where shop_id = #para(shop_id)
#end

#sql("detail")
	select t.shop_id,t.hosp_no,t.hosp_no,t.shop_name,t.shop_category,t.shop_address,t.shop_rank,t.shop_hours,t.promotions,t.shop_notice,t.status,t.remark from syk_shop_store t
	   where t.shop_id = #para(shop_id)
#end

#sql("save")
	insert into syk_shop_store (hosp_no,hosp_name,shop_name,shop_category,shop_address,shop_hours,status) values(#para(hosp_no), #para(hosp_name), #para(shop_name), #para(shop_category), #para(shop_address),#para(shop_hours),'1' )
#end
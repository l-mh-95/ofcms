#sql("query")
	select  
			 t.staff_id,
			 s.shop_name,
		 	 t.shop_id,
		 	 t.staff_name,
		 	 t.staff_mobile,
		 	 t.staff_sex,
		 	 t.staff_age,
		 	 t.staff_address,
		 	 t.status,
		 	 t.remark
	from
		  syk_shop_delivery_staff t left join syk_shop_store s on t.shop_id = s.shop_id where t.status = '1'
	#if (staff_id?? )  and t.staff_id = #para(staff_id)#end
	#if (shop_id?? )  and t.shop_id = #para(shop_id)#end
	#if (staff_name??) and t.staff_name like concat('%', #para(staff_name), '%')#end
	#if (sort?? && field) order by order_field order_sort  #else order by t.staff_id desc #end
#end
#sql("select_query")
	select  
			 	 staff_id,
		 	 shop_id,
		 	 staff_name,
		 	 staff_mobile
	from
		  syk_shop_delivery_staff where status = '1'
	#if (shop_id?? )  and shop_id = #para(shop_id)#end
	#if (sort?? && field) order by order_field order_sort  #else order by staff_id desc #end
#end
 
#sql("detail")
	select 
		 	 staff_id,
		 	 shop_id,
		 	 staff_name,
		 	 staff_mobile,
		 	 staff_sex,
		 	 staff_age,
		 	 staff_address,
		 	 status,
		 	 remark
	  from
		 syk_shop_delivery_staff where  staff_id  = #para(staff_id)
#end

#sql("save")
	insert into syk_shop_delivery_staff (
		 	 staff_id, 
		 	 shop_id, 
		 	 staff_name, 
		 	 staff_mobile, 
		 	 staff_sex, 
		 	 staff_age, 
		 	 staff_address, 
		 	 status, 
		 	 remark 
	) values(
		 	 #para(staff_id), 
		 	 #para(shop_id), 
		 	 #para(staff_name), 
		 	 #para(staff_mobile), 
		 	 #para(staff_sex), 
		 	 #para(staff_age), 
		 	 #para(staff_address), 
		 	 '1', 
		 	 #para(remark) 
	)
#end

#sql("delete")
	delete from syk_shop_delivery_staff where  staff_id  = #para(staff_id)
#end

#sql("status")
	update  syk_shop_delivery_staff set status = '0'  where staff_id  = #para(staff_id)
#end

#sql("update")
	update  
		syk_shop_delivery_staff set 
			   shop_id = #para(shop_id), 
			   staff_name = #para(staff_name), 
			   staff_mobile = #para(staff_mobile), 
			   staff_sex = #para(staff_sex), 
			   staff_age = #para(staff_age), 
			   staff_address = #para(staff_address), 
			   remark = #para(remark) 
	where  staff_id  = #para(staff_id)
#end
 

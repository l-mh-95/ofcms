#sql("query")
	select  
			 id,
		 	 project,
		 	 mobile,
		 	 wx_no,
		 	 qq_no,
		 	 name,
		 	 head_photo,
		 	 address,
		 	 special_areas,
		 	 age,
		 	 protect_age,
		 	 location,
		 	 money,
		 	 service_name,
		 	 service_time,
		 	 introduce,
		 	 evaluation_number,
		 	 score,
		 	 create_time,
		 	 is_audit,
		 	 sort,
		 	 status,
		 	 remark
	from
		  syk_shop_accompany where status = '1'
	#if (id?? ) and id = #para(id)#end
	#if (mobile??) and  mobile like concat('%', #para(mobile), '%')#end
	#if (sort?? && field) order by order_field order_sort  #else order by id desc #end
#end
 
#sql("detail")
	select 
		 	 id,
		 	 project,
		 	 mobile,
		 	 wx_no,
		 	 qq_no,
		 	 name,
		 	 head_photo,
		 	 address,
		 	 special_areas,
		 	 age,
		 	 protect_age,
		 	 location,
		 	 money,
		 	 service_name,
		 	 service_time,
		 	 introduce,
		 	 evaluation_number,
		 	 score,
		 	 create_time,
		 	 is_audit,
		 	 sort,
		 	 status,
		 	 remark
	  from
		 syk_shop_accompany where  id  = #para(id)
#end

#sql("save")
	insert into syk_shop_accompany (
		 	 mobile, 
		 	 wx_no, 
		 	 qq_no, 
		 	 name, 
		 	 head_photo, 
		 	 address, 
		 	 special_areas, 
		 	 age, 
		 	 protect_age, 
		 	 location, 
		 	 money, 
		 	 service_name, 
		 	 service_time, 
		 	 introduce, 
		 	 evaluation_number, 
		 	 score, 
		 	 create_time, 
		 	 is_audit, 
		 	 sort 
	) values(
		 	 #para(mobile), 
		 	 #para(wx_no), 
		 	 #para(qq_no), 
		 	 #para(name), 
		 	 #para(head_photo), 
		 	 #para(address), 
		 	 #para(special_areas), 
		 	 #para(age), 
		 	 #para(protect_age), 
		 	 #para(location), 
		 	 #para(money), 
		 	 #para(service_name), 
		 	 #para(service_time), 
		 	 #para(introduce), 
		 	 '0', 
		 	 '1', 
		 	 now(), 
		 	 #para(is_audit), 
		 	 #para(sort) 
	)
#end

#sql("delete")
	delete from syk_shop_accompany where  id  = #para(id)
#end

#sql("status")
	update  syk_shop_accompany set status = '0'  where id  = #para(id)
#end

#sql("update")
	update  
		syk_shop_accompany set 
			   mobile = #para(mobile), 
			   wx_no = #para(wx_no), 
			   qq_no = #para(qq_no), 
			   name = #para(name), 
			   head_photo = #para(head_photo), 
			   address = #para(address), 
			   special_areas = #para(special_areas), 
			   age = #para(age), 
			   protect_age = #para(protect_age), 
			   location = #para(location), 
			   money = #para(money), 
			   service_name = #para(service_name), 
			   service_time = #para(service_time), 
			   introduce = #para(introduce), 
			   is_audit = #para(is_audit), 
			   sort = #para(sort) 
	where  id  = #para(id)
#end
 

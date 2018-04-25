#sql("query")
	select t.user_id, t.open_id, t.unionid, t.wexin_no, t.nickname, t.user_name, t.country, t.province, t.city,   t.user_mobile, t.user_identifyid, t.user_birthday, t.user_sex,t.photo_image_url, t.user_email, t.user_address,t.user_status, t.create_datetime, t.remark  from syk_user t
	 where t.user_id is not null  
	#if (open_id?? ) and t.open_id  = #para(open_id)#end
	#if (user_name?? ) and t.user_name  = #para(user_name)#end
	#if (nickname??) and t.nickname like concat('%', #para(nickname), '%')#end
	#if (sort?? && field) order by order_field order_sort #else order by t.create_datetime desc  #end
#end

#sql("delete")
	update  syk_user set user_status = '0'  where user_id = #para(user_id)
#end

#sql("update")
	update  syk_user set  
	user_name = #para(user_name),
	nickname = #para(nickname),
	user_address = #para(user_address)  where user_id = #para(user_id)
#end

#sql("detail")
	t.user_id, t.open_id, t.unionid, t.wexin_no, t.nickname, t.user_name, t.country, t.province, t.city,   t.user_mobile, t.user_identifyid, t.user_birthday, t.user_sex,t.photo_image_url, t.user_email, t.user_address,t.user_status, t.create_datetime, t.remark  from syk_user t
	   where t.user_id = #para(user_id)
#end

#sql("user_count")
	select
		month (t.create_datetime) month,
		count(t.open_id) count
	from
		syk_user t where year (t.create_datetime)=year (now())
	group by
		month (t.create_datetime)
#end
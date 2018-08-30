#sql("query")
	select  
			 id,
		 	 site_id,
		 	 day_content_count,
		 	 total_content_count,
		 	 day_access_count,
		 	 total_access_count,
		 	 day_comment_count,
		 	 total_comment_count,
		 	 day_bbs_count,
		 	 total_bbs_count,
		 	 count_date
	from
		  of_cms_count where   site_id = #para(site_id) and count_date = #para(count_date)
#end
#sql("list")
select
			 id,
		 	 site_id,
		 	 day_content_count,
		 	 total_content_count,
		 	 day_access_count,
		 	 total_access_count,
		 	 day_comment_count,
		 	 total_comment_count,
		 	 day_bbs_count,
		 	 total_bbs_count,
		 	 count_date,create_time
	from
		  of_cms_count where   site_id = #para(site_id)
		 #if (count_date?? ) and count_date = #para(count_date)#end
	#if (sort?? && field) order by order_field order_sort  #else order by id desc #end
#end




#sql("save_access")
	insert into of_cms_access (
		 	 site_id,
		 	 access_ip,
		 	 access_entry_page,
		 	 access_last_page,
		 	 access_date,
		 	 access_time,
		 	 access_source,
		 	 access_keyword
	) values(
		 	 #para(site_id),
		 	 #para(access_ip),
		 	 #para(access_entry_page),
		 	 #para(access_last_page),
		 	 curdate(),
		 	 curtime(),
		 	 #para(access_referer),
		 	 #para(user_agent)
	)
#end


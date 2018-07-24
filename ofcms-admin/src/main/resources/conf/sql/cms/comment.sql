#sql("query")
	select  
			 	 comment_id,
		 	 site_id,
		 	 content_code,
		 	 comment_type,
		 	 comment_title,
		 	 comment_url,
		 	 comment_content,
		 	 comment_name,
		 	 comment_time,
		 	 comment_ip,
		 	 create_time,
		 	 check_status,
		 	 status,
		 	 remark
	from
		  of_cms_comment where site_id = #para(site_id)
	#if (comment_id?? ) and  comment_id = #para(comment_id)#end
	#if (sort?? && field) order by order_field order_sort  #else order by comment_id desc #end
#end
 
#sql("detail")
	select 
		 	 comment_id,
		 	 site_id,
		 	 content_code,
		 	 comment_type,
		 	 comment_title,
		 	 comment_url,
		 	 comment_content,
		 	 comment_name,
		 	 comment_time,
		 	 comment_ip,
		 	 create_time,
		 	 check_status,
		 	 status,
		 	 remark
	  from
		 of_cms_comment where  comment_id  = #para(comment_id)
#end

#sql("save")
	insert into of_cms_comment (
		 	 comment_id, 
		 	 site_id, 
		 	 content_code, 
		 	 comment_type, 
		 	 comment_title, 
		 	 comment_url, 
		 	 comment_content, 
		 	 comment_name, 
		 	 comment_time, 
		 	 comment_ip, 
		 	 create_time, 
		 	 check_status, 
		 	 status, 
		 	 remark 
	) values(
		 	 #para(comment_id), 
		 	 #para(site_id), 
		 	 #para(content_code), 
		 	 #para(comment_type), 
		 	 #para(comment_title), 
		 	 #para(comment_url), 
		 	 #para(comment_content), 
		 	 #para(comment_name), 
		 	 #para(comment_time), 
		 	 #para(comment_ip), 
		 	 #para(create_time), 
		 	 #para(check_status), 
		 	 #para(status), 
		 	 #para(remark) 
	)
#end

#sql("delete")
	delete from of_cms_comment where  comment_id  = #para(comment_id)
#end

#sql("status")
	update  of_cms_comment set status = '0'  where comment_id  = #para(comment_id)
#end

#sql("update")
	update  
		of_cms_comment set 
			   comment_id = #para(comment_id), 
			   site_id = #para(site_id), 
			   content_code = #para(content_code), 
			   comment_type = #para(comment_type), 
			   comment_title = #para(comment_title), 
			   comment_url = #para(comment_url), 
			   comment_content = #para(comment_content), 
			   comment_name = #para(comment_name), 
			   comment_time = #para(comment_time), 
			   comment_ip = #para(comment_ip), 
			   create_time = #para(create_time), 
			   check_status = #para(check_status), 
			   status = #para(status), 
			   remark = #para(remark) 
	where  comment_id  = #para(comment_id)
#end
 

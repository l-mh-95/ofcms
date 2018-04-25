#sql("update")
	update  
		syk_sys_param set 
			  param_value = #para(value)
	where  param_id  = #para(id)
#end
 

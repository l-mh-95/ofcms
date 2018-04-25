#sql("reg_query")
select 
	reg_id,
	patient_in_no,
	reg_type,
	office_name,
	reg_time,order_id,
	patient_name,
	doctor_name,
	reg_type,
	reg_free,
	status 
from syk_user_register 

 where patient_in_no=#para(id_no)  
	#if (sort?? && field) order by order_field order_sort #else order by order_id desc  #end
#end

#sql("account_query")
	select
		a.pay_time,a.open_id,a.order_no,a.patient_id,a.pay_money,a.pay_result,a.pay_type,a.pay_mode,u.nickname 
	from
		syk_user_friends_info f
	RIGHT JOIN syk_user_account_detail a on a.patient_id = f.patient_id 
	left join syk_user u on a.open_id = u.open_id
	where
		f.hosp_no = #para(hosp_no)
	and f.in_no = #para(id_no)
	and status = '1'
	#if (sort?? && field) order by order_field order_sort #else order by a.order_no desc  #end
#end

 
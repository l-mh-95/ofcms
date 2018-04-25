#sql("query")
	select  
			 t.order_id,
		 	 t.shop_id,
		 	 t.open_id,
		 	 u.nickname,
		 	 t.order_total_amount,
		 	 t.iffe_total_amount,
		 	 t.shipping_price,
		 	 t.shipping_mode,
		 	 t.order_status,
		 	 t.pay_mode,
		 	 t.pay_status,
		 	 t.pay_trans_no,
		 	 t.order_create_time,
		 	 t.order_pay_time,
		 	 t.order_cancel_time,
		 	 s.staff_id,
		 	 s.staff_mobile,
		 	 s.staff_name,
		 	 t.receiver_detail_address,
		 	 t.receiver_mobile,
		 	 t.receiver_name,
		 	 t.estimated_delivery_time,
		 	 t.review_status,
		 	 t.status,
		 	 t.remark
	from
		  syk_shop_order t left join syk_shop_delivery_staff s on t.staff_id = s.staff_id 
		  left join syk_user u on t.open_id =u.open_id 
		  where order_status !='99' 
	#if (order_id?? ) and  t.order_id = #para(order_id)#end
	#if (order_status?? ) and  t.order_status = #para(order_status)#end
	#if (start_date?? ) and (date_format(t.order_create_time,'%Y-%m-%d') between  #para(start_date) and #para(end_date)  ) #end
	#if (sort?? && field) order by order_field order_sort  #else order by t.order_id desc #end
#end
 
#sql("detail")
	select 
		 	 order_id,
		 	 shop_id,
		 	 open_id,
		 	 order_total_amount,
		 	 iffe_total_amount,
		 	 shipping_price,
		 	 shipping_mode,
		 	 order_status,
		 	 pay_mode,
		 	 pay_status,
		 	 pay_trans_no,
		 	 order_create_time,
		 	 order_pay_time,
		 	 order_cancel_time,
		 	 staff_id,
		 	 staff_mobile,
		 	 staff_name,
		 	 receiver_detail_address,
		 	 receiver_mobile,
		 	 receiver_name,
		 	 estimated_delivery_time,
		 	 review_status,
		 	 status,
		 	 remark
	  from
		 syk_shop_order where  order_id  = #para(order_id)
#end

#sql("save")
	insert into syk_shop_order (
		 	 order_id, 
		 	 shop_id, 
		 	 open_id, 
		 	 order_total_amount, 
		 	 iffe_total_amount, 
		 	 shipping_price, 
		 	 shipping_mode, 
		 	 order_status, 
		 	 pay_mode, 
		 	 pay_status, 
		 	 pay_trans_no, 
		 	 order_create_time, 
		 	 order_pay_time, 
		 	 order_cancel_time, 
		 	 staff_id, 
		 	 staff_mobile, 
		 	 staff_name, 
		 	 receiver_detail_address, 
		 	 receiver_mobile, 
		 	 receiver_name, 
		 	 estimated_delivery_time, 
		 	 review_status, 
		 	 status, 
		 	 remark 
	) values(
		 	 #para(order_id), 
		 	 #para(shop_id), 
		 	 #para(open_id), 
		 	 #para(order_total_amount), 
		 	 #para(iffe_total_amount), 
		 	 #para(shipping_price), 
		 	 #para(shipping_mode), 
		 	 #para(order_status), 
		 	 #para(pay_mode), 
		 	 #para(pay_status), 
		 	 #para(pay_trans_no), 
		 	 #para(order_create_time), 
		 	 #para(order_pay_time), 
		 	 #para(order_cancel_time), 
		 	 #para(staff_id), 
		 	 #para(staff_mobile), 
		 	 #para(staff_name), 
		 	 #para(receiver_detail_address), 
		 	 #para(receiver_mobile), 
		 	 #para(receiver_name), 
		 	 #para(estimated_delivery_time), 
		 	 #para(review_status), 
		 	 #para(status), 
		 	 #para(remark) 
	)
#end

#sql("delete")
	delete from syk_shop_order where  order_id  = #para(order_id)
#end

#sql("status")
	update  syk_shop_order set order_status = '99'  where order_id  = #para(order_id)
#end


#sql("address_update")
	update  
		syk_shop_order set 
			   receiver_detail_address = #para(receiver_detail_address), 
			   receiver_mobile = #para(receiver_mobile), 
			   receiver_name = #para(receiver_name) 
			where  order_id  = #para(order_id)
#end

#sql("status_update")
	update  
		syk_shop_order set 
			   order_status = #para(order_status)
			where  order_id  = #para(order_id)
#end

#sql("deliver_update")
	update  
		syk_shop_order set 
			    staff_id = #para(staff_id), 
			    staff_mobile = #para(staff_mobile),
			    staff_name = #para(staff_name),
			     order_status ='03' 
			where  order_id  = #para(order_id)
#end

#sql("update")
	update  
		syk_shop_order set 
			   order_total_amount = #para(order_total_amount) 
	where  order_id  = #para(order_id)
#end
 
#sql("order_count")
	select   (select count(t.order_id) from syk_shop_order t where t.order_status='01') deal_pay,
			 (select count(t.order_id) from syk_shop_order t where t.order_status='02') deal_ship,
			 (select count(t.order_id) from syk_shop_order t where t.order_status='03') deal_receive,  
      		 (select count(t.order_id) from syk_shop_order t where t.order_status='04') completed,  
			 (select count(t.order_id) from syk_shop_order t where t.order_status='00') cancelled 
 from dual 
#end
 #sql("order_month_count")
	select
		month (t.order_create_time) month,
		count(t.order_id) count
	from
		syk_shop_order t where year (order_create_time)=year (now())
	group by
		month (order_create_time)
#end

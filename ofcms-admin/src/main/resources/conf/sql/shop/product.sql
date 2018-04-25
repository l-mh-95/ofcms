#sql("query")
	select  
			 	 product_id,
		 	 shop_id,
		 	 product_name,
		 	 product_cat,
		 	 product_image,
		 	 product_desc,
		 	 product_detail,
		 	 product_price,
		 	 discount_price,
		 	 product_unit,
		 	 product_inventory,
		 	 max_count,
		 	 min_count,
		 	 box_num,
		 	 box_price,
		 	 month_sold,
		 	 praise,
		 	 is_sign,
		 	 is_hot,
		 	 is_recommend,
		 	 is_promote,
		 	 is_shelves,
		 	 is_sold_out,
		 	 shelves_time,
		 	 create_time,
		 	 is_audit,
		 	 sort,
		 	 status,
		 	 remark
	from
		  syk_shop_product where status ='1'
	#if (product_id?? ) and product_id = #para(product_id)#end
	#if (product_name??) and  product_name like concat('%', #para(product_name), '%')#end
	#if (sort?? && field) order by order_field order_sort  #else order by product_id desc #end
#end
 
#sql("detail")
	select 
		 	 product_id,
		 	 shop_id,
		 	 product_name,
		 	 product_cat,
		 	 product_image,
		 	 product_desc,
		 	 product_detail,
		 	 product_price,
		 	 discount_price,
		 	 product_unit,
		 	 product_inventory,
		 	 max_count,
		 	 min_count,
		 	 box_num,
		 	 box_price,
		 	 month_sold,
		 	 praise,
		 	 is_sign,
		 	 is_hot,
		 	 is_recommend,
		 	 is_promote,
		 	 is_shelves,
		 	 is_sold_out,
		 	 shelves_time,
		 	 create_time,
		 	 is_audit,
		 	 sort,
		 	 status,
		 	 remark
	  from
		 syk_shop_product where  product_id  = #para(product_id)
#end

#sql("save")
	insert into syk_shop_product (
		 	 shop_id, 
		 	 product_name, 
		 	 product_cat, 
		 	 product_image, 
		 	 product_desc, 
		 	 product_detail, 
		 	 product_price, 
		 	 discount_price, 
		 	 product_unit, 
		 	 product_inventory, 
		 	 max_count, 
		 	 min_count, 
		 	 box_num, 
		 	 box_price, 
		 	 month_sold, 
		 	 praise, 
		 	 is_sign, 
		 	 is_hot, 
		 	 is_recommend, 
		 	 is_promote, 
		 	 is_shelves, 
		 	 is_sold_out, 
		 	 shelves_time, 
		 	 create_time, 
		 	 is_audit, 
		 	 sort, 
		 	 status, 
		 	 remark 
	) values(
		 	 #para(shop_id), 
		 	 #para(product_name), 
		 	 #para(product_cat), 
		 	 #para(product_image), 
		 	 #para(product_desc), 
		 	 #para(product_detail), 
		 	 #para(product_price), 
		 	 #para(discount_price), 
		 	 #para(product_unit), 
		 	 #para(product_inventory), 
		 	 #para(max_count), 
		 	 #para(min_count), 
		 	 #para(box_num), 
		 	 #para(box_price), 
		 	 #para(month_sold), 
		 	 #para(praise), 
		 	 #para(is_sign), 
		 	 #para(is_hot), 
		 	 #para(is_recommend), 
		 	 #para(is_promote), 
		 	 #para(is_shelves), 
		 	 #para(is_sold_out), 
		 	 #para(shelves_time), 
		 	 now(), 
		 	 #para(is_audit), 
		 	 #para(sort), 
		 	 '1', 
		 	 #para(remark) 
	)
#end

#sql("delete")
	delete from syk_shop_product where  product_id  = #para(product_id)
#end

#sql("status")
	update  syk_shop_product set status = '0'  where product_id  = #para(product_id)
#end
#sql("shelves")
	update  syk_shop_product set is_shelves = #para(is_shelves)  where product_id  = #para(id)
#end

#sql("update")
	update  
		syk_shop_product set 
			   product_id = #para(product_id), 
			   shop_id = #para(shop_id), 
			   product_name = #para(product_name), 
			   product_cat = #para(product_cat), 
			   product_image = #para(product_image), 
			   product_desc = #para(product_desc), 
			   product_detail = #para(product_detail), 
			   product_price = #para(product_price), 
			   discount_price = #para(discount_price), 
			   product_unit = #para(product_unit), 
			   product_inventory = #para(product_inventory), 
			   max_count = #para(max_count), 
			   min_count = #para(min_count), 
			   box_num = #para(box_num), 
			   box_price = #para(box_price), 
			   month_sold = #para(month_sold), 
			   praise = #para(praise), 
			   is_sign = #para(is_sign), 
			   is_hot = #para(is_hot), 
			   is_recommend = #para(is_recommend), 
			   is_promote = #para(is_promote), 
			   is_shelves = #para(is_shelves), 
			   is_sold_out = #para(is_sold_out), 
			   shelves_time = #para(shelves_time), 
			   create_time = #para(create_time), 
			   is_audit = #para(is_audit), 
			   sort = #para(sort), 
			   remark = #para(remark) 
	where  product_id  = #para(product_id)
#end
 

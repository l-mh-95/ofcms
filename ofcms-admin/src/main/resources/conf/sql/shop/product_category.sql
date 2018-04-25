#sql("query")
	select  
			 t.cat_id,
			 s.shop_name,
		 	 t.shop_id,
		 	 t.cat_name,
		 	 t.cat_desc,
		 	 t.cat_image,
		 	 t.sort,
		 	 t.status,
		 	 t.remark
	from
		  syk_shop_product_category t
		  left join syk_shop_store s on t.shop_id = s.shop_id
	#if (cat_id?? ) where  t.cat_id = #para(cat_id)#end
	#if (sort?? && field) order by order_field order_sort  #else order by t.cat_id desc #end
#end
 
#sql("detail")
	select 
		 	 cat_id,
		 	 shop_id,
		 	 cat_name,
		 	 cat_desc,
		 	 cat_image,
		 	 sort,
		 	 status,
		 	 remark
	  from
		 syk_shop_product_category where  cat_id  = #para(cat_id)
#end

#sql("save")
	insert into syk_shop_product_category (
		 	 shop_id, 
		 	 cat_name, 
		 	 cat_desc, 
		 	 cat_image, 
		 	 sort, 
		 	 status, 
		 	 remark 
	) values(
		 	 #para(shop_id), 
		 	 #para(cat_name), 
		 	 #para(cat_desc), 
		 	 #para(cat_image), 
		 	 #para(sort), 
		 	 #para(status), 
		 	 #para(remark) 
	)
#end

#sql("delete")
	delete from syk_shop_product_category where  cat_id  = #para(cat_id)
#end

#sql("status")
	update  syk_shop_product_category set status = '0'  where cat_id  = #para(cat_id)
#end

#sql("update")
	update  
		syk_shop_product_category set 
			   cat_id = #para(cat_id), 
			   shop_id = #para(shop_id), 
			   cat_name = #para(cat_name), 
			   cat_desc = #para(cat_desc), 
			   cat_image = #para(cat_image), 
			   sort = #para(sort), 
			   status = #para(status), 
			   remark = #para(remark) 
	where  cat_id  = #para(cat_id)
#end
 

package com.ofsoft.cms.admin.controller.order;

import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.ofsoft.cms.admin.controller.BaseController;
import com.ofsoft.cms.core.annotation.Action;

/**
 * 系统角色功能
 * 
 * @author OF
 * @date 2018年1月8日
 */
@Action(path = "/shop/order")
public class SysOrderController extends BaseController {

	/**
	 * 修改配送信息
	 */
	public void delivery() {
		Map<String, Object> params = getParamsMap();
		SqlPara sql = Db.getSqlPara("shop.delivery.select_query", params);
		List<Record> list = Db.find(sql);
		setAttr("list", list);
		setAttr("result", params);
		render("/shop/order/ship/modify_delivery.html");
	}

	/**
	 * 取消订单
	 */
	public void cancel() {
		Map<String, Object> params = getParamsMap();
		params.put("order_status", "00");
		SqlPara sql = Db.getSqlPara("shop.order.status_update", params);
		Db.update(sql);
		rendSuccessJson();
	}

	/**
	 * 修改订单状态
	 */
	public void updateStatus() {
		Map<String, Object> params = getParamsMap();
		SqlPara sql = Db.getSqlPara("shop.order.status_update", params);
		Db.update(sql);
		rendSuccessJson();
	}
}

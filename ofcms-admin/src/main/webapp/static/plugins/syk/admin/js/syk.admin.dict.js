/**
 *  字典配置
 * @author: OF
 * @version 1.0.0
 */
layui.sykDict = {
		//城市
		city : {
			formatType : "local",
			labelField : "name",
			valueField : "code",
			//静态数据
			data:[{"code":0,"name":"北京","style":"color:#F00;"},
				{"code":1,"name":"上海"},
				{"code":2,"name":"广州"},
				{"code":3,"name":"深圳"},
				{"code":4,"name":"杭州"}
			]
		},
		//性别
		sex : {
			formatType : "local",
			labelField : "name",
			valueField : "code",
			//静态数据
			data:[{"code":1,"name":"男","style":"color:#F00;","css":"layui-badge layui-bg-green"},
				 {"code":2,"name":"女","css":"layui-badge layui-bg-orange"},
			]
		},
		//角色
		select_role : {
			formatType : "server",
			sqlid:"system.role.query",
			labelField : "role_name",
			valueField : "role_id"
		},
		//文章分类
		info_category : {
			formatType : "server",
			sqlid:"info.category.query",
			labelField : "cat_name",
			valueField : "cat_id"
		},
		//配送员工
		delivery : {
			formatType : "server",
			sqlid:"shop.delivery.query",
			labelField : "staff_name",
			valueField : "staff_id"
		},
		//所属医院
		shop_store : {
			formatType : "server",
			action:"hosp/hospital/getHospitalSelectList.json",
			labelField : "hosp_name",
			valueField : "hosp_no"
		},
		//微信菜单类型
		weixin_menu_type : {
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[{"code":"view","name":"网页"},
			      {"code":"click","name":"点击"},
			      {"code":"miniprogram","name":"小程序"},
			      {"code":"media_id","name":"素材"}
			]
		},
		//商家分类
		shop_category : {
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[{"code":1,"name":"营养食堂 "},
					{"code":2,"name":"生活用户"}
			]
		},
		//订单状态
		order_status : {
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[{"code":"00","name":"已取消 "},
					{"code":"01","name":"已提交"},
					{"code":"02","name":"已支付"},
					{"code":"03","name":"已发货"},
					{"code":"04","name":"已完成"},
					{"code":"05","name":"退款申请"},
					{"code":"99","name":"已删除"}
			]
		},
		//配送方式
		distribution_mode : {
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[{"code":0,"name":"在线支付 "},
					{"code":1,"name":"第三方支付"}
			]
		},
		//支付状态
		pay_status : {
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[{"code":00,"name":"未支付 "},
					{"code":01,"name":"已支付"},
					{"code":02,"name":"支付失败"}
					
			]
		},
		//支付类型
		pay_type: {
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[{"code":1,"name":"挂号 "},
					{"code":2,"name":"自助缴费"},
					{"code":3,"name":"出院结算"},
					{"code":4,"name":"押金"},
					{"code":5,"name":"日用用品"},
					{"code":6,"name":"营养套餐"},
					{"code":7,"name":"门诊缴费"}
					
			]
		},
		//商品单位
		product_unit:{
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[  {"code":"包","name":"包"},
					{"code":"件","name":"件"},
					{"code":"条","name":"条"},
					{"code":"份","name":"份"},
					{"code":"个","name":"个"},
					{"code":"支","name":"支"},
					{"code":"片","name":"片"},
					{"code":"项","name":"项"},
					{"code":"枝","name":"枝"},
					{"code":"幅","name":"幅"},
					{"code":"块","name":"块"},
					{"code":"段","name":"段"}
			]
		},
		//商品分类
		product_cat:{
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[{"code":1,"name":"日用品 "},
					{"code":2,"name":"食品"},
					{"code":3,"name":"老人用品"},
					{"code":4,"name":"儿童用品"},
					{"code":5,"name":"护理用品"},
					{"code":6,"name":"护理陪护"},
					{"code":7,"name":"产妇用品"},
					{"code":8,"name":"内衣内裤"},
					{"code":9,"name":"洗漱用品"},
					{"code":10,"name":"美食套餐"},
					{"code":11,"name":"营养汤套餐"},
					{"code":12,"name":"清单套餐"},
					{"code":13,"name":"老人用品"},
					
			]
		},
		//陪护类型
		accompany_cat : {
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[{"code":1,"name":"月嫂 "},
					{"code":2,"name":"育婴师"},
					{"code":2,"name":"保姆"},
					{"code":2,"name":"钟点工"},
					{"code":2,"name":"陪护"}
			]
		},
		//状态启用/禁止
		status : {
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[{"code":0,"name":"禁止 "},
					{"code":1,"name":"启用"}
			]
		},
		//定时任务1未启动 2、已启动  3已停止      
		job_status : {
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[{"code":1,"name":"未启动 "},
			      {"code":2,"name":"已启动"},
			      {"code":3,"name":"已停止"},
			      {"code":4,"name":"已暂停"}
			]
		},
		//床位等级
		bed_level : {
			formatType : "local",
			labelField : "name",
			valueField : "code",
			//静态数据
			data:[{"code":1,"name":"一级","style":"color:#F00;","css":"layui-badge layui-bg-green"},
				 {"code":2,"name":"二级","css":"layui-badge layui-bg-orange"},
			]
		},
		//挂号类型
		reg_type : {
			formatType : "local",
			labelField : "name",
			valueField : "code",
			//静态数据
			data:[{"code":1,"name":"上午","style":"color:#F00;","css":"layui-badge layui-bg-green"},
			      {"code":2,"name":"下午","css":"layui-badge layui-bg-orange"},
			      ]
		},
		menu_type : {
			formatType : "local",
			labelField : "name",
			valueField : "code",
			data:[{"code":0,"name":"目录","style":"color:#F00;","css":"layui-badge layui-bg-cyan"},
			      {"code":1,"name":"菜单","css":"layui-badge layui-bg-orange"},
			      {"code":2,"name":"按钮","css":"layui-badge layui-bg-green"},
			      {"code":3,"name":"其他","css":"layui-badge layui-bg-cyan"},
			      ]
		},
		
		//类型
		type : {
			formatType : "local",
			labelField : "name",
			valueField : "code",
			spaceMode : " ",//展示多个数据分隔符，默认,
			data:[{"code":"write","name":"写作","css":"layui-badge layui-bg-orange"},
				{"code":"read","name":"阅读","css":"layui-badge layui-bg-green"},
				{"code":"dai","name":"发呆","css":"layui-badge layui-bg-cyan"}]
		}
		 
		 
};
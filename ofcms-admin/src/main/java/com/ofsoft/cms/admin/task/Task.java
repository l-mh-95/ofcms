package com.ofsoft.cms.admin.task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task extends BaseTask {

	@Override
	public void task( ) {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date()) + "我在执行Task 任务");
	}
}

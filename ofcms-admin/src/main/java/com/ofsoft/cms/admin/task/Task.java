package com.ofsoft.cms.admin.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;

public class Task extends BaseTask {

	@Override
	public void task(JobExecutionContext jobexecutioncontext) {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date()) + "我在执行Task 任务");
	}
}

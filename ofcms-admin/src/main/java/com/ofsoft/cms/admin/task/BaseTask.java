package com.ofsoft.cms.admin.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseTask implements Job {

	@Override
	public void execute(JobExecutionContext jobexecutioncontext)
			throws JobExecutionException {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date()) + "开始任务★★★★★★★★★★★");
		task();
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date()) + "结束任务★★★★★★★★★★★");
	}
	protected abstract void task();

}

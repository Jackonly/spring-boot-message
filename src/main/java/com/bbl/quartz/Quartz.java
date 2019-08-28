package com.bbl.quartz;

import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbl.pojo.Users;
import com.bbl.service.UsersService;

/**
 * Job类
 *
 *
 */
public class Quartz implements Job {
	
	@Autowired
	private UsersService usersService;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("(定时任务:)Spring-boot-Quartz...."+new Date());
		List<Users> list=this.usersService.findUserAll();
		for(Users u:list){
			System.out.println(u.getName()+"------"+u.getAge());
		}
	}
}

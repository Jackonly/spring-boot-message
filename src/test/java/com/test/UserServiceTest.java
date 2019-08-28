package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.bbl.App;
import com.bbl.pojo.Users;
import com.bbl.service.UsersService;
import com.bbl.service.impl.UsersServiceImpl;;

/**
 * SpringBoot测试类
 *@RunWith:启动器 
 *SpringJUnit4ClassRunner.class：让junit与spring环境进行整合
 *
 *@SpringBootTest(classes={App.class}) 1,当前类为springBoot的测试类
 *@SpringBootTest(classes={App.class}) 2,加载SpringBoot启动类。启动springBoot
 *
 *junit与spring整合 @Contextconfiguartion("classpath:applicationContext.xml")
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes={App.class})
public class UserServiceTest {

	@Autowired
	private UsersServiceImpl userServiceImpl;
	
	@Autowired
	private UsersService usersService;
	
	@Test
	public void testAddUser(){
		Users user= this.userServiceImpl.findUserById(12);
		System.out.println("姓名："+user.getName()+"\n"+"年龄："+user.getAge());
	}
	
	
		@Test
		public void testFindUserById(){
			
			
			//第一次查询
			Users s=this.usersService.findUserById(12);
			System.out.println("第一次查询"+"姓名:"+s.getName()+"\n"+"年龄:"+s.getAge());
			//更新数据
			Users users = new Users();
			users.setId(12);
			users.setAge(26);
			users.setName("晁文奇");
			this.usersService.updateUser(users);
			
			//第二次查询
			Users s1=this.usersService.findUserById(12);
			
			System.out.println("第二次查询"+"姓名:"+s1.getName()+"\n"+"年龄:"+s1.getAge());
		}
		
		@Test
		public void testFindUserByPage(){
			Pageable pageable = new PageRequest(0, 2);
			//第一次查询
			System.out.println(this.usersService.findUserByPage(pageable).getTotalElements());
			
			//第二次查询
			System.out.println(this.usersService.findUserByPage(pageable).getTotalElements());
			
			//第三次查询
			pageable = new PageRequest(1, 2);
			System.out.println(this.usersService.findUserByPage(pageable).getTotalElements());
		}
		
		@Test
		public void testFindAll(){
			//第一次查询
			System.out.println(this.usersService.findUserAll().size());
			
			Users users = new Users();
			users.setAge(40);
			users.setName("康熙");
			this.usersService.saveUsers(users);
			
			//第二次查询
			System.out.println(this.usersService.findUserAll().size());
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

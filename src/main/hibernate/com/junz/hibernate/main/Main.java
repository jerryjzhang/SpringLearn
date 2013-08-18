package com.junz.hibernate.main;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.junz.hibernate.dao.UserDao;
import com.junz.hibernate.domain.User;
import com.junz.hibernate.time.TimeUtil;

public class Main {
	public static void main(String [] args){
		foo();
	}
	
	public static void bar(){
		Timestamp time = new Timestamp(2013-1900, 2, 23, 00, 00, 00, 00);
		System.out.println(time.toString());
		System.out.println(TimeUtil.createUnixTimeNumber(time, "Asia/Shanghai"));
		System.out.println(TimeUtil.createUnixTimeNumber(time, "Etc/GMT+4"));
		System.out.println(TimeUtil.createTimeRepresentation(1363968000000L, "Asia/Shanghai"));
		System.out.println(TimeUtil.createTimeRepresentation(1363968000000L, "GMT"));
	}
	
	public static void foo(){
		ClassPathXmlApplicationContext ctx = 
				new ClassPathXmlApplicationContext("com/junz/hibernate/config/applicationContext.xml");
		
		UserDao userDao = (UserDao)ctx.getBean(UserDao.class);
		User user = new User();
		user.setName("junz");
		Timestamp time = new Timestamp(new Date().getTime());
		time.setNanos(0);		
		user.setTime(time);
		
		userDao.save(user);
		User userFromDb = userDao.get(1);
		if(userFromDb != null){
			System.out.println(userFromDb.getName());
		}
	}
}

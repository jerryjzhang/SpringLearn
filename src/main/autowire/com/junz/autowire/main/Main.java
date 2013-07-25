package com.junz.autowire.main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.junz.autowire.domain.User;

public class Main {
	//@Autowired
	//@Qualifier("user2")
	//@Resource(name="user1")
	private User user;
	
	@Resource(name="user2")
	public void setUser(User user){
		this.user = user;
	}
	
	public static void main(String [] args){
		
		ClassPathXmlApplicationContext ctx = 
				new ClassPathXmlApplicationContext("com/junz/autowire/config/applicationContext.xml");
		Main m = (Main)ctx.getBean("main");
		
		System.out.println(m.user.getName());
		

	}
}

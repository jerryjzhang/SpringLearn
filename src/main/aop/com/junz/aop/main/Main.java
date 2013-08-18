package com.junz.aop.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.junz.aop.target.AdvicedDomainObject;

public class Main {
	public static void main(String [] args){
		ClassPathXmlApplicationContext ctx = 
				new ClassPathXmlApplicationContext("com/junz/aop/config/applicationContext.xml");
		
		AdvicedDomainObject ado = (AdvicedDomainObject)ctx.getBean("advicedObject");
		ado.print(1);
		System.out.println("Bean class is: " + ado.getClass().getName());
	}
}

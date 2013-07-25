package com.junz.aop.target;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.junz.aop.aspect.LoggingMethod;


public class AdvicedDomainObjectImpl implements AdvicedDomainObject{
	@LoggingMethod(name="print()")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void print(){
		System.out.println("invoke print()");
		/*
		 * Note: A call from a method in an adviced target to another method in 
		 * the same adviced target does not get intercepted by the proxy. Therefore,
		 * invoking help() here would be be logged by the LoggingAspect
           Read more: http://www.intertech.com/Blog/Post/Secrets-of-the-Spring-AOP-Proxy.aspx#ixzz25ruRUmLD 
		 */
		help();
	}
	
	@LoggingMethod(name="help()")
	public void help(){
		System.out.println("invoke help()");
	}
}

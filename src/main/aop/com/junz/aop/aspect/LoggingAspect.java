package com.junz.aop.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
@Order(2)
@Component
public class LoggingAspect {
	@Pointcut("execution(public * *(..))")
    public void anyPublicMethodExecution() {
    };
    
    @Before("anyPublicMethodExecution() && @annotation(loggingMethod)" )
    public void logg_start(LoggingMethod loggingMethod) {
        System.out.println("logging start "+loggingMethod.name());
    }
    
    @After("anyPublicMethodExecution() && @annotation(loggingMethod)")
    public void logg_end(LoggingMethod loggingMethod) {
        System.out.println("logging end "+loggingMethod.name());
    }
    
}

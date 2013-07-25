package com.junz.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
/*
 * @Order is used to define aspect precedence
 * the smaller value, the higher precedence
 */
@Order(1)
@Component
public class ProfilingAspect {
	@Pointcut("execution(public * *(..))")
    public void anyPublicMethodExecution() {
    };
    
    @Around("anyPublicMethodExecution() && @annotation(loggingMethod)" )
    public void logg_start(ProceedingJoinPoint pjp, LoggingMethod loggingMethod)throws Throwable {
        System.out.println("Profiling start "+loggingMethod.name());
        pjp.proceed();
        System.out.println("Profiling end "+loggingMethod.name());
    }
    
}

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
	@Pointcut("execution(* com.junz.aop..*.*(..))")
	//@Pointcut("within(com.junz.aop..*)")
    public void anyPublicMethodExecution() {
    };
    
    @Around("anyPublicMethodExecution()" )
    public void logg_start(ProceedingJoinPoint pjp)throws Throwable {
        StringBuffer startMessageStringBuffer = new StringBuffer();

        startMessageStringBuffer.append("Profiling start method ");
        startMessageStringBuffer.append(pjp.getSignature().getName());
        startMessageStringBuffer.append("(");

        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++) {
            startMessageStringBuffer.append(args[i]).append(",");
        }
        if (args.length > 0) {
            startMessageStringBuffer.deleteCharAt(startMessageStringBuffer.length() - 1);
        }

        startMessageStringBuffer.append(")");

        System.out.println(startMessageStringBuffer.toString());

        pjp.proceed();
        System.out.println("Profiling end "+ pjp.getSignature().getName());
    }
    
}

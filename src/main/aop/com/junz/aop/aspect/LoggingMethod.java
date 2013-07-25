package com.junz.aop.aspect;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
public @interface LoggingMethod {
	public String name();
}

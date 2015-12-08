package com.annotation.springlock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class NewAspect {
	@Around(value = "execution(* com.annotation.springlock.*.*(..)) ")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		System.out.println(point.getSignature().getName());
		return point.proceed();
	}
}

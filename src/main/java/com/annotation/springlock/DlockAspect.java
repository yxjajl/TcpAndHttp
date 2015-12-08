package com.annotation.springlock;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 完整的注解aop实例
 * 
 * @author shuxiaojun
 *
 */
@Component
@Aspect
public class DlockAspect {

	@Pointcut("execution(* com.annotation.springlock.*.*(..)) && @annotation(com.annotation.springlock.Dlock)")
	private void pointCutMethod() {
	}

	// 声明前置通知
	@Before("pointCutMethod()")
	public void doBefore() {
		System.out.println("前置通知");
	}

	// 声明后置通知
	@AfterReturning(pointcut = "pointCutMethod()", returning = "result")
	public void doAfterReturning(String result) {
		System.out.println("后置通知");
		System.out.println("---" + result + "---");
	}

	// 声明例外通知
	@AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
	public void doAfterThrowing(Exception e) {
		System.out.println("例外通知");
		System.out.println(e.getMessage());
	}

	// 声明最终通知
	@After("pointCutMethod()")
	public void doAfter() {
		System.out.println("最终通知");
	}

	// 声明环绕通知
	@Around("pointCutMethod()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("进入方法---环绕通知");
		Object o = pjp.proceed();
		System.out.println("object name = " + pjp.getTarget().getClass().getSimpleName());
		System.out.println("object name = " + pjp.getSourceLocation());
		System.out.println("object name = " + pjp.getStaticPart().getKind());
		System.out.println("object name = " + pjp.getThis().getClass().getSimpleName());

		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		Dlock dlock = method.getAnnotation(Dlock.class);
		System.out.println("dlock:"+dlock);

		System.out.println("退出方法---环绕通知");
		return o;
	}
}

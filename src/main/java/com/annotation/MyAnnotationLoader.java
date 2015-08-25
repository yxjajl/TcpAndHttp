package com.annotation;

//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
//import org.springframework.stereotype.Component;
//
//import com.dh.handler.activity.ActivityHandler;

public class MyAnnotationLoader {
	public static void main(String[] args) {
		// ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
		// Set<BeanDefinition> beanDefinitions = provider.findCandidateComponents("com/dh/handler");
		// for (BeanDefinition beanDefinition : beanDefinitions) {
		// System.out.println(beanDefinition.getBeanClassName() + "\t" + beanDefinition.getResourceDescription() + "\t" + beanDefinition.getClass());
		// Annotation[] as = beanDefinition.getClass().getAnnotations();
		// for (Annotation a : as) {
		// System.out.println(a.getClass().getSimpleName());
		// }
		// }
		//
		// Annotation[] as = ActivityHandler.class.getAnnotations();
		// System.out.println(ActivityHandler.class.isAnnotationPresent(Component.class));
		// for (Annotation a : as) {
		// System.out.println(a.annotationType().getSimpleName());
		// }
		//
		// Component cc = ActivityHandler.class.getAnnotation(Component.class);
		//
		// System.out.println("d: " + cc.annotationType().getSimpleName());
	}
}
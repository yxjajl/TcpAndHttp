package com.annotation;

@ClassAnnoatation("classannotation")
@MyAnnotation1("this is annotation1")
public class AnnotationDemo {
	@MyAnnotation2(description = "this is sayhello", isAnnotation = true, command = 80001)
	public void sayHello() {
		System.out.println("hello world!");
	}

	@MyAnnotation2(description = "this is say y", isAnnotation = true, command = 80002)
	public void sayy() {
		System.out.println("hello yy");
	}

	public void message() {
		System.out.println("message");
	}
}
package com.annotation;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

public class ClassProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		// HashMap<String, String> map = new HashMap<String, String>();
		for (TypeElement te : annotations) {
			for (Element element : roundEnv.getElementsAnnotatedWith(te)) {
				System.out.println(element.getClass().getClass().getSimpleName());
			}
		}
		return false;
	}

}

package com.reflect;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeanUtils;

public class ReflectMain {
	public static void main(String[] args) {
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(ReflectMain.class);
	}
}

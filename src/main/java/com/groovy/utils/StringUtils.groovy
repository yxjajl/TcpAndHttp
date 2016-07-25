package com.groovy.utils

class StringUtils {
	//创建title函数 (单词首字母大写)
	static def useTitle() {
		String.metaClass.title ={
			def sb = new StringBuffer()
			def first = false;
			delegate.toLowerCase().each {
				if(first) {
					if( Character.isLetter(it as char)) {
						sb << it.toUpperCase();
						first = false
					} else {
						sb << it
					}
				} else {
					if( Character.isLetter(it as char)) {
						sb << it
					} else {
						sb << it
						first = true
					}
				}
			}
			sb.toString()
		}
	}

	//对%重载 实更String.format %s 的效果
	static def useMod() {
		String.metaClass.mod = { List list ->
			String.format(delegate, list as Object[])
		}
	}
}

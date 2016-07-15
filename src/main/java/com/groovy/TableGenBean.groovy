package com.groovy

import groovy.sql.Sql

import java.sql.Types


commonMap = [:]
commonMap.put(Types.BIGINT, long.class.getSimpleName());
commonMap.put(Types.INTEGER, int.class.getSimpleName());
commonMap.put(Types.SMALLINT, int.class.getSimpleName());
commonMap.put(Types.TINYINT, int.class.getSimpleName());
commonMap.put(Types.NUMERIC, int.class.getSimpleName());
commonMap.put(Types.VARCHAR, String.class.getSimpleName());
commonMap.put(Types.CHAR, String.class.getSimpleName());
commonMap.put(Types.DECIMAL, BigDecimal.class.getSimpleName());
commonMap.put(Types.DATE, java.util.Date.class.getSimpleName());
commonMap.put(Types.TIMESTAMP, java.util.Date.class.getSimpleName());
def getTypeString(type) {
	return commonMap[type]
}

def getDB() {
	return DBType.ORACLEL.getInstance("10.140.161.12:1521:orcl","ams","ams");
}


enum DBType {
	MYSQL {
		def getInstance(url,username,password) {
			return Sql.newInstance(
					"jdbc:mysql://"+url, //192.168.16.203:3306/hkjf_dev
					username,
					password,
					"com.mysql.jdbc.Driver");
		}
	}
	,ORACLEL {
		def getInstance(url,username,password) {
			return Sql.newInstance(
					"jdbc:oracle:thin:@"+url, //10.140.161.12:1521:orcl
					username,
					password,
					"oracle.jdbc.driver.OracleDriver");
		}
	}

	abstract getInstance(url,username,password);
}

Sql db= getDB()
def map = [:]
def printColNames = { meta ->

	(1..meta.columnCount).each {
		map[meta.getColumnLabel(it)]=meta.getColumnType(it)
	}
}
def printRow = { row ->
	row.toRowResult().values().each{ print it.toString().padRight(20) }
	println()
}
db.eachRow("select * from ams_user where rownum < 1",printColNames,printRow);

String.metaClass.title ={
	->
	def sb = new StringBuffer()
	def first = true;
	delegate.each {
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

//对String的%重载
String.metaClass.mod = { List list ->
	String.format(delegate, list as Object[])
}

map.each { k,v ->
	def sformat = "private %s %s;"
	println sformat % [getTypeString(v), k.toLowerCase().title().replaceAll("_","")]
}
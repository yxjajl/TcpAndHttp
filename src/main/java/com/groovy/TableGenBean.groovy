package com.groovy

import groovy.sql.Sql

import com.groovy.utils.DBColumnVO
import com.groovy.utils.DBUtils
import com.groovy.utils.StringUtils



def url = "10.142.141.16:1521:orasit01";
def schema = "ECS_XHX_SIT1";
def password = "Ecs0sit123";
Sql db= DBType.ORACLE.getInstance(url,schema,password);


//对String的%重载
StringUtils.useMod()
StringUtils.useTitle()

List<DBColumnVO> list = DBUtils.getColumn(db,schema,"GE_ORDER");

list.each {
	def sformat = "private %s %s;"
	println sformat % [
		DBType.ORACLE.getJavaTypeName(it.columnType),
		it.columnName.title().replaceAll("_","")
	]
}



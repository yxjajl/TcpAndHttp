package com.groovy

import com.groovy.utils.DBColumnVO
import com.groovy.utils.DBUtils
import com.groovy.utils.MapperUtils
import com.groovy.utils.StringUtils
import com.groovy.utils.DBUtils.DBType

import groovy.sql.Sql

//Sql db= Sql.newInstance(
//		"jdbc:mysql://www.db4free.net:3306/yxjtestdb",
//		"abc004",
//		"abc004",
//		"com.mysql.jdbc.Driver");
//	long d1 = System.currentTimeMillis();
//		db.eachRow("select `id`,`name` from ttable ") {
//			println it.name
//		}
//
//println ( (System.currentTimeMillis() - d1))

def url = "10.140.161.12:1521:orcl";//10.140.161.12:1521:orcl
def schema = "epaydev";
def password = "Epaydev123";
Sql db= DBType.ORACLE.getInstance(url,schema,password);


//对String的%重载
StringUtils.useMod()
StringUtils.useTitle()

def tablename = "t_bank_group";
List<DBColumnVO> list = DBUtils.getColumn(db,schema,tablename);

list.each {
	def sformat = "private %s %s; // %s"
	println sformat % [
		DBType.ORACLE.getJavaTypeName(it.columnType),
		it.columnName.toLowerCase(),
		it.comments
	]
}

def vopackage="com.cignacmb.epayment.domain";
def namespace="com.cignacmb.epayment.dao.EpayOrderMapper" 
MapperUtils.gen(namespace,vopackage,tablename,list.collect{k->"${k.columnName.toLowerCase()}"}.join(","))


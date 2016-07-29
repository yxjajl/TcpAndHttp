package com.groovy

import com.groovy.utils.StringUtils

//测试代理服务器是否可用
//File file = new File(/D:\logs\xx.txt/);
//file.eachLine {
//	String[] arr = it.split("	");
//	if(NetUtil.isPortUsing(arr[0],arr[1] as int)){
//		println arr[0, 1] + " true"
//	} 
//}

def insertSql = /insert into wn_user  (userid, username, password, usertype, email, sex, phone, state, createtime, operatorname) values ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s',sysdate, 'admin');/
def insertUserRole=/insert into WN_USER_ROLE values ('%s','8a0c039751859a8d0151c7cd1933016f');/
def list = [
"DIZ0112291",
"DIZ0112291",
"DIZ0112136",
"DIZ0112345",
"DIZ0111996",
"DIZ0111483",
"DIZ0112153",
"DIZ0112303"
]
StringUtils.useMod();
def id= '8a0c03965542c6cb01554353e9860';

def start = 2;
list.each {
	start  ++
	println insertSql % [ id+(""+start).padLeft(3,'0'),it,'96E79218965EB72C92A549DD5A330112','user',it+'@cignacmb.com','M','0755-26090900','1' ]
}

start = 2;
list.each {
	start  ++
	println insertUserRole% [ id+(""+start).padLeft(3,'0')]
}



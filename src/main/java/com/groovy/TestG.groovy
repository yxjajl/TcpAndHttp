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
"DIH0210410",
"DIH0210238",
"DIH0210488",
"DIH0210476",
"DIH0210405",
"DIH0210169",
"DIH0210101",
"DIH0210617",
"DIH0210230",
"DIH0210346",
"DIH0210618",
"DIH0210603",
"DIH0210323",
"DIH0210493",
"DIH0210525",
"DIH0210306",
"DIH0210598",
"DIH0210199",
"DIH0210406",
"DIH0210336",
"DIH0210026",
"DIH0210268",
"DIH0210480",
"DIH0210479",
"DIH0210653",
"DIH0210496",
"DIH0210146",
"DIH0210140",
"DIH0210025",
"DIH0210228",
"DIH0210465",
"DIH0210178",
"DIH0210619",
"DIH0210437",
"DIH0210263",
"DIH0210502",
"DIH0210266",
"DIH0210436",
"DIH0210278",
"DIH0210556",
"DIH0210320",
"DIH0210316",
"DIH0210568",
"DIH0210489",
"DIH0210573",
"DIH0210527",
"DIH0210600",
"DIH0210514",
"DIH0210648",
"DIH0210580",
"DIH0210528",
"DIH0210601",
"DIH0210322",
"DIH0210610",
"DIH0210630",
"DIH0210388",
"DIH0210206",
"DIH0210468",
"DIH0210685",
"DIH0210686",
"DIH0210687",
"DIH0210688",
"DIH0210689",
"DIH0210690",
"DIH0210691",
"DIH0210692",
"DIH0210693",
"DIH0210695",
"DIH0210696",
"DIZ0111618",
"DIZ0111996",
"DIZ0112136",
"DIZ0111785",
"DIZ0112291",
"DIZ0112291",
"DIZ0112345",
"DIZ0111483",
"DIZ0112153",
"DIZ0112303",
"DIZ0112207",
"DIZ0110669",
"DIZ0112029",
"DIZ0112318"

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



package com.groovy

import groovy.sql.Sql;

class DBDemo {
	static main(args) {
		test2();
	}

	static def test2() {
		def db= Sql.newInstance(
				"jdbc:oracle:thin:@10.140.161.12:1521:orcl",
				"ams",
				"ams",
				"oracle.jdbc.driver.OracleDriver");
		def openid = 'oKccnuBlFWPkPHqkqFpfBKaIKma4'
		def firstrow =db.firstRow("select * from ams_user where openid = ?",[openid]);
		//		def firstrow = db.firstRow("select * from ams_user where openid = '$openid'");
		println firstrow.userid

		//		db.query("select * from ams_user where rownum <=5",{
		//				while(it.next()){println it.getString("userid")}
		//		});


		//		db.eachRow("select * from ams_user where rownum <=5") {
		//			println it.userid
		//		}

		// dataset没测通暂时不要用
		//		def dso= db.dataSet('AMS_USER')
		//		def myrow = dso.findAll {it.openid=='oKccnuBlFWPkPHqkqFpfBKaIKma4'}
		//		println myrow.sql
		//myrow.each {print "$it.userid"}
	}

	static def test1() {
		def orderCode="160602000000105498424292";
		def db= Sql.newInstance(
				"jdbc:oracle:thin:@10.140.161.12:1521:orcl",
				"ECSAPP2",
				"ECSAPP2",
				"oracle.jdbc.driver.OracleDriver");
		def firstRow=db.firstRow ("select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') as tt from dual")
		println firstRow.tt;
		def orders=db.query ("select orderno,orderCode from ge_order where orderCode='${orderCode}'",{
			while(it.next()){
				println "orderno is "+it.getString("orderno");
				println "orderCode is "+it.getString("orderCode");
			}
		});
		println("====end=====")

		def printColNames = { meta ->
			(1..meta.columnCount).each {
				print meta.getColumnLabel(it).padRight(20)
			}
			println()
		}
		def printRow = { row ->
			row.toRowResult().values().each{ print it.toString().padRight(20) }
			println()
		}
		db.eachRow("select * from ge_order where orderCode = ? and  rownum <= ? ", ['160613000000151256627976', 2],printColNames, printRow)
	}
}

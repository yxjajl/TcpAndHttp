package com.groovy

import groovy.sql.Sql

def srcdb  = Sql.newInstance("jdbc:oracle:thin:@10.140.161.12:1521:orcl","ams","ams","oracle.jdbc.driver.OracleDriver");
def targetdb= Sql.newInstance("jdbc:oracle:thin:@10.140.161.12:1521:orcl","ams","ams","oracle.jdbc.driver.OracleDriver");

def cc(srd,tdb,srcTable,targeTable,columns) {
	def strColumn ;
	if(columns==null){
		strColumn = getColumn(srd,srcTable).keySet().join(",");
	} else {
		strColumn = columns;
	}
	def querySql = "select $strColumn from "+srcTable
	String[] arrColumn= strColumn.split(",");
	int nColumns= arrColumn.length;
	srd.query (querySql.toString(),{rs->
		while(rs.next()){
			def objList= [];
			def insertWen=[]
			def updateWen=[]
			for(t in 1..nColumns)  {
				objList << rs.getObject(t)
				insertWen << "?"
				updateWen << arrColumn[t-1] + "=?"
			}
			//println insertWen
			//println updateWen

			//insert
			tdb.executeInsert("insert into "+targeTable+"  values ("+insertWen.join(",")+")",objList)

			//update
			//			def strWhere = " where x=?"
			//			objList.getObject("");
			//			tdb.executeUpdate("update "+targeTable+"  set "+updateWen.join(",")+"",objList)
		}
	});
}


cc(srcdb,targetdb,"product","product_back",null);







/*----------------以下是公共方法-----------------*/




def getColumn(db,tableName) {
	def map = [:]
	def printColNames = { meta ->

		(1..meta.columnCount).each {
			map[meta.getColumnLabel(it)]=meta.getColumnType(it)
		}
	}
	def printRow = { row -> print }
	db.eachRow("select * from "+tableName+" where rownum < 1",printColNames,printRow);
	return map
}

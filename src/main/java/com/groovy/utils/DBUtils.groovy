package com.groovy.utils

import groovy.sql.Sql

import java.sql.ResultSet
import java.sql.Types

class DBUtils {
	static def commonMap = [:]
	static {
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
	}
	static def getTypeString(type) {
		return commonMap[type]
	}

	enum DBType {
		MYSQL {
			def getJavaTypeName(type) {
				return "";
			}
			def getInstance(url,username,password) {
				def sql= Sql.newInstance(
						"jdbc:mysql://"+url, //192.168.16.203:3306/hkjf_dev
						username,
						password,
						"com.mysql.jdbc.Driver");
				sql.getConnection().putAt("remarksReporting","true");
				return sql;
			}
		}
		,ORACLE {
			def getJavaTypeName(type) {
				return commonMap[2]
			}
			def getInstance(url,username,password) {
				def sql = Sql.newInstance(
						"jdbc:oracle:thin:@"+url, //10.140.161.12:1521:orcl
						username,
						password,
						"oracle.jdbc.driver.OracleDriver");
				sql.getConnection().putAt("remarksReporting","true");//oracle需要这个才能取到remark
				return sql;
			}
		}

		abstract getInstance(url,username,password);
		abstract getJavaTypeName(type);
	}

	static List<DBColumnVO> getColumn(sql,schema,table) {
		ResultSet rs = sql.getConnection().getMetaData().getColumns(null,schema,table,"%");
		List<DBColumnVO> list= []
		while (rs.next()) {
			DBColumnVO dbColumnVO = new DBColumnVO();
			dbColumnVO.comments = rs.getString("REMARKS");

			dbColumnVO.columnName = rs.getString("COLUMN_NAME");
			dbColumnVO.columnType = rs.getInt("DATA_TYPE");
			dbColumnVO.columnTypeName = rs.getString("TYPE_NAME");
			// COLUMN_SIZE
			//dbColumnVO.javaName = getJavaName(dbColumnVO.columnName);
			//dbColumnVO.javaTypeName = getJavaTypeName(dbColumnVO.columnType, dbColumnVO.columnTypeName);
			list.add(dbColumnVO);
			//println dbColumnVO.columnName+","+dbColumnVO.comments
		}

		return list;
	}
}

class DBColumnVO {
	def columnName;
	def columnType;
	def columnTypeName;
	def javaTypeName;
	def javaName;
	def comments = "";
}

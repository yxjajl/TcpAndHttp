package com.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

public class TableToBean {

	public static void main(String[] args) throws Exception {

		readTable("t_invest_record");
	}

	public static void readTable(String tableName) throws Exception {
		DBHelper dbHelper = DBHelper.getInstance();

		dbHelper.getTableInfo(null, tableName);
	}

}

class DBHelper {
	private final static HashMap<Integer, String> commonMap = new HashMap<Integer, String>();
	private static final String url = "jdbc:mysql://192.168.16.203:3306/hkjf_dev";
	private static final String name = "com.mysql.jdbc.Driver";
	private static final String user = "admin";
	private static final String password = "password";

	private Connection conn = null;
	private PreparedStatement pst = null;

	private static DBHelper INSTANCE = new DBHelper();

	public static DBHelper getInstance() {
		return INSTANCE;
	}

	private DBHelper() {
		try {
			init();
			Class.forName(name);// 指定连接类型
			conn = DriverManager.getConnection(url, user, password);// 获取连接
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getTableInfo(String schema, String tableName) throws Exception {
		DatabaseMetaData dbmd = conn.getMetaData();
		ResultSet rs = dbmd.getColumns(null, schema, tableName, "%");
		int count = rs.getMetaData().getColumnCount();
		for (int i = 1; i < count; i++) {
			System.out.println(rs.getMetaData().getColumnLabel(i));
		}
		System.out.println("-------------------------------------------------");
		while (rs.next()) {
			System.out.println(getBeanDefine(rs.getString("COLUMN_NAME"), rs.getInt("DATA_TYPE"), rs.getString("REMARKS")));
		}
	}

	public static final String strFormat = "private %s %s; // %s";

	public static String getBeanDefine(String columnName, int columnType, String remark) throws Exception {
		return String.format(strFormat, DBHelper.columnTypeToBeanType(columnType), columnName, remark);
	}

	public ResultSet exeSql(String sql) throws Exception {
		pst = conn.prepareStatement(sql);// 准备执行语句
		return pst.executeQuery();
	}

	public void close() {
		try {
			this.conn.close();
			this.pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String columnTypeToBeanType(int columnType) throws Exception {
		String result = commonMap.get(columnType);
		if (result == null)
			throw new Exception("not mitch type :" + columnType);

		return result;
	}

	public static void init() {
		commonMap.clear();
		commonMap.put(Types.BIGINT, long.class.getSimpleName());
		commonMap.put(Types.INTEGER, int.class.getSimpleName());
		commonMap.put(Types.SMALLINT, int.class.getSimpleName());
		commonMap.put(Types.TINYINT, int.class.getSimpleName());

		commonMap.put(Types.VARCHAR, String.class.getSimpleName());
		commonMap.put(Types.CHAR, String.class.getSimpleName());

		commonMap.put(Types.DECIMAL, BigDecimal.class.getSimpleName());

		commonMap.put(Types.DATE, java.util.Date.class.getSimpleName());
		commonMap.put(Types.TIMESTAMP, java.util.Date.class.getSimpleName());

	}
}

package com.hbase;

import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.HBaseConfiguration;
//import org.apache.hadoop.hbase.client.Get;
//import org.apache.hadoop.hbase.client.HBaseAdmin;
//import org.apache.hadoop.hbase.client.HTable;
//import org.apache.hadoop.hbase.client.Result;
//import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseUtil {
	public static void main(String[] args) throws Exception {
		try {
			testHbase();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testHbase() throws Exception {
		System.setProperty("hadoop.home.dir", "F:\\hadoop-common-2.2.0-bin-master\\");
		Configuration config = HBaseConfiguration.create();
		config.set("hbase.zookeeper.property.clientPort", "2181");
		config.set("hbase.zookeeper.quorum", "main.dianziq.com");
		config.set("hbase.master", "main.dianziq.com:60000");
		System.out.println("=table=");

		// HBaseAdmin hbaseAdmin = new HBaseAdmin(config);
		// System.out.println("avi: "+hbaseAdmin.isTableAvailable("user"));
		// Instantiating HTable class
		HTable table = new HTable(config, Bytes.toBytes("user"));
		System.out.println("=Get=");
		// Instantiating Get class
		Get g = new Get(Bytes.toBytes("1"));

		
	
		// Reading the data
		Result result = table.get(g);

		byte[] value = result.getValue(Bytes.toBytes("a"), Bytes.toBytes("ansc"));

		byte[] value1 = result.getValue(Bytes.toBytes("a"), Bytes.toBytes("city"));

		// Printing the values
		String name = Bytes.toString(value);
		String city = Bytes.toString(value1);
		System.out.println("name: " + name + " city: " + city);

		table.close();
	}
}

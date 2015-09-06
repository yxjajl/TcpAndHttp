package com.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseUtil {
	public static void main(String[] args) throws Exception {
		try {
			testHbase();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void testHbase() throws Exception {
		Configuration config = HBaseConfiguration.create();
		config.set("hbase.zookeeper.property.clientPort", "2181");
		config.set("hbase.zookeeper.quorum", "10.10.11.1");
		
		// config.set("hbase.master", "10.10.11.1:60000");
		System.out.println("=table=");
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

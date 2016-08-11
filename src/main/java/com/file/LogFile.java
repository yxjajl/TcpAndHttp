package com.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class LogFile {

	public static final String[] fileName = { //
			"D:\\logs\\m.cignacmb.com.error.log_20160808\\m.cignacmb.com.error.log",
			"D:\\logs\\www.cignacmb.com.access.20160808.log\\www.cignacmb.com.access.20160807.log",
			"D:\\logs\\www.cignacmb.com.access.20160808.log\\www.cignacmb.com.access.log",
			"D:\\logs\\www.cignacmb.com.error.20160807.log\\www.cignacmb.com.error.20160807.log",
			"D:\\logs\\www.cignacmb.com.error.20160807.log\\www.cignacmb.com.error.log",
			"D:\\logs\\www.cignacmb.com.error.log_20160808\\www.cignacmb.com.error.log"

	};

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < fileName.length; i++)
			readFile(fileName[i]);
	}

	public static void readFile(String path) throws Exception {
		FileReader fi = null;
		BufferedReader bs = null;
		try {
			fi = new FileReader(path);
			bs = new BufferedReader(fi);
			int n = 0;
			while (true) {
				String line = bs.readLine();
				if (line == null) {
					break;
				}
				
				if (line.indexOf("/drupal") > -1) {
					//System.out.println(line);
				} else {
					continue;
				}
				
				if (n < 100) {
					System.out.println(line);
					n++;
				} else {
					Scanner input = new Scanner(System.in);
					input.nextLine();
					n = 0;
				}
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fi.close();
			bs.close();
		}

	}

}

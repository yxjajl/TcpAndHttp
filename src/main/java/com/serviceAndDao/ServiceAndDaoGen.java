package com.serviceAndDao;

import java.io.BufferedReader;
import java.io.FileReader;

public class ServiceAndDaoGen {
	public static String filePath = ServiceAndDaoGen.class.getResource("").getPath().replaceAll("%20", " ");
	// ServiceAndDaoGen.class.getClassLoader().getResource("").getPath().replaceAll("%20",
	// " ");

	private static final String PACKAGE = "@Package";
	private static final String NAME = "@Name";
	private static final String SMALLNAME = "@Small";

	static String strName = "Resume";
	static String strSmallName = "resume";
	static String strPack = "com.dianziq.com" + "." + strName;

	public static void main(String[] args) throws Exception {
		System.out.println();
		readFile(filePath + "ServiceTemp.txt");
	}

	public static String myreplace(String str) {
		String tmp = str;
		tmp = tmp.replace(PACKAGE, strPack);
		tmp = tmp.replace(NAME, strName);
		tmp = tmp.replace(SMALLNAME, strSmallName);
		return tmp;
	}

	public static void readFile(String path) throws Exception {
		FileReader fi = null;
		BufferedReader bs = null;
		try {
			fi = new FileReader(path);
			bs = new BufferedReader(fi);
			while (true) {
				String line = bs.readLine();
				if (line == null) {
					break;
				}
				System.out.println(myreplace(line));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fi.close();
			bs.close();
		}

	}
}

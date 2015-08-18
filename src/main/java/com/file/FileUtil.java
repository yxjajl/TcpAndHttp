package com.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.TreeMap;

public class FileUtil {
	public static TreeMap<String, Integer> map = new TreeMap<String, Integer>();

	public void readFile(String path) throws Exception {
		FileReader fi = null;
		BufferedReader bs = null;
		int n = 0;
		try {
			fi = new FileReader(path);
			bs = new BufferedReader(fi);
			while (true) {
				String line = bs.readLine();

				if (line == null) {
					break;
				}
				String[] tmp = line.split("\t");
				System.out.println(tmp[4]);
				n++;
				if (n >= 100) {
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fi.close();
			bs.close();
		}

	}

	// public void readFile(String path) throws Exception {
	// FileChannel fc = new FileInputStream(path).getChannel();
	// ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
	// int c = -1;
	// int n = 0;
	// while (true) {
	// c = fc.read(byteBuffer);
	// if (c == -1) {
	// break;
	// }
	//
	// byteBuffer.flip();
	// System.out.println(new String(byteBuffer.array()));
	// byteBuffer.clear();
	// n++;
	// if (n > 200) {
	// break;
	// }
	// }
	//
	// }

	static final String INDEXSTR = "/static/facevalue/index.html";
	static final String MARKSTR = "/static/facevalue/maker.html";
	static final String STF = "/handler/savetofile";

	public void readFile2(String path) throws Exception {
		FileReader fi = null;
		BufferedReader bs = null;
		int indexcount = 0;
		int markecount = 0;
		int stf__count = 0;

		try {
			fi = new FileReader(path);
			bs = new BufferedReader(fi);
			while (true) {
				String line = bs.readLine();
				if (line == null) {
					break;
				}

				String[] tmp = line.split("\t");
				line = (tmp[4]);

				if (contain(line, INDEXSTR)) {
					indexcount++;
					String key = catString(line, "html", '&');
					Integer iv = map.get(key);
					if (iv == null) {
						map.put(key, 1);
					} else {
						map.put(key, iv + 1);
					}
				}
				if (contain(line, MARKSTR)) {
					markecount++;
				}

				if (contain(line, STF)) {
					stf__count++;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fi.close();
			bs.close();
		}

		System.out.println("indexcount = " + indexcount);
		System.out.println("markecount = " + markecount);
		System.out.println("stf__count = " + stf__count);
		
		int sum10 = 0;
		int sum100= 0;
		SortMapUtil.sort(map);
		for (Entry<String, Integer> entry : map.entrySet()) {
			
			if(entry.getValue() > 100) {
				sum100 ++;
			} else if(entry.getValue() > 10) {
				sum10 ++;
			}
			
			
			
			if(entry.getValue() > 100)
            System.out.println(entry.getKey() + " " + entry.getValue());  
        }  
		
		System.out.println("sum10  = " + sum10);
		System.out.println("sum100 = " + sum100);

	}

	public static boolean contain(String str, String key) {
		return str.indexOf(key) > -1;
	}

	public static String catString(String str, String start, char end) {
		int ms = str.indexOf(start);
		int me = str.indexOf(end);
		
		
		if(ms > -1 && me == -1) {
			me = str.indexOf(" HTTP");
		}
		if(ms==-1||me==-1) {
			System.out.println(str);
			return "";
		}
		

		return str.substring(ms + start.length(), me);
	}

	public static void main(String[] args) throws Exception {
		new FileUtil().readFile2("F:\\www.dianziq.access.log-20150806");
	}
}

class MyComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		// TODO Auto-generated method stub
		return o1 - o2;
	}

	
}

package com.serviceAndDao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

public class ServiceAndDaoGen {
	public static String filePath = ServiceAndDaoGen.class.getResource("").getPath().replaceAll("%20", " ");
	// ServiceAndDaoGen.class.getClassLoader().getResource("").getPath().replaceAll("%20",
	// " ");

	private static final String PACKAGE = "@Package";
	private static final String NAME = "@Name";
	private static final String SMALLNAME = "@Small";

	private static final String SelectColumns = "@SelectColumns";
	private static final String Table = "@Table";
	private static final String UpdateColumns = "@UpdateColumns";
	private static final String KEYID = "@whereid";
	private static final String VO = "@VO";

	private static final String strName = "Resume";
	private static final String strSmallName = "resume";
	private static final String strPack = "com.dianziq.com";
	
	private static final String strTableName = "t_epay_order";
	private static final String strColumns = "epay_order_id, epay_order_code, appid, external_code, amount, card_holder_name, card_holder_id, time_expire, notify_url, success_url, second_underwriting, order_detail_url, renew_state, product_code, product_name";
	private static final String strKeyId = "epay_order_code";
	private static final String strVO = "com.cignacmb.epayment.domain.EpayOrderVO";
	

	public static void main(String[] args) throws Exception {
		
		splitToList("1,2,3,4,5,,7",',').forEach(System.out::println);

		Map<String, String> param = new HashMap<>();
		param.put(PACKAGE, strPack);
		param.put(NAME, strName);
		param.put(SMALLNAME, strSmallName);
		readFile(filePath + "ServiceTemp.txt", param);

		param = new HashMap<>();
		param.put(PACKAGE, strPack);
		param.put(NAME, strName);
		param.put(SMALLNAME, strSmallName);
		param.put(VO, strVO);
		readFile(filePath + "DaoTemp.txt", param);
		
		
		param = new HashMap<>();
		param.put(PACKAGE, strPack+".dao.");
		param.put(NAME, strName);
		param.put(Table, strTableName);
		param.put(SelectColumns, strKeyId+","+strColumns);
		
		param.put(UpdateColumns, strColumns.replace(",", " = ? ,") + " = ? ");
		
		Joiner joiner = Joiner.on(" AND ").skipNulls();
//		StringBuilder whereid = new StringBuilder();
//		for(String str:Splitter.on(',').trimResults().splitToList(strKeyId)) {
//			joiner.appendTo(whereid,str+" = "+"#{"+str+"}",null);
//		}
		String whereid = joiner.join(
				splitToList(strKeyId,',').stream()
		.map(str->String.format("%s = #{%s}",str,str)).iterator());
		
		
		param.put(KEYID, whereid.toString());
		param.put(VO, strVO);
		
		readFile(filePath + "MapperTemp.txt", param);

	}
	
	public static List<String> splitToList(String str, char ch) {
		String[] arr = str.split("" + ch);
		return Arrays.stream(arr).filter(x -> StringUtils.isNotBlank(x)).collect(Collectors.toList());
	}


	public static String myreplace(String str, Map<String, String> param) {
		String tmp = str;
		for (Entry<String, String> entry : param.entrySet()) {
			tmp = tmp.replace(entry.getKey(), entry.getValue());
		}
		return tmp;
	}

	public static void readFile(String path, Map<String, String> param) throws Exception {
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
				System.out.println(myreplace(line, param));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fi.close();
			bs.close();
		}

	}
}

package com.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 根据经纬度获取城市
 * 
 * @author 根据百度api查城市
 */
public class LocToCityUtil {
	// Geocoding API
	// coordtype bd09ll（默认 百度经纬度坐标）、gcj02ll（国测局经纬度坐标）、wgs84ll（ GPS经纬度）
	private static final String BAIDU_API_URL = "http://api.map.baidu.com/geocoder/v2/?ak=7PnV10uQYDj9n9qhIL9yitXv&location=%s&output=json&pois=1";
	private static final String CHARGESET = "UTF-8";
	private static final String searchCityKey = "city";

	private static LocToCityUtil INSTANCE = new LocToCityUtil();

	private LocToCityUtil() {
	}

	public static LocToCityUtil getInstance() {
		return INSTANCE;
	}

	/**
	 * 根据坐标取城市
	 * 
	 * @param longitude lng<经度>
	 * @param latitude  lat<纬度>
	 * @return
	 */
	public String getCityByCoord(String lat, String lng) {
		return getCityByCoord(lat + "," + lng);
	}
	
	public String getCityByCoord(String locations) {
		String url = String.format(BAIDU_API_URL, locations);
		String returnStr = getResult(url, null, CHARGESET);
		 System.out.println(returnStr);
		String strCity = null;
		try {

			strCity = findByKey(returnStr, searchCityKey);
			strCity = decodeUnicode(strCity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strCity;
	}

	public static String findByKey(String content, String key) throws Exception {
		String value = null;
		int start = content.indexOf(key);
		if (start > -1) {
			int end = content.indexOf(",", start);

			value = content.substring(start + key.length() + 2, end);
			value = value.replace("\"", "");
		}

		return value;
	}

	/**
	 * unicode 转换成 中文
	 * 
	 * @author fanhui 2007-3-15
	 * @param theString
	 * @return
	 */
	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed      encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't') {
						aChar = '\t';
					} else if (aChar == 'r') {
						aChar = '\r';
					} else if (aChar == 'n') {
						aChar = '\n';
					} else if (aChar == 'f') {
						aChar = '\f';
					}
					outBuffer.append(aChar);
				}
			} else {
				outBuffer.append(aChar);
			}
		}
		return outBuffer.toString();
	}

	/**
	 * @param urlStr
	 *            请求的地址
	 * @param content
	 *            请求的参数 格式为：name=xxx&pwd=xxx
	 * @param encoding
	 *            服务器端请求编码。如GBK,UTF-8等
	 * @return
	 */
	private static String getResult(String urlStr, String content, String encoding) {
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();// 新建连接实例
			connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
			connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
			connection.setDoOutput(true);// 是否打开输出流 true|false
			connection.setDoInput(true);// 是否打开输入流true|false
			connection.setRequestMethod("POST");// 提交方法POST|GET
			connection.setUseCaches(false);// 是否缓存true|false
			connection.connect();// 打开连接端口
			if (content != null && content.trim().length() > 0) {
				DataOutputStream out = new DataOutputStream(connection.getOutputStream());// 打开输出流往对端服务器写数据
				out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
				out.flush();// 刷新
				out.close();// 关闭输出流
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
			// ,以BufferedReader流来读取
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();// 关闭连接
			}
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 1; i++) {
			long d1 = System.currentTimeMillis();
			String city = LocToCityUtil.getInstance().getCityByCoord("22.651753,114.023726");
			long d2 = System.currentTimeMillis() - d1;
			System.out.println("use :" + d2 + "," + city);
		}
	}
}
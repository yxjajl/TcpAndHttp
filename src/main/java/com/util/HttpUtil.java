package com.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http
 * 
 * @author RickYu
 */
public class HttpUtil {
	private static final Logger l = LoggerFactory.getLogger(HttpUtil.class);
	private static final int CONNECT_TIMEOUT = 15000;
	private static final int READ_TIMEOUT = 300000;
	private static final String UTF_8 = "utf-8";

	/**
	 * 请求已成功，请求所希望的响应头或数据体将随此响应返回。
	 */
	public static final int OK = 200;

	private static String contentType = "application/x-www-form-urlencoded";

	/**
	 * 检查Url是否可用
	 * 
	 * @param urlStr
	 * @return
	 */
	public static boolean available(String urlStr) {

		if (StringUtils.isBlank(urlStr)) {
			return false;
		}
		try {
			URL url = new URL(urlStr);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(9000);
			if (OK == con.getResponseCode())
				return true;
		} catch (Exception e) {
			l.error(String.format("url:%s; exception:%s", urlStr, e.getMessage()), e);
		}
		return false;
	}

	/**
	 * 向指定URL发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param params
	 *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendPost(String url, String params) {
		DataOutputStream out = null;
		BufferedReader in = null;
		StringBuilder sb = new StringBuilder();
		try {
			byte[] bytes = org.apache.commons.codec.binary.StringUtils.getBytesUtf8(params);
			URL realUrl = new URL(url);
			HttpURLConnection con = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestProperty("charset", UTF_8);
			con.setRequestMethod("POST");
			con.setRequestProperty("accept", "*/*");
			con.setRequestProperty("connection", "Keep-Alive");
			con.setRequestProperty("Content-Type", contentType);
			con.setInstanceFollowRedirects(false);
			con.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
			con.setRequestProperty("Content-Length", "" + Integer.toString(bytes.length));
			con.setUseCaches(false);
			// 发送POST请求必须设置如下两行
			con.setConnectTimeout(CONNECT_TIMEOUT);
			con.setReadTimeout(READ_TIMEOUT);
			// 获取URLConnection对象对应的输出流
			out = new DataOutputStream(con.getOutputStream());
			out.write(bytes);
			out.flush();

			in = new BufferedReader(new InputStreamReader(con.getInputStream(), UTF_8));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append("\n").append(line);
			}

			out.close();
			con.disconnect();
		} catch (Exception e) {
			l.error(e.getMessage(), e);
		} finally {
			close(out);
			close(in);
		}
		return sb.toString().replaceFirst("\n", "");
	}

	/**
	 * 向指定URL发送Get方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param params
	 *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendGet(String url) {
		BufferedReader in = null;
		StringBuilder sb = new StringBuilder();
		try {
			// url = URLEncoder.encode(url, UTF_8);
			URL realUrl = new URL(url);
			HttpURLConnection con = (HttpURLConnection) realUrl.openConnection();
			con.setRequestMethod("GET");
			// 设置通用的请求属性
			con.setRequestProperty("accept", "*/*");
			con.setRequestProperty("connection", "Keep-Alive");
			// con.setRequestProperty("Content-Type",
			// "application/json; charset=" + UTF_8);
			con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			con.setConnectTimeout(CONNECT_TIMEOUT);
			con.setReadTimeout(READ_TIMEOUT);
			con.connect();

			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(con.getInputStream(), UTF_8));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			l.error(e.getMessage(), e);
		} finally {
			close(in);
		}
		return sb.toString().replaceFirst("\n", "");
	}

	public static String sendDelete(String url) {
		BufferedReader in = null;
		StringBuilder sb = new StringBuilder();
		try {
			// url = URLEncoder.encode(url, UTF_8);
			URL realUrl = new URL(url);
			HttpURLConnection con = (HttpURLConnection) realUrl.openConnection();
			con.setRequestMethod("DELETE");
			// 设置通用的请求属性
			con.setRequestProperty("accept", "*/*");
			con.setRequestProperty("connection", "Keep-Alive");
			// con.setRequestProperty("Content-Type",
			// "application/json; charset=" + UTF_8);
			con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			con.setConnectTimeout(CONNECT_TIMEOUT);
			con.setReadTimeout(READ_TIMEOUT);
			con.connect();

			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(con.getInputStream(), UTF_8));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			l.error(e.getMessage(), e);
		} finally {
			close(in);
		}
		return sb.toString().replaceFirst("\n", "");
	}

	public static String post(String url, Map<String, String> params) {
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);// 连接时间
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 15000);// 数据传输时间
		HttpPost post = new HttpPost(url);
		//		 post.setHeader("Content-Type", "application/json");
		StringBuilder sb = new StringBuilder();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		for (Map.Entry<String, String> entry : params.entrySet()) {
			nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		try {
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs, UTF_8));
			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String get(String url) {
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);// 连接时间
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 15000);// 数据传输时间
		HttpGet get = new HttpGet(url);
		// post.setHeader("Content-Type", contentType);
		StringBuilder sb = new StringBuilder();
		// List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		// for (Map.Entry<String, String> entry : params.entrySet()) {
		// nameValuePairs.add(new BasicNameValuePair(entry.getKey(),
		// entry.getValue()));
		// }
		try {
			// get.setEntity(new UrlEncodedFormEntity(nameValuePairs, UTF_8));
			HttpResponse response = client.execute(get);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String put(String url, Map<String, String> params) {
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);// 连接时间
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 15000);// 数据传输时间
		HttpPut put = new HttpPut(url);
		// post.setHeader("Content-Type", contentType);
		StringBuilder sb = new StringBuilder();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		for (Map.Entry<String, String> entry : params.entrySet()) {
			nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		try {
			put.setEntity(new UrlEncodedFormEntity(nameValuePairs, UTF_8));
			HttpResponse response = client.execute(put);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String delete(String url) {
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);// 连接时间
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 15000);// 数据传输时间
		HttpDelete delete = new HttpDelete(url);
		// post.setHeader("Content-Type", contentType);
		StringBuilder sb = new StringBuilder();
		// List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		// for (Map.Entry<String, String> entry : params.entrySet()) {
		// nameValuePairs.add(new BasicNameValuePair(entry.getKey(),
		// entry.getValue()));
		// }
		try {
			// get.setEntity(new UrlEncodedFormEntity(nameValuePairs, UTF_8));
			HttpResponse response = client.execute(delete);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	private static <T> void close(T t) {
		if (null == t)
			return;
		try {
			if (t instanceof DataOutputStream) {
				((DataOutputStream) t).close();
			}

			if (t instanceof BufferedReader) {
				((BufferedReader) t).close();
			}
		} catch (Exception e) {
			l.error(e.getMessage(), e);
		}
	}

	public static void main(String[] args) {
		String param = "username=10xun-fdd&pwd=666666&phone_number=18665334407&neirong=【房多多】验证码：77";
		HttpUtil.sendPost("http://port.joycloud.mobi:81/WebServices/SMS/send_smsserver.ashx", param);
	}
}
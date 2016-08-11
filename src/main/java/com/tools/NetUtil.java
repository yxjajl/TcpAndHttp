package com.tools;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import org.jboss.netty.handler.codec.http.HttpRequest;

//检测端口被占用
public class NetUtil {

	private static final String[] HEADERS_TO_TRY = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED",
			"HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP", "HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR", "X-Real-IP" };

	/***
	 * 获取客户端ip地址(可以穿透代理)
	 *
	 * @param request
	 * @return
	 */
	public String getClientIpAddress(HttpRequest req) {
		for (String header : HEADERS_TO_TRY) {
			String ip = req.getHeader(header);
			if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
//		return getRemoteIP();
		return null;
	}

	/***
	 * true:already in using false:not using
	 * 
	 * @param port
	 */
	public static boolean isLoclePortUsing(int port) {
		boolean flag = true;
		try {
			flag = isPortUsing("127.0.0.1", port);
		} catch (Exception e) {
		}
		return flag;
	}

	/***
	 * true:already in using false:not using
	 * 
	 * @param host
	 * @param port
	 * @throws UnknownHostException
	 */
	public static boolean isPortUsing(String host, int port) throws UnknownHostException {
		boolean flag = false;
		try {
			SocketAddress address = new InetSocketAddress(host, port);
			Socket socket = new Socket();
			socket.connect(address, 500);
			socket.close();
			flag = true;
		} catch (IOException e) {

		}
		return flag;
	}
//	public static boolean isPortUsing(String host, int port) throws UnknownHostException {
//		boolean flag = false;
//		InetAddress theAddress = InetAddress.getByName(host);
//		try {
//			Socket socket = new Socket(theAddress, port);
//			socket.close();
//			flag = true;
//		} catch (IOException e) {
//
//		}
//		return flag;
//	}

	public static void main(String[] args) throws Exception {
		System.out.println(NetUtil.isPortUsing("123.57.190.51", 7777));
	}
}
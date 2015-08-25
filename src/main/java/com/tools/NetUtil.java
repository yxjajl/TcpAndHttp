package com.tools;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

//检测端口被占用
public class NetUtil {

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
		InetAddress theAddress = InetAddress.getByName(host);
		try {
			Socket socket = new Socket(theAddress, port);
			socket.close();
			flag = true;
		} catch (IOException e) {

		}
		return flag;
	}

	public static void main(String[] args) {
		System.out.println(NetUtil.isLoclePortUsing(843));
	}
}
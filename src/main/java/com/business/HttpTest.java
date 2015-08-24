package com.business;

import java.util.HashMap;
import java.util.Map;

import com.constants.Cmd;
import com.util.HttpUtil;

public class HttpTest {
	// private static final Logger log =
//	 LoggerFactory.getLogger(HttpTest.class);
//	private final static String PATH = "http://127.0.0.1:8888/";
	private final static String PATH = "http://10.10.11.80:8888/";
	
	public static void main(String[] args) {
		// testUserLogin();
		 testUserInfo();
		// testUserFriend();

		// testGroupUsers();

//		testMessageSend();
	}

	// 登陆
	public static void testUserLogin() {
		String url = PATH + Cmd.USER_LOGIN;
		Map<String, String> postParamMap = new HashMap<String, String>();
		postParamMap.put("account", "wilkes@dianziq.com");
		postParamMap.put("pwd", "wilkes1122");

		String result = HttpUtil.post(url, postParamMap);
		println(result);
	}

	// 用户信息
	public static void testUserInfo() {
		String url = PATH + Cmd.USER_INFO;
		Map<String, String> postParamMap = new HashMap<String, String>();
		postParamMap.put("uid", "1000");
		postParamMap.put("lid", "1436406039909-f62c93af9197b0be48792be231071252");
		postParamMap.put("quid", "1956853");

		String result = HttpUtil.post(url, postParamMap);
		println(result);
	}

	// 好友列表
	public static void testUserFriend() {
		String url = PATH + Cmd.USER_FRIENDS;
		Map<String, String> postParamMap = new HashMap<String, String>();
		postParamMap.put("uid", "1000");
		postParamMap.put("lid", "1436406039909-f62c93af9197b0be48792be231071252");
		postParamMap.put("pageIndex", "0");
		postParamMap.put("quid", "0");
		postParamMap.put("pageSize", "10");

		String result = HttpUtil.post(url, postParamMap);
		println(result);
	}

	// 群用户
	public static void testGroupUsers() {
		String url = PATH + Cmd.GROUP_USERS;
		Map<String, String> postParamMap = new HashMap<String, String>();
		postParamMap.put("gid", "10023");
		postParamMap.put("uid", "1000");
		postParamMap.put("lid", "1436406039909-f62c93af9197b0be48792be231071252");
		postParamMap.put("pageIndex", "0");

		String result = HttpUtil.post(url, postParamMap);
		println(result);
	}

	// 发送消息
	public static void testMessageSend() {
		String url = PATH + Cmd.MESSAGE_SEND;
		Map<String, String> postParamMap = new HashMap<String, String>();
		postParamMap.put("tuid", "999");
		postParamMap.put("msg", "你好999");
		postParamMap.put("uid", "1000");
		postParamMap.put("lid", "1436406039909-f62c93af9197b0be48792be231071252");
		// postParamMap.put("ctype", "0");
		// postParamMap.put("isResend", "0");
		// postParamMap.put("stime", "0");

		String result = HttpUtil.post(url, postParamMap);
		println(result);

	}

	public static void println(Object obj) {
		System.out.println(obj);
	}
}

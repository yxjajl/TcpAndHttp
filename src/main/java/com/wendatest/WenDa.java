package com.wendatest;

import java.util.HashMap;
import java.util.Map;

import com.util.HttpUtil;

public class WenDa {
	public static final String PATH= "http://localhost:8080/wenda";
	
	public static void main(String[] args) {
		// testUserLogin();
		// testUserInfo();
		// testUserFriend();

		// testGroupUsers();

		testRegister();
//		testEditUserInfo();
	}

	public static void testRegister() {
		String url = PATH + "/m/register.action";
		Map<String, String> postParamMap = new HashMap<String, String>();
		postParamMap.put("account", "ytest3@163.com");
		postParamMap.put("pwd", "123456");
		postParamMap.put("name", "rick3");
		postParamMap.put("industry", "互联网");
		postParamMap.put("job_category", "工程师g");
//		postParamMap.put("skill_tag", "1");
//		postParamMap.put("one_line", "2");
//		postParamMap.put("work_exp", "3");

		String result = HttpUtil.post(url, postParamMap);
		System.out.println(result);
	}
	
	public static void testEditUserInfo() {
		String url = PATH + "/m/user/profile!edit.action";
		
		Map<String, String> postParamMap = new HashMap<String, String>();
		postParamMap.put("profileKey", "work_exp");
		postParamMap.put("profileValue", "3");
//		postParamMap.put("name", "rick2");
//		postParamMap.put("jobnature", "工程师");
//		postParamMap.put("skill_tag", "1");
//		postParamMap.put("one_line", "2");
//		postParamMap.put("work_exp", "3");

		String result = HttpUtil.post(url, postParamMap);
		System.out.println(result);
	}
}

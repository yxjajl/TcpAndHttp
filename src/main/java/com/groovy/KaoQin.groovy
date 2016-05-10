package com.groovy

import com.util.HttpUtil

class KaoQin {
	static main(args) {
		def map =[
			"user":"SZK160048","password":"Aa123456"
		];

		String url = "http://10.140.2.55:7021/login.aspx?action=login";

		String str = HttpUtil.post(url, map);
		println(str);
	}
}

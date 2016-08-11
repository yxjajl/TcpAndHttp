package com.groovy

//ams生成连接地址测试类
class AmsAddressTest {
	static main(args) {
		//def map= ["mobile":"17727887270","imgValidateCode":"1111"]
		//println HttpUtil.post("http://localhost:8085/wechat/sendsms",map);


		wxtest()
	}

	static def wxtest() {
		//def env= 'uat'
		def env= 'prd'

		def wxg = env=='uat'?"http://test.cignacmb.com/wxweb":"http://wx.cignacmb.com/wxweb";
		//def ams = env=='uat'?"http://ams-uat.cignacmb.com/wechat":"http://ams.cignacmb.com/wechat";
		def ams = env=='uat'?"http://xnwx.suedai.com/amsapi":"http://weixin.123baobei.cn/amsapi";

		def wxid = env=='uat'?"wx9de7849179fa4533":"wx8d188615510c9093";


		//def url1 ="http%3a%2f%2ftest.cignacmb.com%2fwxweb%2foauth2fetchcode.xhtml&response_type=code&scope=snsapi_base&state=http%3a%2f%2ftest.cignacmb.com%2fmember%2fm%2fwechat%2fB.xhtml?f=http%3a%2f%2fams-uat.cignacmb.com%2fwechat%2flogin";
		def url2 = wxg+"/oauth2fetchcode.xhtml&response_type=code&scope=snsapi_base&state="+ams+"/newLogin";
		def url3 = wxg+"/oauth2fetchcode.xhtml&response_type=code&scope=snsapi_base&state="+ams+"/goNewRegister";
		def x = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+wxid+"&redirect_uri="

		//def url4 ="http%3A%2F%2Ftest.cignacmb.com%2Fwxweb%2Foauth2fetchcode.xhtml%26response_type%3Dcode%26scope%3Dsnsapi_base%26state%3Dhttp%3A%2F%2F10.141.139.128%3A8085%2Fwechat%2FgoNewRegister&connect_redirect=1&uin=MjM4MzU4MzY0Mw%3D%3D&key=0406033ccdee3321e3c9a98be2a5c5f0284375a5ee1feeef91651fe1ed860da69ba00d6011a02514d4c3a261ab4eb7f0&pass_ticket=ULdW+GM4jqFMG+fYgqvsZ1+IIpfFdDIbU4TmnF/vpCg8KLrtyZikT1cU+7rsueJClBYMuhy3HJX8e1RKhIV+TQ=="
		//println URLDecoder.decode(url1,"utf-8")
		println ((x+URLEncoder.encode(url2,"utf-8")).replaceAll("%26","&").replaceAll("%3D","="))
		println ((x+URLEncoder.encode(url3,"utf-8")).replaceAll("%26","&").replaceAll("%3D","="))
		//println URLDecoder.decode(url4,"utf-8")
		
		println URLDecoder.decode(/http%3A%2F%2Fm.cignacmb.com%2Fdrupal%2F%3Fq%3Dnode%26destination%3Dnode/,"utf-8")

	}
}

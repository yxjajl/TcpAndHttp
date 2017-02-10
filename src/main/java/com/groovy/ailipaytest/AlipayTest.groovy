package com.groovy.ailipaytest

import com.util.HttpUtil

class AlipayTest {

	static main(args) {
		def s=new StringWriter()
		def builder = new groovy.xml.MarkupBuilder(s)
		builder.'notify' {
			'payment_type' '1'
			'subject' '保险' //商品名称
			'trade_no' '1111' //支付宝交易的号
			'buyer_email' '1111@mail.com' //买家支付宝帐号(邮件或手机号)
			'gmt_create' new Date().format('yyyy-MM-dd HH:mm:ss') //
			'notify_type' 'trade_status_sync'
			'quantity' '1'
			'out_trade_no' 'ALTEST000000001' //商户交易号
			'notify_time' new Date().format('yyyy-MM-dd HH:mm:ss')
			'seller_id' '22222222' //卖家支付宝用户号

			'trade_status' 'TRADE_FINISHED'
			'is_total_fee_adjust' 'N'
			'total_fee' '1.0'
			'gmt_payment' new Date().format('yyyy-MM-dd HH:mm:ss')
			'seller_email' '2222@mail.com'
			'gmt_close' new Date().format('yyyy-MM-dd HH:mm:ss')
			'price' '1.0'
			'buyer_id' '1111'
			'notify_id' 'TTTTTTZZZZZZZ'
		}
		println s.toString()


		def map =[
			"service":"alipay.wap.trade.create.direct",
			"v":"1.0",
			"sec_id":"MD5",
			"sign":"xxxxxxxxxx",
			"notify_data":s.toString()
		];

		String url = "http://localhost:8080/payment/aliPayNotify";

		String str = HttpUtil.post(url, map);
		//		println(str);
	}
}

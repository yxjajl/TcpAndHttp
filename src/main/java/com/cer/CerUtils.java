package com.cer;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

//KCS 全称是 Public-Key Cryptography Standards ，是由 RSA 实验室与其它安全系统开发商为促进公钥密码的发展而制订的一系列标准，PKCS 目前共发布过 15 个标准。 常用的有：
//PKCS#7 Cryptographic Message Syntax Standard
//PKCS#10 Certification Request Standard
//PKCS#12 Personal Information Exchange Syntax Standard
//X.509是常见通用的证书格式。所有的证书都符合为Public Key Infrastructure (PKI) 制定的 ITU-T X509 国际标准。
//PKCS#7 常用的后缀是： .P7B .P7C .SPC
//PKCS#12 常用的后缀有： .P12 .PFX
//X.509 DER 编码(ASCII)的后缀是： .DER .CER .CRT
//X.509 PAM 编码(Base64)的后缀是： .PEM .CER .CRT
//.cer/.crt是用于存放证书，它是2进制形式存放的，不含私钥。
//.pem跟crt/cer的区别是它以Ascii来表示。
//pfx/p12用于存放个人证书/私钥，他通常包含保护密码，2进制方式
//p10是证书请求
//p7r是CA对证书请求的回复，只用于导入
//p7b以树状展示证书链(certificate chain)，同时也支持单个证书，不含私钥。
public class CerUtils {

	public static void main(String[] args) throws Exception {
		FileInputStream in = new FileInputStream("C:\\Users\\r6yuxx\\Desktop\\cerdemo\\99bill-rsa.pfx");
		KeyStore keyStore = KeyStore.getInstance("pkcs12");
		keyStore.load(in, "Aa123456".toCharArray());
		//keyStore.aliases()
		PrivateKey pk = (PrivateKey) keyStore.getKey("alias", "Aa123456".toCharArray());
	}

	public static void main2(String args[]) throws Exception {

		// 获取X509Certificate的对象
		// 从命令行中读入需要验证的证书文件
		CertificateFactory of = CertificateFactory.getInstance("x.509");
		FileInputStream in = new FileInputStream("C:\\Users\\r6yuxx\\Desktop\\cerdemo\\81231006300090790.cer");
		java.security.cert.Certificate ceof = of.generateCertificate(in);
		System.out.println(ceof.getPublicKey().getEncoded() + "\t" + ceof.getEncoded());
		in.close();
		X509Certificate t = (X509Certificate) ceof;
		System.out.println("开始：" + DateFormatUtils.format(t.getNotAfter(), "yyyy-MM-dd"));
		System.out.println("结束:" + DateFormatUtils.format(t.getNotBefore(), "yyyy-MM-dd"));

		// 获取日期
		// 验证证书在某个日期是否有效，从命令行读入年月日，由此生成Date()对象。有Date类的很多设置年月日的方法已经不提倡使用，因此改用Calendar类，Calendar类也是一个工厂类，通过getInstance()方法获得对象，然后使用set()方法设置时间，最后通过其getTime()方法获得Date()对象由于Calendar类的set()方法参数是整数，因此对命令行参数读入的年月日字符串使用Integer.parseInt()方法转换为整数。由于Calendar类的set()方法设置月份时从0开始，0代表1月，11代表12月，因此命令读入的月份要减去1
		Calendar cld = Calendar.getInstance();
		int year = Integer.parseInt("2012");
		int month = Integer.parseInt("9");
		int day = Integer.parseInt("24");
		cld.set(year, month, day);
		Date d = cld.getTime();
		System.out.println(d);
		try {
			// 检验证书
			t.checkValidity(d);

			System.out.print("OK");
		}

		// 处理CertificateExpiredException异常
		// 若证书在指定日期已经过期，则产生CertificateExpiredException异常，在cath语句中作相关处理
		catch (CertificateExpiredException e) {
			System.out.println("expired");
			System.out.println(e.getMessage());
		}

		// 处理CertificateNorYetValidException异常
		// 若证书在指定日期尚未生效，则产生CertificateNorYetValidException异常，在cath语句中作相关处理
		catch (CertificateNotYetValidException e) {
			System.out.println("Too early");
			System.out.println(e.getMessage());
		}
	}

}

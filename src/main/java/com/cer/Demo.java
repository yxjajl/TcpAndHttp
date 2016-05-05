package com.cer;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.crypto.Cipher;

public class Demo {
	public static void main(String[] args) throws Exception {
		/*
		 * 前提：JDK已安装且正确配置环境变量 首先在D盘建立目录 test，用来存放证书库以及导出的证书文件，然后在命令行执行下列2句 下句含义：在D盘目录创建 test 密钥库，库密码 123456789 ，创建证书
		 * test ：非对称密钥，RSA 算法，key密码为qwertyuiop ，存于 test.keystore C:\Users\Administrator> keytool -genkey -alias test
		 * -dname "CN=127.0.0.1" -keyalg RSA -keystore test -storepass 123456789 -keypass qwertyuiop 下句含义：将 test 库中的
		 * test 导出为证书文件 test.cer ，这里可能需要将 export C:\Users\Administrator> keytool -export -alias test -file test.cer
		 * -keystore test.keystore -storepass 123456789 证书库证书保存证书的公私钥，导出的证书文件只携带公钥
		 */
		byte[] msg = "犯大汉天威者，虽远必诛！".getBytes("UTF8"); // 待加解密的消息

		// 用证书的公钥加密
		CertificateFactory cff = CertificateFactory.getInstance("X.509");
		FileInputStream fis1 = new FileInputStream("d://test.cer"); // 证书文件
		Certificate cf = cff.generateCertificate(fis1);
		PublicKey pk1 = cf.getPublicKey(); // 得到证书文件携带的公钥
		Cipher c1 = Cipher.getInstance("RSA/ECB/PKCS1Padding"); // 定义算法：RSA
		c1.init(Cipher.ENCRYPT_MODE, pk1);
		byte[] msg1 = c1.doFinal(msg); // 加密后的数据

		// 用证书的私钥解密 - 该私钥存在生成该证书的密钥库中
		FileInputStream fis2 = new FileInputStream("d://test.keystore");
		KeyStore ks = KeyStore.getInstance("JKS"); // 加载证书库
		// 输入keystore密码就是证书的证书库密码,主密码就是证书密码
		char[] kspwd = "123456789".toCharArray(); // 证书库密码
		char[] keypwd = "qwertyuiop".toCharArray(); // 证书密码
		ks.load(fis2, kspwd); // 加载证书
		PrivateKey pk2 = (PrivateKey) ks.getKey("test", keypwd); // 获取证书私钥
		fis2.close();
		Cipher c2 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		c2.init(Cipher.DECRYPT_MODE, pk2);
		byte[] msg2 = c2.doFinal(msg1); // 解密后的数据

		// 打印解密字符串 - 应显示 犯大汉天威者，虽远必诛！
		System.out.println(new String(msg2, "UTF8")); // 将解密数据转为字符串

	}
}

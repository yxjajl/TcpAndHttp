package com.utf8;

import java.io.UnsupportedEncodingException;

public class ConverFromGBKToUTF8 {
	public static void main(String[] args) {

		try {

			ConverFromGBKToUTF8 convert = new ConverFromGBKToUTF8();
			byte[] fullByte = convert.getUTF8BytesFromGBKString("中 a文");
			String fullStr = new String(fullByte, "UTF-8");
			System.out.println("string from GBK to UTF-8 byte:  " + fullStr);
			testUtf8();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testUtf8() throws UnsupportedEncodingException {
//		Byte 1		 Byte 2		  Byte3
//		0xxxxxxx		                     //单字节字符 1个字节的UTF-8十六进制编码一定是以比8小的数字开头的
//		110xxxxx	10xx xxxx	             //双字节字符 2个字节的UTF-8十六进制编码一定是以C或D开头的
//		1110xxxx	10xx xxxx	10xx xxxx    //三字节字符 3个字节的UTF-8十六进制编码一定是以E开头的
		// utf-8\/u4E00-\/u9FFF，这个包含了常用的汉字、日文和韩文
		// GBK 8140至FEFE
		String str = "a";
		byte[] tmp = str.getBytes();
		for (int i = 0; i < tmp.length; i++) {
			System.out.println(Integer.toHexString(tmp[i]));
		}

		String strHex = "E5BE88";
		tmp = new byte[2];
		tmp[0] = (byte) (0xff & Integer.valueOf("C2", 16));
		tmp[1] = (byte) (0xff & Integer.valueOf("A2", 16));
//		tmp[2] = (byte) (0xff & Integer.valueOf("AD", 16));

		System.out.println(new String(tmp, "UTF-8"));
	}

	public static byte[] getUTF8BytesFromGBKString(String gbkStr) {
		int n = gbkStr.length();
		byte[] utfBytes = new byte[3 * n];
		int k = 0;
		for (int i = 0; i < n; i++) {
			int m = gbkStr.charAt(i);
			if (m < 128 && m >= 0) {
				utfBytes[k++] = (byte) m;
				continue;
			}
			// 第一个这字放4位，第二个字节放6位，第三个字节放6位
			utfBytes[k++] = (byte) (0xe0 | (m >> 12));
			utfBytes[k++] = (byte) (0x80 | ((m >> 6) & 0x3f));
			utfBytes[k++] = (byte) (0x80 | (m & 0x3f));
		}

		// 当gakStr中有单字节字符时，utfBytes的长度就错了，需要截断
		if (k < utfBytes.length) {
			byte[] tmp = new byte[k];
			System.arraycopy(utfBytes, 0, tmp, 0, k);
			return tmp;
		}
		return utfBytes;
	}

}
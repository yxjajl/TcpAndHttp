package com.utf8;

public class Utf8Main {

	public static void main(String[] args) {
		String str = "111001011001101110111101";
		byte[] temp = new byte[str.length()];
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			byte b = Integer.valueOf("" + ch, 2).byteValue();
			temp[i] = b;
		}
		System.out.println(Integer.valueOf("111001011001101110111101", 2));

		for (byte bb : temp) {
			System.out.println(Integer.toHexString(bb & 0xFF));
		}

		String ss = "国";

		byte[] dd = ss.getBytes();

		for (byte bb : dd) {
			System.out.println(Integer.toHexString(bb & 0xFF));
		}

		System.out.println(binaryString2hexString("01100000"));
	}

	public static int binaryString2hexString(String bString) {
		if (bString == null || bString.equals("") || bString.length() % 8 != 0)
			return 0;
		StringBuffer tmp = new StringBuffer();
		int iTmp = 0;
		for (int i = 0; i < bString.length(); i += 4) {
			iTmp = 0;
			for (int j = 0; j < 4; j++) {
				iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);
			}
			tmp.append(Integer.toHexString(iTmp));
		}
		return Integer.valueOf(tmp.toString(), 16);
	}

	public static String binaryString2hexString2(String bString) {
		if (bString == null || bString.equals("") || bString.length() % 8 != 0)
			return null;
		StringBuffer tmp = new StringBuffer();
		int iTmp = 0;
		for (int i = 0; i < bString.length(); i += 4) {
			iTmp = 0;
			for (int j = 0; j < 4; j++) {
				iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);
			}
			tmp.append(Integer.toHexString(iTmp));
		}
		return tmp.toString();
	}

}

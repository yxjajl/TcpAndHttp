package com.utf8;

public class GbkAndUtf8 {
	public static void main(String[] args) {
		String str = "符医存";

		byte[] bb = str.getBytes();
		int i = 0;
		for (byte b : bb) {
			System.out.println("sou[" + i + "]=0x" + Integer.toHexString(b) + ";");
			i++;
		}

	}

	public static byte[] getData() {
		byte[] sou = new byte[9];
		sou[0] = 0xffffffe7;
		sou[1] = 0xffffffac;
		sou[2] = 0xffffffa6;
		sou[3] = 0xffffffe5;
		sou[4] = 0xffffff8c;
		sou[5] = 0xffffffbb;
		sou[6] = 0xffffffe5;
		sou[7] = 0xffffffad;
		sou[8] = 0xffffff98;
		return sou;
	}
}

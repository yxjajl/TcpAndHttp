package com.bytes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BytesUtil {
	public static byte[] int2bytes(int num) {
		byte[] result = new byte[4];
		result[0] = (byte) ((num >>> 24) & 0xff);// 说明一
		result[1] = (byte) ((num >>> 16) & 0xff);
		result[2] = (byte) ((num >>> 8) & 0xff);
		result[3] = (byte) ((num >>> 0) & 0xff);
		return result;
	}

	// 高位在前，低位在后
	public static int bytes2int(byte[] bytes) {
		int result = 0;
		if (bytes.length == 4) {
			int a = (bytes[0] & 0xff) << 24;// 说明二
			int b = (bytes[1] & 0xff) << 16;
			int c = (bytes[2] & 0xff) << 8;
			int d = (bytes[3] & 0xff);
			result = a | b | c | d;
		}
		return result;
	}

	public static void main(String[] args) {
		// System.out.println(bytes2int(int2bytes(1488)));
		System.out.println(Integer.toBinaryString(1488));
		
		byte[] tmp = int2bytes(1488);
		for (byte b : tmp) {
			if (b != 0)
				System.out.println(Integer.toHexString(b));
		}
		int v = Integer.valueOf("011010000",2);
		System.out.println(v);
		System.out.println(Integer.toHexString(v));
	}
}

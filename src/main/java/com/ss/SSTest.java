package com.ss;

public class SSTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = SSTest.getDate();
		for(int value: arr) {
			System.out.print((char)value);
		}
		System.out.println("==========");
		 arr = new int[]{87, 101, 108 ,99, 111, 109 ,101, 78, 101 ,111};
		for(int value: arr) {
			System.out.print((char)value);
		}
	}
	
	public static int[] getDate () {
		int[] src = new int[36];
		int n = 0;
		src[n++] =0x38;
		src[n++] =0x37;
		src[n++] =0x20;
		src[n++] =0x31;
		src[n++] =0x30;
		src[n++] =0x31;
		src[n++] =0x20;
		src[n++] =0x31;
		src[n++] =0x30;
		src[n++] =0x38;
		src[n++] =0x20;
		src[n++] =0x39;
		src[n++] =0x39;
		src[n++] =0x20;
		src[n++] =0x31;
		src[n++] =0x31;
		src[n++] =0x31;
		src[n++] =0x20;
		src[n++] =0x31;
		src[n++] =0x30;
		src[n++] =0x39;
		src[n++] =0x20;
		src[n++] =0x31;
		src[n++] =0x30;
		src[n++] =0x31;
		src[n++] =0x20;
		src[n++] =0x37;
		src[n++] =0x38;
		src[n++] =0x20;
		src[n++] =0x31;
		src[n++] =0x30;
		src[n++] =0x31;
		src[n++] =0x20;
		src[n++] =0x31;
		src[n++] =0x31;
		src[n++] =0x31;
		return src;
	}

}

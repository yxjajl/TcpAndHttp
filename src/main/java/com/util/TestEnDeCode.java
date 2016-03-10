package com.util;

import java.io.File;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class TestEnDeCode {

	/**  
	 *   
	 */
	public TestEnDeCode() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestEnDeCode t = new TestEnDeCode();
		t.encode();
		// t.decode();

	}

	//
	// 编码
	public void encode() {
		try {
			String str = "http://www.zzlsoftware.com:8080/Mymiyou/image/weixing 4.3.apk";// 二维码内容
			String path = "D:\\hwy.png";
			BitMatrix byteMatrix;
			byteMatrix = new MultiFormatWriter().encode(new String(str.getBytes("GBK"), "iso-8859-1"),
					BarcodeFormat.QR_CODE, 200, 200);
			File file = new File(path);
			
			MatrixToImageWriter.writeToFile(byteMatrix, "png", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//
	// // 解码
	// public void decode() {
	// try {
	// Reader reader = new MultiFormatReader();
	// String imgPath = "D:\\hwy.png";
	// File file = new File(imgPath);
	// BufferedImage image;
	// try {
	// image = ImageIO.read(file);
	//
	// if (image == null) {
	// System.out.println("Could not decode image");
	// }
	// LuminanceSource source = new BufferedImageLuminanceSource(image);
	// BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
	// source));
	// Result result;
	// Hashtable hints = new Hashtable();
	// hints.put(DecodeHintType.CHARACTER_SET, "GBK");
	// result = new MultiFormatReader().decode(bitmap, hints);
	// String resultStr = result.getText();
	// System.out.println(resultStr);
	//
	// } catch (Exception ioe) {
	// System.out.println(ioe.toString());
	// }
	//
	// } catch (Exception ex) {
	//
	// }
	// }

}
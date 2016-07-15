package com.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PackageUtil {
	public static void main(String[] args) throws Exception {
		//Runtime.getRuntime().exec("cmd /k del C:\\Users\\Administrator.HX15011009\\Desktop\\war\\*.* /Q");
		//Runtime.getRuntime().exec("cmd /k cd C:\\Users\\Administrator.HX15011009\\Desktop\\war\\ ");

		Process process = Runtime.getRuntime().exec("cmd /k dir");

		new Thread(new StreamDrainer(process.getInputStream())).start();

	}
}

class StreamDrainer implements Runnable {
	private InputStream ins;

	public StreamDrainer(InputStream ins) {
		this.ins = ins;
	}

	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(ins,"GBK"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
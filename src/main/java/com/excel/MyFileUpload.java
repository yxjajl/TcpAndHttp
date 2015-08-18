package com.excel;

import java.io.File;
import java.io.IOException;

import com.excel.up.FileClient;

public class MyFileUpload {

	public static FileClient upload(File file) throws IOException {
		String access_key = "lodawd98232";
		String secret_access_key = "2313sd32131231321";
		String upload_api_domain = "up.dianziq.com";
		String upload_uid = "6793";
		String upload_lid = "1409545484838-d11cea5d584bbec66243a4eec61ceb2f";
		FileClient dzqClient = new FileClient(access_key, secret_access_key);
		dzqClient.setApiDomain(upload_api_domain);
		String md5s = FileClient.md5(file);
		dzqClient.setContentMD5(md5s);
		// System.out.println("md5s = " + md5s);
		dzqClient.setUid(upload_uid);
		dzqClient.setLid(upload_lid);
		dzqClient.writeFile(file);
		return dzqClient;
	}
	
	//http://img.dianziq.com/img/bYNz2aACJAAx.jpg
	public static void main(String[] args) throws Exception {
		FileClient fc = MyFileUpload.upload(new File("D:\\pic\\x.jpg"));
		System.out.println(fc.getFilename());
	}
}

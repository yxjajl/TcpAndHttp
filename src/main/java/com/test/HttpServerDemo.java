package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HttpServerDemo {
	public static void main(String[] args) throws IOException {
		System.setProperty("user.dir", "/usr/local/java/aido/books");
		InetSocketAddress addr = new InetSocketAddress(8080);
		HttpServer server = HttpServer.create(addr, 0);

		server.createContext("/", new MyHandler());
		server.setExecutor(Executors.newCachedThreadPool());
		server.start();
		System.out.println("Server is listening on port 8080");
	}
}

class MyHandler implements HttpHandler {
	public static final String USERDIR = System.getProperty("user.dir");

	public void handle(HttpExchange exchange) throws IOException {
		String requestMethod = exchange.getRequestMethod();
		System.out.println(exchange.getRequestURI().getPath());
		String path = exchange.getRequestURI().getPath();
		if ("/favicon.ico".equalsIgnoreCase(path)) {
			return;
		}
		if (requestMethod.equalsIgnoreCase("GET")) {
			exchange.sendResponseHeaders(200, 0);

			Headers responseHeaders = exchange.getResponseHeaders();
			OutputStream responseBody = exchange.getResponseBody();
			responseHeaders.set("Content-Type", "text/html; charset=utf-8");
			File file = new File(USERDIR + path);

			if (file.exists()) {
				if (file.isDirectory()) {
					responseBody.write(createDirBody(file).getBytes());
				} else {
					responseBody.write(createFileBody(file).getBytes());
				}
			} else {
				responseBody.write(createErr().getBytes());
			}

			responseBody.close();
		}
	}

	public String createDirBody(File path) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html><body>\n");
		sb.append("<ul>");
		for (File subFile : path.listFiles()) {

//			try {
			sb.append("<li><a href=\"" + subFile.getPath().replace(USERDIR, "") + "\">");
//			} catch (UnsupportedEncodingException e) {
//				sb.append("SystemErr");
//			}
			sb.append(subFile.getName());
			sb.append("</a></li>");
		}
		sb.append("</ul>");
		sb.append("</body></html>\n");
		return sb.toString();
	}

	public String createFileBody(File file) {
		String snum = file.getName().substring(0, 4);
		int num = Integer.valueOf(snum);

		int preNum = num - 1;
		if (preNum < 0) {
			preNum = 0;
		}

		int nexNum = num + 1;

		StringBuilder sb = new StringBuilder();
		sb.append("<html><body>\n");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			sb.append("System exception \n");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
		sb.append("<br><center>");
		sb.append("&nbsp;&nbsp;<a href=\" " + getUrl(file, preNum) + "  \">上一页</a>");
		sb.append("&nbsp;&nbsp;目录&nbsp;&nbsp;");
		sb.append("<a href=\"" + getUrl(file, nexNum) + "\">下一页</a>");
		sb.append("</center>");
		sb.append("</body></html>\n");
		return sb.toString();
	}

	public String createErr() {
		return "<html></body></body></html>";
	}

	public String getUrl(File file, int num) {
		return getFile(file, lpad("" + num));
	}

	public String getFile(File file, String num) {
		for (String str : file.getParentFile().list()) {
			if (str.startsWith(num)) {
				return str;
			}
		}

		return "";
	}

	public static String lpad(String str) {
		StringBuilder sb = new StringBuilder();
		int start = str.length();
		for (int i = start; i < 4; i++) {
			sb.append("0");
		}
		return sb.append(str).toString();
	}
}

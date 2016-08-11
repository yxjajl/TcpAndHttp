package com.groovy.utils

import java.nio.ByteBuffer
import java.nio.channels.FileChannel

class FileUtil {
	static final def FILENAME = /C:\Users\r6yuxx\Desktop\tmp\test.txt/;

	static main(args) {
		readFileAll(FILENAME)
	}

	static def readFile(path) {
		RandomAccessFile file = new RandomAccessFile(path,"r");
		FileChannel inChannel = file.getChannel();

		ByteBuffer bf = ByteBuffer.allocate(10);
		while(inChannel.read(bf)!= -1) {
			bf.flip();
			byte[] tmp = new byte[bf.limit()];
			bf.get(tmp);
			bf.clear();
			println new String(tmp);
		}
	}

	static def readFileAll(path) {
		def file = new File(path)
		file.readLines().each { println it }
	}

	static def writeFile(path){
		def file = new File(path);
		file.append("aaaaa");
	}
}



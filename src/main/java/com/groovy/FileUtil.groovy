package com.groovy

def tt() {
	def fn = /C:\Users\r6yuxx\Desktop\tmp\test.txt/
	File file = new File(fn);

	//		for( a in 0..10) {
	//			file.append(a+"aaaaa,")
	//		}
	//		file.append("\n")
	//		for( a in 0..10) {
	//			file.append(a+"bbbbb,")
	//		}

	file.eachLine { println it }
}

tt();

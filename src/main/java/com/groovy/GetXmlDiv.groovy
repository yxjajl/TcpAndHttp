package com.groovy

class GetXmlDiv {
	def map=[:]
	static def main(args){
		getXmlDiv();
	}

	static def getXmlDiv() {
		File file = new File(/C:\Users\r6yuxx\Desktop\tmp\bill99\daan.txt/);
		def text = file.getText();
		def pattern = ~/<div style="float: left; width: 94%;">([\s\S]*?)(<span[^<]*?>[\s\S]*?<\/span>)+?([\s\S]*?)<\/div>/
		def matcher = pattern.matcher(text)

		matcher.each { println it[1] }
	}
}

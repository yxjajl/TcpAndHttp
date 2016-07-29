package com.groovy
//拿成绩 保存题库
def fn = /C:\Users\r6yuxx\Desktop\tmp\bill99\daan.txt/
File file = new File(fn);

def isSeq = false;
def isTitle = false;
def s1 = """ class=\"question-order\""""
def s2 = """<td width="95%" align="left" valign="middle">"""
def s3 =  """<div style="float: left; width: 94%;">"""
file.readLines().each {

	if(it.indexOf(s3) >-1) {
		//println it
		isTitle = true
		return
	}


	if(isTitle) {
		if(it.indexOf("span") > -1) {
			isTitle = false
		} else {
			println it.trim()
		}
		return
	}
	
	getSeq(it,s1)
	getLine(it,s2)
	getLine2(it,"标准答案")
}



def getSeq(line,content){
	if(line.toString().indexOf(content)>-1) {
		def ss = line.replace('.',"").trim()
		def aa = new XmlParser().parseText(ss)
		print "["+aa.text().trim()+"]"
	}
}
 
def getLine(line,content){
	if(line.toString().indexOf(content)>-1) {
		def ss = line.replace('.',"").trim()
		def aa = new XmlParser().parseText(ss)
		println aa.text().trim()
	}
}

def getLine2(line,content){
	if(line.toString().indexOf(content)>-1) {
		println line.trim()
	}
}


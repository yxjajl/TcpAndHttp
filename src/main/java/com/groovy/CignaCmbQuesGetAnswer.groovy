package com.groovy
// 拿到题目并找到答案
def fn = /C:\Users\r6yuxx\Desktop\tmp\bill99\tt.txt/
File file = new File(fn);

File ff = new File(/C:\Users\r6yuxx\Desktop\tmp\bill99\zzzz.txt/);

int i=1;
List<Davo> alist = []
List tmpList = []
ff.readLines().each {
	def lin = it.toString().trim()
	if(lin.length() == 0) return;

	tmpList << lin

	if(lin.indexOf("标准答案")>-1) {
		if(tmpList.size() != 6) {
			println tmpList
		} else {
			Davo vo = new Davo();
			vo.title = tmpList[0]
			vo.a = tmpList[1]
			vo.b = tmpList[2]
			vo.c = tmpList[3]
			vo.d = tmpList[4]
			vo.des = tmpList[5]
			alist.add(vo);
		}
		tmpList = []
	}
}

//alist.each  { println it.tos() }
//println("=====================================================")
//return
def countc = 0;
def isSeq = false;
def isTitle = false;

def s1 = """<div style="float: left; width: 30px;">"""

def s2 = """<div style="float: left; width: 94%;">"""
file.readLines().each {
	if(it.indexOf(s1) >-1) {
		//println it
		isSeq = true
		return
	}

	if(it.indexOf(s2) >-1) {
		//println it
		isTitle = true
		return
	}

	if(isSeq) {
		def ss = it.replace('.',"").trim()
		//		println ss
		def aa = new XmlParser().parseText(ss)
		print "["+aa.text() +"]"
		isSeq = false;
		return
	}

	if(isTitle) {
		if(it.indexOf("span") > -1) {
			isTitle = false
		} else {
			def tt = it.trim()
			Davo vv = findDavo(alist,tt)
			if(vv == null) {
				//				println tt
				countc ++;
			} else {
				println "*****"+vv.tos()
			}
		}
		return
	}
}


println "没有找到答案的题有 + " + countc

def findDavo(list,title) {
	for(Davo tmp:list) {
		if(tmp.title.indexOf(title.toString()) >-1)
			return tmp
	}
	return null
}

class Davo {
	def title
	def a
	def b
	def c
	def d
	def des

	def tos() {
		def bb = des.replaceAll("标准答案：","").trim()
		def x
		if(bb.equalsIgnoreCase("A")) {
			x = a
		}  else
		if(bb.equalsIgnoreCase("B")) {
			x = b
		} else
		if(bb.equalsIgnoreCase("C")) {
			x = c
		} else
		if(bb.equalsIgnoreCase("D")) {
			x = d
		}

		def st = title[title.indexOf(']')..-1]
		return [st,  "****答案是:"+x]
	}
}


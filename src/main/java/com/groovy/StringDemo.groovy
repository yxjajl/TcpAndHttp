package com.groovy

def tt1() {
	//取消转议
	s = /c:\tindows\tystem但 \n 字符\n不包含在内/
	println s
	s = /c:\tindows\tystem/
	println s
	s = 'c:\tindows\tystem'
	println s

	s1 = new String("Abc")
	s2 = new String("abc")

	println s1 == s2 ? "Same" : "Different"		// 执行s1.equals(s2)
	println s1 != s2 ? "Different" : "Same"		// 执行!s1.equals(s2)
	println s1 > s2 ? "Great" : "Less"			// 执行s1.compareTo(s2) > 0
	println s1 < s2 ? "Less" : "Great"			// 执行s1.compareTo(s2) < 0
	println s1 <=> s2 == 1 ? "Same" : "Different"		// 执行s1.compareTo(s2)
	println s1 <=> s2
}

def tt2() {
	def str = "0123456"
	println str[5,3..1]
	println str[-1..-4]
	def add = { x, y -> x + y }
	println pairWise(1..5,add)
}
static def pairWise(list, Closure invoke) {
	if (list.size() < 2) return []
	def next = invoke(list[0], list[1])
	return [next] + pairWise(list[1..-1], invoke)
}

def ffor() {
	def list = ["Lars", "Ben", "Jack"]
	list.each{
		print it
	}

	5.times {
		ss->print "Times + $ss "
	}
	1.upto(3) {
		println "Up + $it "
	}
	4.downto(1) {
		print "Down + $it "
	}
	def sum = 0
	1.upto(100) {
		sum += it
	}
	println "sum="+ sum

	(1..<6).each {
		print "Range $it"
	}
	println ""
	for (i in 0..9) {
		println ("Hello $i")
	}
}


tt1();

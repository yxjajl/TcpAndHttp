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
	println str[5, 3..1]
	println str[-1..-4]
	def add = { x, y -> x + y }
	println pairWise(1..5,add)
}
static def pairWise(list, Closure invoke) {
	if (list.size() < 2) return []
	def next = invoke(list[0], list[1])
	return [next]+ pairWise(list[1..-1], invoke)
}

def ffor() {
	def list = ["Lars", "Ben", "Jack"]
	list.each{ print it }

	5.times { ss->print "Times + $ss " }
	1.upto(3) { println "Up + $it " }
	4.downto(1) { print "Down + $it " }
	def sum = 0
	1.upto(100) { sum += it }
	println "sum="+ sum

	(1..<6).each { print "Range $it" }
	println ""
	for (i in 0..9) {
		println ("Hello $i")
	}
}

class Person{
	String name
	int age
	String toString(){
		return "${name}:${age}"
	}

	def getName() {
		//println "Getting Name"
		name
	}
}
def tt3() {
	//一个集合使用展开运算符(*.)可以得到一个元素为原集合各个元素执行后面指定方法所得值的集合
	def people = [new Person(name:'阿大',age:24), new Person(name:'阿二',age:18)]
	println people*.toString()

	//由于Groovy中“非空即真”，所以原java中的三元运算符可以简化为二元运算符
	def person = new Person(name: "Jim")
	String name=  person.name?person.name:'unknown'// java的写法
	def name2= person.name ?: "unknown" // Groovy 的写法

	Person user
	// 为避免空指针异常，需要加上一个检查，java的写法如下
	if (user != null) {
		println "Java FirstName = ${user.name}"

	}
	// Groovy语法的写法
	println "Groovy FirstName = ${user?.name}"


	def todo = new Person(name: "Jim")
	//写的getName
	println todo.name + "${todo.name}" + "$todo.name"
	//pojo的getName
	println todo.@name
}


tt3();
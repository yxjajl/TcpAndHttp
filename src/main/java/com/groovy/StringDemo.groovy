package com.groovy

//groovy 几个特殊符号
//.& 取函数 def say = new Class().&say
//*  放在list或map 前可取出list的所有值  (方便传参)
//*. 一个集合使用展开运算符(*.)可以得到一个元素为原集合各个元素执行后面指定方法所得值的集合
//?: 类似三目运算符
//?. 用于非空取对像属性值 (如果对象不为空则取其属性，否则返回null)
//.@ 用于直接读取对像属性 不采用get方法
//${obj.name}与$obj.name相同，通过get方法取属性值


def tt1() {
	//取消转议
	s = /c:\tindows\tystem但 \n 字符\n不包含在内/
	println s
	s = /c:\tindows\tystem/
	println s
	s = 'c:\tindows\tystem'
	println s
	s="""123456"""
	println "aaaaaaa"+ s[0..-1]
	
	s= " 123 "
	println "去空格: |${s.trim()}"

	s1 = new String("Abc")
	s2 = new String("abc")

	println s1 == s2 ? "Same" : "Different"		// 执行s1.equals(s2)
	println s1 != s2 ? "Different" : "Same"		// 执行!s1.equals(s2)
	println s1 > s2 ? "Great" : "Less"			// 执行s1.compareTo(s2) > 0
	println s1 < s2 ? "Less" : "Great"			// 执行s1.compareTo(s2) < 0
	println s1 <=> s2 == 1 ? "Same" : "Different"		// 执行s1.compareTo(s2)
	println s1 <=> s2

	def  x = "hello world_rick"
	//动态添加类方法
	String.metaClass.title ={
		->
		def sb = new StringBuffer()
		def first = true;
		delegate.each {
			if(first) {
				if( Character.isLetter(it as char)) {
					sb << it.toUpperCase();
					first = false
				} else {
					sb << it
				}
			} else {
				if( Character.isLetter(it as char)) {
					sb << it
				} else {
					sb << it
					first = true
				}
			}
		}
		sb.toString()
	}

	println "xtitle:"+ x.title()


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
	println ""

	//*list取出list中的所有值
	def list2 =[*list, "last"]
	list2.each{ print it }
	println ""

	5.times {print "Times + $it " }
	println ""
	1.upto(5) { print "Up + $it " }
	println ""
	4.downto(1) { print "Down + $it " }
	println ""
	def sum = 0
	1.upto(100) { sum += it }
	println "sum="+ sum

	(3..<6).each { print "Range $it" }
	println ""
	for (i in 0..9) {
		println ("Hello $i")
	}
	
	def a,b = retmoreval();
	println ("a=$a : b = $b")
}

def retmoreval() {
	return [1,2]
}

class Person{
	String name
	int age
	String toString(){
		return "${name}:${age}"
	}

	def getName() {
		println "Getting Name"
		"1"+name
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
	println "@==="+todo.@name
}


tt1();
//tt2();
//tt3();
//ffor();

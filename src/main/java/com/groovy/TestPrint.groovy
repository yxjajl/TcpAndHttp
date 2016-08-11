package com.groovy


def tt(a,b,c){
	println a + b
	c.call(null)
}

tt 10, 11, { println "cccc" }
tt (10, 11) { println "cccc" }

package com.groovy


def filePath = /D:\Java\workspace_newcoresystem\portal-pc-newcoresystem\portal-core/
File file = new File(filePath);

def dirs= file.listFiles().findAll{it.isDirectory() && !it.name.endsWith("svn")}

println "cd D:"
println "D:"
dirs.each {
	println "cd "+it
	println "call mvn compile install"
}
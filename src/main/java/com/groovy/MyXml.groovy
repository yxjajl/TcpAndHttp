package com.groovy

class MyXml {

	static main(args) {

		pars1();
	}

	static def pars() {
		def xml = new groovy.xml.MarkupBuilder()
		xml.langs(type:"current"){
			language("Java")
			language("Groovy")
			language("JavaScript")
		}
	}
	
	static def pars1() {
		def s=new StringWriter()
		s.append('<header>\n')
		def xml = new groovy.xml.MarkupBuilder(s)
		xml.'web-app'(){
			'display-name'('My Web Application')
		}
		println s.toString()
	}

	static def pars2() {
		def xml = '<langs type="current"> <language>Java</language>  <language>Groovy</language>    <language>JavaScript</language> </langs>' ;
		def langs = new XmlParser().parseText(xml)
		println "type = ${langs.attribute("type")}"
		langs.language.each{ println it.text() }
	}

	static def pars3() {
		def s=new StringWriter()
		def builder = new groovy.xml.MarkupBuilder(s)
		builder.'web-app' { 'display-name' 'My Web Application' }
		println s.toString()
	}
}

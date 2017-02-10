package com.groovy

class MyXml {

	static main(args) {

		pars3();
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
		xml.'web-app'(){ 'display-name'('My Web Application') }
		println s.toString()
	}

	static def pars2() {
		def xml = '<langs type="current"> <language>Java</language>  <language>Groovy</language>    <language>JavaScript</language> </langs>' ;
		def langs = new XmlParser().parseText(xml)
		println "type = ${langs.attribute('type')}"
		langs.language.each{ println it.text() }
	}

	static def pars20() {
		def xml = '''
					<html >
						<body>
							<head>
							<title/>
							<div class="class1">abc</div>
							<div class="class1">efg</div>

							</head>
					    </body>
					</html>

					'''
		def html = new XmlParser().parseText(xml)
		def list = []
		proNode(html,list)
		list.each{ println it.text() }
	}

	static def proNode(node, list) {
		println node.name()
		if(node.name() == 'div') {
			list.add(node)
		} else {
			List children=node.children()
			if(children!=null){
				children.each { proNode(it,list) }
			}
		}
	}

	static def pars3() {
		def s=new StringWriter()
		def builder = new groovy.xml.MarkupBuilder(s)
		builder.'web-app' {
			'display-name' 'My Web Application'
			'servlet-name' 'xxxServlet'
		}
		builder.'servlet' { 'name'	'xxxServlet'}
		println s.toString()
	}

	static def pars4() {
		def s=new StringWriter()
		def page = new groovy.xml.MarkupBuilder(s)
		page.html {
			head{title 'Hello'}
			body {
				ul{
					for(count in 1..10) {
						li "world $count"
					}
				}
			}
		}

		println s.toString()
	}
}

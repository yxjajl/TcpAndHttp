package com.groovy

import com.tools.NetUtil
import com.util.HttpUtil

class TelnetDemo {
	static main(args) {
		def pageContent = HttpUtil.sendGet("http://www.proxy360.cn/Region/America");//http://www.proxy360.cn/default.aspx
		def replist= [
			"""<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">"""
			,
			"xmlns=\"http://www.w3.org/1999/xhtml\""
		]
		replist.each {
			pageContent = pageContent.replace(it,"");
		}

		//println pageContent
		pageContent = pageContent.replace("&","&amp;");
		//<div.*?>(.*?)<\/div>
		def pattern = ~/<div (style="float:left; display:block; width:630px;")+?>[\s\S]*?<\/div>/
		//def pattern2 = ~/<span.*?>(.*?)<\/span>/
		def matcher = pattern.matcher(pageContent)

		matcher.each {
			def div = new XmlParser().parseText(it[0])
			def children = div.children()

			def host = children[0].text().trim();
			def port = children[1].text().trim() as int  // 可以用 strnumber as int  或　 strnumber.toInteger()
			if(NetUtil.isPortUsing(host,port)){
				println host +":"+port + " yyyyyyyyyyyyyyyyyyyyyyyyy"
			} else {
			println host +":"+port + " nnnnnnnnnnnnnnnn"
			}
		}


		//		pageContent = pageContent.replace("&","&amp;");
		//		pageContent = pageContent.replace("&","&amp;");

		//println pageContent
		//		def html = new XmlParser().parseText(pageContent)
		//		def list = []
		//		proNode(html,list)
		//list.each{ println it.text() }


	}


	static def proNode(node, list) {
		println node.name()
		if(node.name() == 'div') {
			list.add(node)
		} else {
			List children=node.children()
			if(children){
				children.each { proNode(it,list) }
			}
		}
	}
}

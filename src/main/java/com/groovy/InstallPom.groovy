package com.groovy

import org.apache.commons.io.FileUtils;

class InstallPom {
	static final String strInstall = "mvn install:install-file -Dfile=D:\\doc\\jar\\%s -DgroupId=%s -DartifactId=%s -Dversion=%s -Dpackaging=jar";
	static main(args) {
		String path = "D:\\doc\\jar\\dd.xml";
		String content = readFile(path);
		parseXml(content);
	}


	static def String readFile(String path) {
		return FileUtils.readFileToString(new File(path));
	}

	static def parseXml(strXml) {
		def langs = new XmlParser().parseText(strXml);
		langs.dependency.each {

			//println it.groupId.text()+"."+it.artifactId.text()+" X"+it.version.text();
			String strJar = it.artifactId.text() + "-"+it.version.text()+".jar";
			println String.format(strInstall,strJar,it.groupId.text(),it.artifactId.text(),it.version.text());
		}
	}
}

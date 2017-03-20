package com.groovy

class DeployPom {
	static deploystr = """mvn deploy:deploy-file -DgroupId=%s -DartifactId=%s -Dversion=%s -Dpackaging=jar -Dfile=%s -Durl=%s -DrepositoryId=%s """
	static filepath = """D:\\Java\\workspace_epay_17_2_8\\epay-sdk\\epay-sdk-bill99wtds\\pom.xml""";
	static MYREP = """D:\\repository\\Repository\\"""
	static repoUrl = """http://10.142.146.41:7001/nexus/content/repositories/releases/"""
	static def main(args){
		parseXml(filepath)
	}

	static def parseXml(file) {
		def root = new XmlParser().parse(new File(file));
		root.dependencies.dependency.each {
			//			println it.groupId.text() + ","+ it.artifactId.text() + "," + it.version.text()
			def df= MYREP+it.groupId.text().replaceAll("\\.","""\\\\""")+"\\"+it.artifactId.text()+
					"\\"+it.version.text()+"\\"+it.artifactId.text()+"-"+it.version.text()+".jar"
			//这个地方很奇怪，直接从D的仓库上传死活不成功，改成从tmp目录就可以了,不知道是不是路径中包含了什么特殊字符引起的，还是别的什么原因
			println "copy "+df+"  D:\\tmp\\  /y"
			println String.format(deploystr,it.groupId.text(),it.artifactId.text(),it.version.text(),"D:\\tmp\\"+it.artifactId.text()+"-"+it.version.text()+".jar",repoUrl,"releases")
		}
	}
}

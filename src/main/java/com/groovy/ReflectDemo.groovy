package com.groovy

class ReflectDemo {

	static main(args) {
		def vo  = new ReflectVO();
		vo.a = 128
		println vo.class.getName();
		println vo."c";
		println "===================="
		//vo.metaClass.metaMethods.each {println it};
		//vo.metaClass.properties.each {println it.name};
		vo."setB"("ddddx")
		println vo."b";
		println vo.a;

		println vo.a.class.simpleName
		println vo.b.class.simpleName
		println vo.c.class.simpleName
		println "========ClassTmp============"
		ClassTmp tmp = new ClassTmp<Integer>();

		tmp.t = 123
		println tmp.getType()
		println "========xml============"

		voToXml(vo);
	}

	static String voToXml(Object obj){
		def sw=new StringWriter()
		def builder = new groovy.xml.MarkupBuilder(sw)
		builder."${obj.class.simpleName}"{
			obj.metaClass.properties.sort{a,b->a.toString().compareTo(b.toString())}.each {
				println it.name
				if(it.name in ["class", "metaClass"])  {
					
				}else {
					"${it.name}" obj."${it.name}"
				}
			}
		}

		println sw.toString()
	}
}


class ReflectVO {
	def a;
	def b;
	def String c = "cccc";
	//此处注意如果用 public String c ,那么在 obj.metaClass.properties.each会找不到，暂不清楚原因

	public setB(_b) {
		b=_b;
	}

	public setC(_c) {
		c = _c;
	}
}

class ClassTmp<T> {
	public T t

	String getType() {
		return t.class.simpleName;
	}
}
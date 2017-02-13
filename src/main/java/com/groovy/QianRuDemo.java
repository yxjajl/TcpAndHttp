package com.groovy;

import java.io.File;

import com.util.CommonUtil;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

/**
 * java嵌入groovy脚本执行
 * 1.shello命令调用
 * 2.嵌入脚本调用
 * 
 * 
 * @author r6yuxx
 *
 */
public class QianRuDemo {
	private static QianRuDemo instance = new QianRuDemo();
	private QianRuService qianRuService;

	public static QianRuDemo getInstance() {
		return instance;
	}

	private QianRuDemo() {
		load();
	}

	// 脚本
	public static void evalScript() throws Exception {
		Binding binding = new Binding();
		GroovyShell shell = new GroovyShell(binding);
		// 直接方法调用
		// shell.parse(newFile(//))
		Script script = shell.parse("def join(String[] list){return list.join('--');}");
		String joinString = (String) script.invokeMethod("join", new String[] { "A1", "B2", "C3" });
		System.out.println(joinString);
		//// 脚本可以为任何格式,可以为main方法,也可以为普通方法
		// 1)defcall(){...};call();
		// 2)call(){...};
		script = shell.parse("static void main(String[] args){i=i*2;}");
		script.setProperty("i", new Integer(10));
		script.run();// 运行,
		System.out.println(script.getProperty("i"));
		// thesameas
		System.out.println(script.getBinding().getVariable("i"));
		script = null;
		shell = null;
	}

	// 脚本2
	public static void evalScriptTextFull() throws Exception {
		StringBuffer buffer = new StringBuffer();
		// defineAPI
		buffer.append("class User{").append("String name;Integer age;")
				// .append("User(Stringname,Integerage){this.name=name;this.age=age};")
				.append("String sayHello(){return'Hello,Iam'+name+',age'+age;}}\n");
		// Usage
		buffer.append("def user=new User(name:'zhangsan',age:1);").append("user.sayHello();");
		// groovy.lang.Binding
		Binding binding = new Binding();
		GroovyShell shell = new GroovyShell(binding);
		String message = (String) shell.evaluate(buffer.toString());
		System.out.println(message);
	}

	// 类加载
	public void load() {
		String str = "src\\main\\java\\com\\groovy\\Qian.groovy";
		// QianRuDemo.class.getClassLoader().getResource("").getPath() + "com/groovy/Qian.groovy";
		System.out.println(str);
		File sourceFile = new File(str);
		System.out.println("" + sourceFile.lastModified());
		Class<?> testGroovyClass;
		try {
			GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());
			testGroovyClass = classLoader.parseClass(new GroovyCodeSource(sourceFile));
			qianRuService = (QianRuService) testGroovyClass.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void tt() {
		QianRuVO vo = new QianRuVO();
		vo.a = 15;
		vo.c = 25;
		qianRuService.change(vo);

//		qianRuService.invokeMethod("change", vo);
		System.out.println(CommonUtil.getClassString(vo));
//
		vo = qianRuService.init();
//		vo = (QianRuVO) qianRuService.invokeMethod("init", null);
		System.out.println(CommonUtil.getClassString(vo));
	}

	public static void main(String[] args) throws Exception {
		QianRuDemo demo = QianRuDemo.getInstance();

		demo.tt();
		// demo.evalScriptTextFull();
		// demo.evalScript();
	}

}

package com.xml;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class TestStudent {

	/**
	 * <P>
	 * Title: main
	 * </P>
	 * <P>
	 * Description:
	 * </P>
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		java.io.File file = new java.io.File("student.xml");
		System.out.println(file.getAbsolutePath());
		if (!file.exists()) {
			file.createNewFile();
		}
		Student s1 = new Student();
		s1.setNameString("卞哲");
		s1.setAge(10);

		java.io.BufferedOutputStream oop = new java.io.BufferedOutputStream(new java.io.FileOutputStream(file));
		java.beans.XMLEncoder xe = new java.beans.XMLEncoder(oop);
		xe.flush();
		// 写入xml
		xe.writeObject(s1);
		xe.close();
		oop.close();

		// 读取xml文件
		java.beans.XMLDecoder xd = new java.beans.XMLDecoder(new BufferedInputStream(new FileInputStream(file)));

		Student s2 = (Student) xd.readObject();
		xd.close();
		System.out.println("name: " + s2.getNameString());
		System.out.println("age :" + s2.getAge());
	}

}

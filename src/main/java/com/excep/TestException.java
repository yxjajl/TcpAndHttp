package com.excep;

//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
import org.springframework.util.Assert;

public class TestException {

	public static void main(String[] args) {
		test3();
		test2(null);
	}

	public static void test1() {
		try {
			throw new BusinessException("testexception");
		} catch (BusinessException | NullPointerException be) {
			System.out.println("BusinessException " + be.getMessage());
			be.printStackTrace();
		} catch (Exception be) {
			System.out.println("Exception " + be.getMessage());
			be.printStackTrace();
		}
	}

	public static void test2(String str) {
		Assert.isTrue(str != null, "str is null");
		assert(str != null) : "str is null";
		System.out.println("hello " + str);
	}

	public static void test3() {
		// 断言1结果为true，则继续往下执行
		assert true;
		System.out.println("断言1没有问题，Go！");

		System.out.println("\n-----------------\n");

		// 断言2结果为false,程序终止
		assert false : "断言失败，此表达式的信息将会在抛出异常的时候输出！";
		System.out.println("断言2没有问题，Go！");
	}
}

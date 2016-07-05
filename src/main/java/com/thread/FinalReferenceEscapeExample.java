package com.thread;

/**
 * 12可能会重排序(所以JSR-133增强了final的语义)
 * @author r6yuxx
 *
 */
public class FinalReferenceEscapeExample {
	final int i;
	static FinalReferenceEscapeExample obj;

	public FinalReferenceEscapeExample() {
		i = 1; //1写final域
		obj = this; //2 this引用在此“逸出”
	}

	public static void writer() {
		new FinalReferenceEscapeExample();
	}

	public static void reader()

	{
		if (obj != null) { //3
			int temp = obj.i; //4
		}
	}
}
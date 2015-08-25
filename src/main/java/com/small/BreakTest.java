package com.small;

public class BreakTest {
	public static void main(String[] args) {
		A: for (int a = 0; a < 100; a++) {
			for (int b = 0; b < 100; b++) {
				for (int c = 0; c < 100; c++) {
					System.out.println(a + "-" + b + "-" + c);
					if (c == 0)
						break A;
				}
			}
		}
	}
}

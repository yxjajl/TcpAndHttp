package com.ss;


public class TestInterfaceMain {
	public final static String kkk = "456";
	public String message = "c";

	public static void main(String[] args) {
		TestInterfaceMain tm1 = new TestInterfaceMain();
		TestInterfaceMain tm2 = new TestInterfaceMain();
		// tm.print(tm.new TestInterfaceA());
		System.out.println(MEITI.PLUS.apply(tm1, tm2));
	}

	public void print(TestInterface tt) {
		tt.print();
		// System.out.println(3+4);
	}

	class TestInterfaceA implements TestInterface {

		@Override
		public void print() {
			System.out.println(message);
			System.out.println(kkk);
		}

	}

	enum MEITI {
		PLUS("+") {
			String apply(TestInterfaceMain x, TestInterfaceMain y) {
				return x.message + y.message;
			}
		};
		private final String symbol;

		private MEITI(String symbol) {
			this.symbol = symbol;
		}

		abstract String apply(TestInterfaceMain x, TestInterfaceMain y);
	}
}

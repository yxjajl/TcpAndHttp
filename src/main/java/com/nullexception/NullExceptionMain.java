package com.nullexception;

import java.text.MessageFormat;

public class NullExceptionMain {
	private NeTestA nta = new NeTestA(new NeTestB());

	public static void main(String[] args) throws Exception {
		NullExceptionMain nem = new NullExceptionMain();
		nem.getNta().tt(MessageFormat.format("abc{0},cde{1}{2}", "a", "b","c"));

	}

	public NeTestA getNta() {
		return nta;
	}

	public void setNta(NeTestA nta) {
		this.nta = nta;
	}

}

class NeTestA {
	private NeTestB ntb;

	public NeTestA(NeTestB _ntb) {
		ntb = _ntb;
	}

	public void tt(String str) throws Exception {
		ntb.print(str);
	}
}

class NeTestB {
	public void print(String str) throws Exception {
		System.out.println(str);
	}
}

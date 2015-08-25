package com.set;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetTest {
	public static void main(String[] args) {
		TreeSet<TestTreeSetVO> tree = new TreeSet<TestTreeSetVO>();
		for (int i = 1; i < 8; i++) {
			tree.add(new TestTreeSetVO("" + i, 100 - i));
			System.out.println("=======================================" + i);
		}
		TestTreeSetVO vv = new TestTreeSetVO("" + 1, 50);
		if (!tree.contains(vv)) {
			System.out.println("=======================================tree");
			tree.add(vv);
		}
		System.out.println("=======================================en");

		for (TestTreeSetVO vo : tree) {
			System.out.println(vo.getName() + "," + vo.getValue());
		}
		// System.out.println("====================");
		// vv.setName("x");
		// System.out.println(tree.remove(vv));
		// vv.setValue(8);
		// tree.add(vv);
		//
		// for (TestTreeSetVO vo : tree) {
		// System.out.println(vo.toString());
		// }
		//
		// for (TestTreeSetVO vo : tree.subSet(new TestTreeSetVO("n", 2), new TestTreeSetVO("b", 4))) {
		// System.out.println("testtreesetvo : " + vo.toString());
		// }

		//
		// List<TestTreeSetVO> list = new ArrayList<TestTreeSetVO>();
		// list.add(new TestTreeSetVO(4));
		// list.add(new TestTreeSetVO(3));
		// list.add(new TestTreeSetVO(2));
		// list.add(new TestTreeSetVO(1));
		// TestTreeSetVO vv2 = new TestTreeSetVO(0);
		// list.add(vv2);
		// vv2.setValue(99);
		// System.out.println("remove vv2 : "+list.remove(vv2));

	}

}

class MyComparator implements Comparator<TestTreeSetVO> {
	@Override
	public int compare(TestTreeSetVO arg0, TestTreeSetVO arg1) {
		System.out.println("===========arg0.getName() = " + arg0.getName() + "===========================" + arg1.getName());
		if (arg0.getName().equals(arg1.getName())) {
			return 0;
		}

		if (arg0.getValue() > arg1.getValue())
			return 1;
		// if (arg0.getValue() == arg1.getValue())
		// return 0;
		return -1;
	}
}

class TestTreeSetVO implements Comparable<TestTreeSetVO> {
	private int value;
	private String name;

	public TestTreeSetVO(String na, int v) {
		name = na;
		value = v;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int compareTo(TestTreeSetVO playerArenaVO) {
		System.out.println("===========playerArenaVO.getName() = " + playerArenaVO.getName() + "=================thisName=========" + this.getName());
		if (this.getName().equals(playerArenaVO.getName())) {
			return 0;
		}
		if (value > playerArenaVO.getValue())
			return 1;
		// if (value == playerArenaVO.getValue())
		// return 0;
		return -1;

	}

	public String toString() {
		return name + ":" + value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub

		if (obj != null && obj instanceof TestTreeSetVO) {
			TestTreeSetVO bb = (TestTreeSetVO) obj;
			if (this.getName() == bb.getName()) {
				return true;
			}
		}
		return true;
	}

}
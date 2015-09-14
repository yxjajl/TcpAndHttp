package com.ss;

import java.util.ArrayList;
import java.util.List;

public class MyQuickSortList {

	public static void main(String[] args) {
		int[] arr = { 1, 3, 4, 5, 6, 7, 13, 10 };
		List<Integer> list = new ArrayList<Integer>();
		for (int value : arr) {
			list.add(value);
		}

		quickSort(list, 0, list.size() - 1);
		print(list, 0, list.size() - 1);
	}

	public static void print(List<Integer> list, int start, int end) {
		for (int i = start; i <= end; i++) {
			System.out.print(list.get(i) + ",");
		}
		System.out.println();
	}

	public static void quickSort(List<Integer> list, int start, int end) {
		if (end < start + 1) {
			return;
		}
		int half = halfpart(list, start, end);
		quickSort(list, start, half - 1);
		quickSort(list, half + 1, end);
	}

	public static int halfpart(List<Integer> list, int start, int end) {
		int x = start - 1;
		int div = list.get(end);
		for (int i = start; i < end; i++) {
			if (list.get(i) <= div) {
				x++;
				swap(list, x, i);
			}
		}
		swap(list, x + 1, end);
		return x + 1;
	}

	public static void swap(List<Integer> list, int a, int b) {
		Integer A = list.get(a);
		Integer B = list.get(b);
		list.remove(A);
		list.add(a, new Integer(B.intValue()));
		list.remove(B);
		list.add(b, A);
	}

}

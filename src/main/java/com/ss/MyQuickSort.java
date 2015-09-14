package com.ss;

import java.util.Arrays;
import java.util.List;

public class MyQuickSort {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 3, 4, 5, 6, 7, 13, 10);

		int[] arr = new int[] { 2, 8, 7, 1, 3, 5, 6, 4, 3 };
		quickSort(arr, 0, arr.length - 1);
		print(arr, 0, arr.length - 1);
	}

	public static void print(int[] arr, int start, int end) {
		for (int i = start; i <= end; i++) {
			System.out.print(arr[i] + ",");
		}
		System.out.println();
	}

	public static void quickSort(int[] arr, int start, int end) {
		if (end < start + 1) {
			return;
		}
		int half = halfpart(arr, start, end);
		quickSort(arr, start, half - 1);
		quickSort(arr, half + 1, end);
	}

	public static int halfpart(int[] arr, int start, int end) {
		int x = start - 1;
		int div = arr[end];
		for (int i = start; i < end; i++) {
			if (arr[i] <= div) {
				x++;
				swap(arr, x, i);
			}
		}
		swap(arr, x + 1, end);
		return x + 1;
	}

	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}

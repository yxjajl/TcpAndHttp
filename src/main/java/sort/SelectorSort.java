package sort;

import java.util.Arrays;
import java.util.Random;
import java.util.StringJoiner;

import com.util.TimeComp;

public class SelectorSort {
	static int swapCount = 0;

	public static void main(String[] args) {
		int[] arr = { 10, 3, 5, 2, 7, 9, 8, 1, 300, 200, 250 };
		// int max = 10000;
		// int[] arr = new int[max];
		// Random random = new Random();
		// for (int i = 0; i < max; i++) {
		// arr[i] = random.nextInt(max * 2);
		// }

		TimeComp tc = new TimeComp();
		qksort(arr);
		System.out.println("use:" + tc.comsum());
		print(arr);
		System.out.println("swapCount = " + swapCount);
	}

	// 选择排序
	public static void sort(int[] arr) {
		int length = arr.length;
		int min = 0;
		for (int n = 0; n < length - 1; n++) {
			min = n;
			// 选出最小的
			for (int m = n + 1; m < length; m++) {
				if (arr[min] > arr[m]) {
					min = m;
				}
			}
			if (min != n) {
				swap(arr, min, n);
			}
		}
	}

	// 冒泡
	public static void mpsort(int[] arr) {
		int length = arr.length;
		for (int n = 0; n < length; n++) {
			for (int m = length - 1; m > n; m--) {
				compareAndSwap(arr, m - 1, m);
			}
		}
	}

	// 插入排序
	public static void insort(int[] arr) {
		insort(arr, 0, 1);
	}

	public static void insort(int[] arr, int start, int d) {
		for (int i = start + d; i < arr.length; i = i + d) {
			// 取出arr[i]
			for (int m = start; m < i; m = m + d) {
				compareAndSwap(arr, m, i);
			}
		}
	}

	// 希尔
	public static void xesort(int[] arr) {
		int d = arr.length;
		while (d > 1) {
			// 计算分量
			d = d / 2;
			for (int i = 0; i < d; i++) {
				// 插入排序对分量上的数组元素排序
				insort(arr, i, d);
				// for (int j = i + d; (j + d) < arr.length; j = j + d) {
				// for (int m = i; m < j; m = m + d) {
				// compareAndSwap(arr, m, j);
				// }
				// }
			}

		} // end while
	}

	public static void qksort(int[] arr) {
		qksort(arr, 0, arr.length - 1);
	}

	public static void qksort(int[] arr, int low, int high) {
		if (low < high) {
			int middle = getMiddle(arr, low, high);
			System.out.println(middle);
			qksort(arr, low, middle);
			qksort(arr, middle + 1, high);
		}
	}

	public static int getMiddle(int[] arr, int low, int high) {
		int tmp = arr[low];
		while (low < high) {
			while (low < high && arr[high] > tmp) {
				high--;
			}
			arr[low] = arr[high];

			while (low < high && arr[low] < tmp) {
				low++;
			}
			arr[high] = arr[low];
		}

		arr[low] = tmp;

		return low;
	}

	public static void print(int[] arr) {
		StringJoiner sj = new StringJoiner(",", "[", "]");
		Arrays.stream(arr).forEach(x -> sj.add("" + x));
		System.out.println(sj);
	}

	public static void compareAndSwap(int[] arr, int m, int n) {
		if (arr[m] > arr[n]) {
			swap(arr, m, n);
		}
	}

	public static void swap(int[] arr, int m, int n) {
		swapCount++;
		int tmp = arr[m];
		arr[m] = arr[n];
		arr[n] = tmp;
		// arr[m] = arr[m] ^ arr[n];
		// arr[n] = arr[m] ^ arr[n];
		// arr[m] = arr[m] ^ arr[n];
	}
}

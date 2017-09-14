package sort;

public class HeadSort {
	public static void main(String[] args) {
		int arr[] = new int[] { 4, 1, 3, 2, 16, 9, 10, 14, 8, 7 };
		heapSort(arr);
		print(arr, 0, arr.length - 1);
	}

	private static void heapSort(int[] arr) {
		// 1数组堆化
		buildMaxHeap(arr);
		for (int i = arr.length - 1; i > 0; i--) {
			// 移除根节点(0与n-1互换)
			swap(arr, 0, i);
			// 调整 arr 0, i - 1
			maxHeapify(arr, 0, i - 1);
		}
	}

	// i节点的左右子节点的值是 2i+1, 2i+2,父节点的下标是 i-1/2
	private static void buildMaxHeap(int arr[]) {
		// 从子节点往上调整
		// 最后一个节点的父节点 ((index-1)/2就是最后一个节点的父节点下标)
		int parent = (arr.length - 1 - 1) / 2;
		for (int i = parent; i >= 0; i--) { // 必须从大到
			maxHeapify(arr, i, arr.length - 1);
		}
	}

	private static void maxHeapify(int[] arr, int index, int lastIndex) {
		// 父节点与两个子节点中值最大的那个节点的下标
		int biggest = getMaxIndex(arr, index, lastIndex);
		// 需要进行调整
		if (biggest != index) {
			swap(arr, biggest, index);
			// 递归最大堆化子树
			maxHeapify(arr, biggest, lastIndex);
		}
	}

	/**
	 * 取节点与子节点最大的那个
	 * 
	 * @param arr
	 * @param index
	 * @param lastIndex
	 * @return
	 */
	public static int getMaxIndex(int arr[], int index, int lastIndex) {
		int biggest = index;
		int leftChildIndex = left(index);
		int rightChildIndex = right(index);
		if (leftChildIndex <= lastIndex && arr[biggest] < arr[leftChildIndex]) {
			biggest = leftChildIndex;
		}
		if (rightChildIndex <= lastIndex && arr[biggest] < arr[rightChildIndex]) {
			biggest = rightChildIndex;
		}

		return biggest;
	}

	public static int left(int index) {
		return 2 * index + 1;
	}

	public static int right(int index) {
		return 2 * index + 2;
	}

	public static void swap(int[] arr, int m, int n) {
		arr[m] = arr[m] ^ arr[n];
		arr[n] = arr[m] ^ arr[n];
		arr[m] = arr[m] ^ arr[n];
	}

	public static void print(int[] arr, int start, int end) {
		for (int i = start; i <= end; i++) {
			System.out.print(arr[i] + ",");
		}
		System.out.println();
	}
}

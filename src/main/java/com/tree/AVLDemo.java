package com.tree;

public class AVLDemo<E extends Comparable<E>> {
	AVLNode<E> root;

	public AVLNode<E> add(E e, AVLNode<E> node) {
		if (node == null) {
			node = new AVLNode<>(e, null, null);
			return node;
		}

		// 左子树
		if (e.compareTo(node.value) < 0) {
			node.left = add(e, node.left);

			if (height(node.left) - height(node.right) >= 2) {// 打破平衡
				if (e.compareTo(node.left.value) < 0)// LL型（左左型）
					node = LL(node);
				else
					node = LR(node);
			}
		}

		// 右子树
		if (e.compareTo(node.value) > 0) {
			node.right = add(e, node.right);
			if (height(node.right) - height(node.left) >= 2) {// 打破平衡
				if (e.compareTo(node.right.value) > 0)// LL型（左左型）
					node = RR(node);
				else
					node = RL(node);
			}
		}
		node.height = Math.max(height(node.left), height(node.right)) + 1;// 更新高度
		return node;
	}

	// 带左子树旋转,适用于LL型
	private AVLNode<E> LL(AVLNode<E> k2) {
		AVLNode<E> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), k2.height) + 1;
		return k1;
	}

	// 带右子树旋转，适用于RR型
	private AVLNode<E> RR(AVLNode<E> k1) {
		AVLNode<E> k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
		k2.height = Math.max(height(k2.right), k1.height) + 1;
		return k2;
	}

	// 双旋转，适用于LR型
	private AVLNode<E> LR(AVLNode<E> k3) {
		k3.left = RR(k3.left);
		return LL(k3);
	}

	// 双旋转,适用于RL型
	private AVLNode<E> RL(AVLNode<E> k1) {
		k1.right = LL(k1.right);
		return RR(k1);
	}

	public int height(AVLNode<E> node) {
		return node == null ? -1 : node.height;
	}

	public static void printAVL(AVLNode<Integer> node) {
		if (node == null) {
			return;
		}
		printAVL(node.left);
		System.out.print(node.value+",h="+node.height + "|");
		printAVL(node.right);
	}

	public static void main(String[] args) {
		AVLDemo<Integer> tree = new AVLDemo<>();
		int[] arr = {3,2,1,4,5,6,7,16,15,14,13,12,11,10,8,9};
		for (int i = 0; i < arr.length; i++)
			tree.root = tree.add(arr[i], tree.root);
		printAVL(tree.root);
		// printLRR(tree.root);
	}
}

/**
 * 内部节点类
 */
class AVLNode<E extends Comparable<E>> {
	E value; // 元素对象
	AVLNode<E> left; // 左孩子节点
	AVLNode<E> right; // 右孩子节点
	int height = 0;

	public AVLNode(E value, AVLNode<E> left, AVLNode<E> right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
}

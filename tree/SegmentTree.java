package com.ds.tree;

import java.util.function.BiFunction;
import java.util.function.BiFunction.*;

public class SegmentTree<T extends Comparable<T>> {
	static class Node<T extends Comparable<T>> {
		private int leftRange;
		private int rightRange;
		private T data;
		private Node<T> leftChild;
		private Node<T> rightChild;

		public Node(int left, int right, T data) {
			leftRange = left;
			rightRange = right;
			this.data = data;
			leftChild = rightChild = null;
		}

		public String toString(){
			return "[ Range : start = " + this.leftRange + " End = " + this.rightRange + " require data = " + this.data;
		}
	}

	private Node<T> root;
	private T iden;
	private BiFunction<T, T, T> operation;
	private int length;

	public SegmentTree(T[] input, BiFunction<T, T, T> operation, T iden) {
		this.operation = operation;
		this.iden = iden;
		root = build(input, 0, input.length - 1);
		System.out.println("created sucessfully");
		length = input.length ;
	}

	private Node<T> build(T[] array, int start, int end) {
		if (start == end) {
			return new Node<T>(start, end, array[start]);
		}

		int mid = (start + end) / 2;

		Node<T> left = build(array, start, mid);
		Node<T> right = build(array, mid + 1, end);
		T merge = operation.apply(left.data, right.data);

		Node<T> node = new Node<T>(start, end, merge);
		node.leftChild = left;
		node.rightChild = right;
		return node;
	}

	public void display() {
		if (root == null) {
			System.out.println("tree is empty");
			return;
		}
		display(root);
	}

	private void display(Node<T> node) {
		if (node != null) {
			display(node.leftChild);
			System.out.println(node);
			display(node.rightChild);
		}
	}

	public void update(int index, T value) {
		if (value == null) {
			System.out.println("invalid data");
			return;
		}
		if (root == null) {
			System.out.println("tree is empty");
			return;
		}
		update(root, index, value);
	}

	private void update(Node<T> node, int index, T value) {
		if (node.leftRange == node.rightRange) {
			node.data = value;
			System.out.println("updated sucessfully");
			return;
		}

		int mid = (node.leftRange + node.rightRange) / 2;
		if (index <= mid) {
			update(node.leftChild, index, value);
		} else {
			update(node.rightChild, index, value);
		}

		node.data = operation.apply(node.leftChild.data, node.rightChild.data);
	}

	public T query(int start, int end) {
		if(end < 0 || end >= length ) {
			System.out.println("index out of bound");return null ;
		}
		if(root == null) {
			System.out.println("tree is empty");
			return null ;
		}
		return query(root, start, end);
	}

	private T query(Node<T> node, int start, int end) {

		if (start > node.rightRange || end < node.leftRange)
			return this.iden;

		if ( start <= node.leftRange && end >= node.rightRange ) {
			return node.data;
		}
		
		T left = query(node.leftChild, start, end);
		T right = query(node.rightChild, start, end);

		return operation.apply(left, right);
	}
}

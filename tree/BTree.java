package com.ds.tree;

import java.lang.reflect.Array;

public class BTree<T extends Comparable<T>> {
	static class Node<T extends Comparable<T>> {
		private int length;
		private int order;
		private boolean leaf;
		private Node<T>[] childrens;
		private T[] values;

		@SuppressWarnings("unchecked")
		public Node(int t, boolean leaf) {
			this.order = t;
			this.leaf = leaf;
			this.childrens = (Node<T>[]) new Node[2 * t];
			this.values = (T[]) new Comparable[2 * t - 1];
			this.length = 0;
		}
	}

	private Node<T> root = null;
	private int t = 0;

	public BTree(int m) {
		this.t = m;
		root = null;
	}

	public void insert(T key) {
		if (root == null) {
			root = new Node<T>(t, true);
			root.values[0] = key;
			root.length = 1;
		} else {
			if (root.length == 2 * t - 1) {
				Node<T> parent = new Node<T>(this.t, false);
				parent.childrens[0] = root;
				split(parent, 0, root);
				int i = key.compareTo(parent.values[0]) > 0 ? 1 : 0;
				insert(parent.childrens[i], key);
				root = parent;
			} else {
				insert(root, key);
			}
		}
		System.out.println("Inserted successfully");
	}

	private void insert(Node<T> node, T key) {
		int i = node.length - 1;

		if (node.leaf) {
			while (i >= 0 && key.compareTo(node.values[i]) < 0) {
				node.values[i + 1] = node.values[i];
				i--;
			}
			node.values[i + 1] = key;
			node.length++;
		} else {
			while (i >= 0 && key.compareTo(node.values[i]) < 0)
				i--;

			if (node.childrens[i + 1].length == 2 * t - 1) {
				split(node, i + 1, node.childrens[i + 1]);
				if (key.compareTo(node.values[i + 1]) > 0)
					i++;
			}

			insert(node.childrens[i + 1], key);
		}
	}

	public void delete(T key) {
		if (root == null ) {
			System.out.println("tree is empty");
			return;
		}
		if(key == null) {
			System.out.println("its null");
			return;
		}
		
		delete(root, key);

		if (root.length == 0) {
			if (root.leaf) {
				root = null;
			} else {
				root = root.childrens[0];
			}
		}
		System.out.println("deleted sucessfully");
	}

	private void delete(Node<T> node, T key) {
		int index = 0;
	    
	    while (index < node.length && key.compareTo(node.values[index]) > 0) {
	        index++;
	    }
		
		if (index < node.length && node.values[index].compareTo(key) == 0) {
			if (node.leaf) {
				solveLeaf(node, index);
			} else {
				solveInternal(node, index);
			}
		} else {
			if(node.leaf) {
				System.out.println("Element not present");return;
			}
			
			boolean i = (index == node.length) ;
			if(node.childrens[index].length < t) {
				fill(node,index) ;
			}
			
			if(i && index > node.length) {
				delete(node.childrens[index-1],key) ;
			}
			else {
				delete( node.childrens[index],key) ;
			}
		}
	}
	
	private void fill(Node<T> node , int index) {
		if(index != 0 && node.childrens[index].length >= t) {
			borrowFromLeft(node , index);
		}
		else if(index != node.length && node.childrens[index + 1].length >= t) {
			borrowFromRight(node , index) ;
		}
		else {
			if(index != node.length) {
				merge(node,index) ;
			}
			else {
				merge(node , index - 1) ;
			}
		}
	}
	
	private void borrowFromRight(Node<T> node , int index) {
		Node<T> child = node.childrens[index] ;
		Node<T> right = node.childrens[index + 1] ;
		
		child.values[child.length] = node.values[index] ;
		if(!child.leaf) {
			child.childrens[child.length+1] = right.childrens[0] ;
		}
		
		child.length++;
		node.values[index] = right.values[0] ;
		
		for(int i = 0 ; i < right.length ; i++) {
			right.values[i] = right.values[i+1] ;
		}
		if(!right.leaf) {
			for(int i = 0 ; i <= right.length ; i++) {
				right.childrens[i] = right.childrens[i+1] ;
			}
		}
		
		right.length--;
	}
	
	private void borrowFromLeft(Node<T> node , int index) {
		Node<T> child = node.childrens[index] ;
		Node<T> left = node.childrens[index - 1] ;
		
		for(int i = child.length - 1 ; i >= 0 ; i--) {
			child.values[i+1] = child.values[i] ;
		}
		
		if(!child.leaf) {
			for(int i = child.length ; i>= 0 ; i--) {
				child.childrens[i+1] = child.childrens[i] ;
			}
		}
		
		child.values[0] = node.values[index - 1];
		
		if(!child.leaf) {
			child.childrens[0] = left.childrens[left.length] ;
		}
		
		node.values[index-1] = left.values[left.length - 1] ;
		child.length++;
		left.length--;
	}
	
	private void solveLeaf(Node<T> node, int index) {

		for (int i = index; i < node.length - 1; i++) {
			node.values[i] = node.values[i + 1];
		}

		node.length--;
	}

	private void solveInternal(Node<T> node, int index) {

		if (node.childrens[index].length >= t) {
			T key = getPredeccessor(node, index);
			node.values[index] = key;
			delete(node.childrens[index], key);
		} else if (node.childrens[index + 1].length >= t) {
			T key = getSucessor(node, index);
			node.values[index] = key;
			delete(node.childrens[index + 1], key);
		} else {
			merge(node,index);
		}
	}
	
	private void merge(Node<T> node , int index) {
		Node<T> left = node.childrens[index];
		Node<T> right = node.childrens[index+1] ;
		
		left.values[t-1] = node.values[index] ;
		left.length++;
		for(int i = index ; i < node.length ; i++) {
			node.values[i] = node.values[i+1] ;
		}
		
		if(!node.leaf) {
			for(int i = index ; i <= node.length ; i++) {
				node.childrens[i] = node.childrens[i+1] ;
			}
		}
		
		node.childrens[index] = left ;
		
		for(int i = 0 ; i < right.length ; i++) {
			left.values[t+i] = right.values[i] ;
		}
		
		if(!left.leaf) {
			for(int i = 0 ; i <= right.length ; i++) {
				left.childrens[t+i] = right.childrens[i] ;
			}
		}
		
		left.length += right.length ;
	}

	private T getPredeccessor(Node<T> node, int index) {
		Node<T> current = node.childrens[index];

		while (!current.leaf) {
			current = current.childrens[current.length];
		}

		return current.values[current.length - 1];
	}

	private T getSucessor(Node<T> node, int index) {
		Node<T> current = node.childrens[index + 1];

		while (!current.leaf) {
			current = current.childrens[0];
		}

		return current.values[0];
	}

	private void split(Node<T> parent, int childNo, Node<T> leftChild) {
		Node<T> rightChild = new Node<T>(leftChild.order, leftChild.leaf);
		rightChild.length = t - 1;

		for (int i = 0; i < t - 1; i++) {
			rightChild.values[i] = leftChild.values[i + t];
		}

		if (!leftChild.leaf) {
			for (int i = 0; i < t; i++) {
				rightChild.childrens[i] = leftChild.childrens[i + t];
			}
		}

		leftChild.length = t - 1;

		// Shift parent children pointers correctly
		for (int i = parent.length; i >= childNo + 1; i--) {
			parent.childrens[i + 1] = parent.childrens[i];
		}

		parent.childrens[childNo + 1] = rightChild;

		// Shift parent values correctly
		for (int i = parent.length - 1; i >= childNo; i--) {
			parent.values[i + 1] = parent.values[i];
		}

		parent.values[childNo] = leftChild.values[t - 1];
		parent.length++;
	}

	public void traverse() {
		if (root == null) {
			System.out.println("Tree is empty");
			return;
		}
		traverse(root);
		System.out.println();
	}

	private void traverse(Node<T> node) {
		int i = 0;
		for (i = 0; i < node.length; i++) {
			if (!node.leaf) {
				traverse(node.childrens[i]);
			}
			System.out.print(node.values[i] + " ");
		}

		if (!node.leaf) {
			traverse(node.childrens[i]);
		}
	}

	public boolean search(T key) {
		return search(root, key) != null;
	}

	private Node<T> search(Node<T> node, T key) {
		int i = 0;
		while (i < node.length && key.compareTo(node.values[i]) > 0) {
			i++;
		}

		if (i < node.length && node.values[i].compareTo(key) == 0) {
			return node;
		}

		if (node.leaf) {
			return null;
		}

		return search(node.childrens[i], key);
	}

}

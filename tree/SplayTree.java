package com.ds.tree;

public class SplayTree<T extends Comparable<T>> {
	static class Node<T extends Comparable<T>>{
		private Node<T> left ;
		private T data ;
		private Node<T> right ;
		
		public Node(T data){
			this.data = data ;
			left = right = null ;
		}
	}
	
	private Node<T> root ;
	private int length ;
	
	public SplayTree() {
		this.root = null ;
		length = 0 ;
	}
	
	public int getLength() {
		return length ;
	}
	
	public Node<T> rightRotate(Node<T> y){
		Node<T> x = y.left ;
		Node<T> t = x.right ;
		
		x.right = y ;
		y.left = t ;
		
		return x ;
	}
	
	public Node<T> leftRotate(Node<T> y){
		Node<T> x = y.right ;
		Node<T> t = x.left ;
		
		x.left = y ;
		y.right = t ;
		
		return x ;
	}
	
	public Node<T> splay(Node<T> node , T data){
		
		if(node == null || node.data == data) {
			return node ;
		}
		
		if(data.compareTo(node.data) < 0) {
			//left subtree
			if(node.left == null) {
				return node ;
			}
			
			if(data.compareTo(node.left.data) < 0) {
				//right right case
				node.left.left = splay(node.left.left , data) ;
				node = rightRotate(node) ;
			}
			else if(data.compareTo(node.left.data) > 0) {
				//left right case
				node.left.right = splay(node.left.right, data) ;
				if(node.left.right != null) {
					node.left = leftRotate(node.left) ;
				}
			}
			
			return node.left == null ? node : rightRotate(node) ;
		}
		else {
			//right subtree
			if(node.right == null) {
				return node ;
			}
			//left left case
			if(data.compareTo(node.right.data) > 0) {
				node.right.right = splay(node.right.right, data) ;
				node = leftRotate(node) ;
			}
			//left right case 
			else if(data.compareTo(node.right.data) < 0) {
				node.right.left = splay(node.right.left, data) ;
				if(node.right.left != null) {
					node.right = rightRotate(node.right) ;
				}
			}
			
			return node.right == null ? node : leftRotate(node) ;
		}
	}
	
	public void insert(T data) {
		if(data == null) {
			System.out.println("invalid data...");return;
		}
		
		if(root == null) {
			this.root = new Node<T>(data) ;
			System.out.println("inserted sucessfully");
			return ;
		}
		
		root = insert(this.root , data) ;
	}
	
	public Node<T> insert(Node<T> node , T data){
		
		if(node.data.equals(data)) {
			return node ;
		}
		
		node = splay(node, data) ;
		Node<T> newnode = new Node<>(data) ;
		
		if(data.compareTo( node.data) > 0) {
			newnode.left = node ;
			newnode.right = node.right ;
			node.right = null ;
		}
		else {
			newnode.right = node ;
			newnode.left = node.left ;
			node.left = null ;
		}
		System.out.println("inserted sucessfully");length++;
		return newnode ;
	}
	
	public void inorder() {
		if(root == null) {
			System.out.println("tree is empty");return;
		}
		
		inorder(root) ;
		System.out.println();
	}
	
	
	public boolean search(T data) {
		if(root == null) {
			System.out.println("tree is empty");
			return false ;
		}
		
		this.root =   splay(root, data) ; 
		if(root.data.equals(data)) {
			return true ;
		}
		
		return false ;
	}
	
	public void delete(T data) {
		if(root == null) {
			System.out.println("tree is empty");
		}
		
		root = splay(root, data) ;
		if(root.data != data) {
			System.out.println("element not present");return ;
		}
		
		if(root.left == null) {
			root = root.right ;
			length -- ;System.out.println("deleted sucessfully");
		}
		else {
			Node<T> temp = root.right ;
			root = splay(root.left, data) ;
			root.right = temp ;
		}
		
		length-- ;
		System.out.println("deleted sucessfully...");
	}
	
	public Node<T> getSucessor(Node<T> node){
		while(node.right != null) {
			node = node.right ;
		}
		return node ;
	}
	
	public void inorder(Node<T> node) {
		if(node != null) {
			inorder(node.left) ;
			System.out.print(node.data+" ");
			inorder(node.right);
		}
	}
	
	public T getRoot() {
		return root == null ? null : root.data ;
	}
}

package com.ds.tree;

import java.util.Scanner;

public class BinarySearchTree<B extends Comparable<B>> {
	private class Branch<B extends Comparable<B>>{
		private Branch<B> left ;
		private B data ;
		private Branch<B> right ;
		private Scanner s = new Scanner(System.in);
		
		@SuppressWarnings("unused")
		public Branch(B data) {
			this.data = data ;
			left = right = null ;
		}
	}
	
	private Branch<B> root ;
	private int length ;
	private boolean flag = false ;
	
	public void insert(B data) {
		root = insert(root , data) ;
		System.out.println("inserted sucessfully");length++ ;
	}
	
	private Branch<B> insert(Branch<B> root, B data) {
	    if (root == null) {
	        root = new Branch<>(data);
	    } else if (data.compareTo(root.data) > 0) {
	        root.right = insert(root.right, data);
	    } else {
	        root.left = insert(root.left, data);
	    }
	    return root;
	}
	
	public boolean search(B data) {
		if(root == null) {
			System.out.println("root is empty");return false ;
		}
		if(data == null) {
			System.out.println("invalid data");return false ;	
		}
		Branch<B> current = search(data , root) ;
		if(current == null)
			return false ;
		
		return true ;
	}
	
	private Branch<B> search(B data , Branch<B> node){
		
		if(node == null)
			return null ;
		if(node.data.equals(data))
			return node ;
		else if(data.compareTo(node.data) > 0) {
			return search(data , node.right);
		}
		else {
			return search(data , node.left) ;
		}
	}

	public void delete(B data) {
		if(root == null) {
			System.out.println("tree is empty");
		}
		
		root = delete(data , root) ;
		if(!flag) {
			System.out.println("deleted sucessfully");
			length--;	
		}
	
	}
	
	public Branch<B> delete(B data , Branch<B> node) {
		 if(node == null) {
			 System.out.println("element not found");flag = true ;
			 return null ;
		 }
		 if(data.compareTo(node.data) < 0){
			 node.left = delete(data , node.left) ;
		 }
		 else if(data.compareTo(node.data) > 0) {
			 node.right = delete(data , node.right) ;
		 }
		 else {
			 
			 if(node.left == null && node.right == null)
				 return null ;
			 else if(node.left == null)
				 return node.right ;
			 else if(node.right == null)
				 return node.left ;
			 else {
				 Branch<B> sucessor = findMin(node.right);
				 node.data = sucessor.data ;
				 node.right = delete(sucessor.data , node.right) ;
			 }
		 }
		 
		 return node ;
	}
	
	public Branch<B> findMin(Branch<B> node) {
		while(node.left != null)
			node = node.left ;
		return node ;
	}
	
	public void inorder() {
		if(root == null) {
			System.out.println("tree is empty");return;
		}
		inorder(root) ;
		System.out.println();
	}
	
	public int getLength() {
		return length ;
	}
	
	public void preorder(Branch<B> root) {
		if(root != null) {
			System.out.print(root.data +" ");
			preorder(root.left);
			preorder(root.right);
		}
	}
	
	public void inorder(Branch<B> root) {
		if(root != null) {
			inorder(root.left);
			System.out.print(root.data +" ");
			inorder(root.right);
		}
	}
	
	public void postorder(Branch<B> root) {
		if(root != null) {
			postorder(root.left);
			postorder(root.right);
			System.out.print(root.data +" ");
		}
	}
	public B getRoot() {
		return root.data ;
	}
}

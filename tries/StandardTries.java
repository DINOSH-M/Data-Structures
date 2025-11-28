package com.ds.tries;

public class StandardTries {
	static class Node{
		private Node[] node ;
		private boolean isEnd ;
		private int currentLength ;
		
		public Node() {
			node = new Node[26] ;
			isEnd = false ;
			currentLength = 0 ;
		}
		
		public boolean isEmpty() {
			return node.length > 0 ;
		}
	}
	
	private Node root ;
	private int length ;
	
	public StandardTries() {
		root = new Node() ;
		length = 0 ;
	}
	
	public void insert(String word) {
		word = word.toLowerCase();
		Node current = root ;
		for(char ch : word.toCharArray()) {
		   int index = ch - 'a' ;
		   if(current.node[index] == null) {
			   current.node[index] = new Node();
			   current.currentLength++;
		   }
		   current = current.node[index] ;
		}
		if(!current.isEnd) {
			System.out.println("inserted sucessfully");
			current.isEnd = true ;
			length++;
		}
		else {
			System.out.println("Element already exists");
		}
	}
	
	public boolean search(String word) {
		word = word.toLowerCase();
		Node current = root ;
		
		for(char ch : word.toCharArray()) {
			int index = ch - 'a' ;
			if(current.node[index] == null) {
				return false ;
			}
			current = current.node[index] ;
		}
		
		return current.isEnd ;
	}
	
	public void display() {
		if(length == 0) {
			System.out.println("tries is empty");return;
		}
		
		display(root,"");
	}
	
	private void display(Node current , String word) {
		if(current.isEnd) {
			System.out.println(word);;
		}
		
		for(char ch = 'a' ; ch <= 'z' ;ch ++) {
			int index = ch - 'a' ;
			if(current.node[index] != null) {
				display(current.node[index] , word + ch) ;
			}
		}
	}
	
	public void delete(String word) {
		
		if(delete(word,root,0)) {
			System.out.println("deleted sucessfully");
			length--;
		}
		else {
			System.out.println("word not found");
		}
		
	}
	
	public boolean delete(String word , Node node , int index) {
		if(index == word.length()) {
			if(!node.isEnd) {
				return false ;
			}
			node.isEnd = false ;
			return !isEmpty(node) ;
		}
		
		int charIndex = word.charAt(index) - 'a' ;
		if(node.node[charIndex] == null) {
			return false ;
		}
		
		boolean cur = delete(word,node.node[charIndex],index+1) ;
		
		if(cur) {
			//node.node[charIndex] ;
			node.currentLength--;
			return !isEmpty(node) && !node.isEnd ;
		}
		return false;
	}
	
	public int getWordLength() {
		return length ;
	}
	
	private boolean isEmpty(Node node) {
		return !node.isEmpty() ;
	}
}

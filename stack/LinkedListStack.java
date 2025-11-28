package com.ds.stack;

public class LinkedListStack<T>{
	static class Node<T>{
		T data ;
		Node<T> next ;
		
		public Node(T data){
			this.data = data ;
			next = null ;
		}
	}
	
	private Node<T> top = null ;
	private int length = 0 ;
	
	public int getLength() {
		return length ;
	}
	
	public void isEmpty() {
		if(top == null)
			System.out.println("stack is empty");
		else {
			System.out.println("stack is not empty");
		}
	}
	
	public void push(T data) {
		Node<T> node = new Node<T>(data) ;
		length++;
		if(top == null) {
			top = node ;
			System.out.println("inserted sucessfully...");
			return;
		}
		
		node.next = top ;
		top = node ;
		System.out.println("inserted sucessfully...");
	}
	
	public T pop() {
		if(top == null) {
			System.out.println("underflow condition..");
			return null;
		}
		length--;
		T value = top.data ;
		top = top.next ;
		
		return value ;
	}
	
	public T peek() {
		if(top == null) {
			System.out.println("underflow condition..");
			return null;
		}
		return top.data ;
	}
	
	public void update(int position , T data) {
		if(top == null) {
			System.out.println("underflow condition..");
			return ;
		}
		
		if(position < 0 || position >= length ) {
			System.out.println("Index out of Bound");
			return;
		}
		
		Node<T> temp = top ;
		for(int i = 0 ; i <= position - 1 ; i++) {
			temp = temp.next ;
		}
		temp.data = data ;
		System.out.println("updated sucessfully");
	}
	
	public void display() {
		if(top == null) {
			System.out.println("underflow condition..");
			return ;
		}
		Node<T> temp = top ;
		System.out.print("Elements are : ");
		while(temp != null) {
			System.out.print(temp.data + " -> ");
			temp = temp.next ;
		}
		System.out.print("null");
	}
}

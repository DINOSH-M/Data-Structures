package com.ds.linkedlist;

public class CircularLinkedList<T> {
	static class Node<T>{
		T data ;
		Node<T> next ;
		
		public Node(T data) {
			this.data = data ;
			next = null ;
		}
	}
	
	private Node<T> head ;
	private int length ;
	
	public int getLength() {
		return length ;
	}
	
	public void insert(int position , T data) {
		
		Node<T> node = new Node<>(data) ;
		if(head == null) {
			head = node;
			node.next = head ;
			System.out.println("Inserted sucessfully..");length++;
			return ;
		}
		Node<T> temp = head ;
		
		if(position == 0 ) {
			do {
				temp = temp.next ;
			}while(temp.next != head);
			
			node.next = head ;
			head = node ;
			temp.next = head ;
			System.out.println("Inserted sucessfully..");length++;
			return ;
		}
		if(position >= length) {
			while(temp.next != head)
				temp = temp.next ;
			
			node.next = head ;
			temp.next = node ;
			System.out.println("Inserted sucessfully..");length++;
			return;
		}
		for(int i = 1 ; i < position-1 ; i++){
			temp = temp.next ;
		}
		
		 node.next = temp.next ;
		 temp.next = node ;
		 
		System.out.println("Inserted sucessfully..");length++;
	}
	
	public void delete(int position) {
		if(position < 0 || position > length) {
			System.out.println("index out of bound...");
			return;
		}
		length--;
		Node<T> temp = head ;
		if(position == 0) {
			while(temp.next != head)
				temp = temp.next ;
			
			head = head.next ;
			temp.next = head ;
			System.out.println("deleted sucessfully");
			return;
		}
		if(position == length) {
			while(temp.next.next != head)
				temp = temp.next ;
			
			temp.next = head ;
			System.out.println("deleted sucessfully");
			return;
		}
		
		for(int i = 1 ; i < position-1 ; i++){
			temp = temp.next ;
		}
		
		temp.next = temp.next.next ;
		System.out.println("deleted sucessfully..");
	}
	
	public void display() {
		if(head == null) {
			System.out.println("list is empty");
			return ;
		}
		Node<T> temp = head ;
		do {
			System.out.print(temp.data + " -> ");
			temp = temp.next ;
		}while(temp != head);
		System.out.print("null\n");
	}
	
	public T get(int position) {
		if(position < 0 || position >= length) {
			System.out.println("index out of bound...");
			return null;
		}
		if(head == null) {
			System.out.println("list is empty");
			return null;
		}
		Node<T> temp = head;
		for(int i = 0 ; i < position ; i++){
			temp = temp.next ;
		}
		return temp.data;
		
	}
	
	public void update(int position , T data) {
		if(position < 0 || position >= length) {
			System.out.println("index out of bound...");
			return;
		}
		if(head == null) {
			System.out.println("list is empty");
			return;
		}
		Node<T> temp = head;
		for(int i = 0 ; i < position ; i++){
			temp = temp.next ;
		}
		temp.data = data ;
		System.out.println("updated sucessfully");
	}
}

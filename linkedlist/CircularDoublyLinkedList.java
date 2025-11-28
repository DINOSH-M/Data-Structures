package com.ds.linkedlist;

public class CircularDoublyLinkedList<T> {
	
	static class Node<T>{
		Node<T> previous ;
		T data ;
		Node<T> next ;
		
		public Node(T data) {
			this.previous = null;
			this.data = data ;
			this.next = null ;
		}
	}
	
	private Node<T> head ;
	private Node<T> tail ;
	private int length ;
	
	public int getLength(){
		return length ;
	}
	
	public void insert(int position , T data) {
		Node<T> node = new Node<T>(data);
		if(head == null) {
			head = tail = node ;
			tail.next = head ;
			head.previous = tail ;
			System.out.println("inserted sucessfully..");length++ ;
			return;
		}
		if(position == 0) {
			node.next = head ;
			node.previous = tail ;
			head.previous = node ;
			tail.next = node ;
			head = node ;
			System.out.println("inserted sucessfully..");length++ ;
			return;
		}
		if(position >= length) {
			node.previous = tail ;
			node.next = head ;
			tail.next = node ;
			head.previous = node ;
			tail = node ;
			System.out.println("inserted sucessfully..");length++ ;
			return;
		}
		
		Node<T> temp = head ;
		for(int i = 0 ; i < position - 1 ; i++) {
			temp = temp.next ;
		}
		
		node.next = temp.next ;
		node.previous = temp ;
		temp.next.previous = node ;
		temp.next = node ;
		System.out.println("inserted sucessfully..");length++ ;
	}
	
	public void delete(int position) {
		if(head == null) {
			System.out.println("list is empty");
			return ;
		}
		if(position < 0 || position >= length) {
			System.out.println("Index out of bound..");
			return;
		}
		if (length == 0) {
	        head = tail = null;
	        System.out.println("Deleted successfully.");
	        return;
	    }
		length--;
		if(position == 0) {
			 head = head.next ;
			 head.previous = tail ;
			 tail.next = head ;
			 System.out.println("deleted sucessfully");return;
		}
		if(position == length ) {
			tail = tail.previous ;
			tail.next = head ;
			head.previous = tail ;
			System.out.println("deleted sucessfully");return;
		}
		Node<T> temp = head;
	    for (int i = 0; i < position - 1; i++) {
	        temp = temp.next;
	    }

	    temp.next = temp.next.next;
	    temp.next.previous = temp;
	    System.out.println("deleted sucessFully");
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


	public void display(boolean reverse) {
		if(head == null) {
			System.out.println("list is empty");
			return ;
		}
		if(!reverse) {
			Node<T> temp = head ;
			do {
				System.out.print(temp.data + " -> ");
				temp = temp.next ;
			}while(temp != head);
			System.out.print("null\n");
		}
		else {
			Node<T> temp = tail ;
			do {
				System.out.print(temp.data + " -> ");
				temp = temp.previous ;
			}while(temp != tail);
			System.out.print("null\n");
		}	
	}
	
	
}

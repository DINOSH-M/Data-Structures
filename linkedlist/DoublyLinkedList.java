package com.ds.linkedlist;


public class DoublyLinkedList<T> {
	
	static class Node<T>{
		Node<T> previous ;
		T data ;
		Node<T> next ;
		
		public Node(T data) {
			previous = null;
			this.data = data ;
			next = null;
		}
	}

	private int length ;
	private Node<T> head ;
	private Node<T> tail ;
	
	public int getLength() {
		return length ;
	}
	
	public void insert(int position , T data) {
		if(position < 0) {
			System.out.println("Invalid position - enter valid position");
			return;
		}
	    length++;
		Node<T> node = new Node<>(data);
		if(head == null) {
			head = tail = node ;
			System.out.println("inserted sucessfully");
			return ;
		}
		if(position == 0) {
			node.next = head ;
			node.previous = null ;
			head.previous = node ;
			head = node ;
			System.out.println("inserted sucessfully");
			return ;
		}
		
		if(position >= length) {
			tail.next = node ;
			node.previous = tail ;
			tail = node ;
			System.out.println("inserted sucessfully");
			return ;
		}
		
		Node<T> temp = head ;
		for(int i = 1 ; i < position - 1 ; i++) {
			temp = temp.next ;
		}
		
		node.next = temp.next ;
		node.previous = temp ;
		temp.next.previous = node ;
		temp.next = node ;

		System.out.println("inserted sucessfully");
	}
	
	public void delete(int position) {
		if(position < 0 || position > length) {
			System.out.println("Invalid position - enter valid position");
			return;
		}
	    
		if(head == null) {
			System.out.println("list is empty..");
			return ;
		}
		if(position == 0) {
			head = head.next ;
			head.previous = null ;
			System.out.println("deleted sucessfully");length--;
			return ;
		}
		
		if(position == length) {
			tail = tail.previous ;
			tail.next = null ;
			System.out.println("deleted sucessfully");length--;
			return ;
		}	
		
		 Node<T> temp = head ;
			for(int i = 1 ; i < position - 1 ; i++) {
				temp = temp.next ;
			}
			
		    temp.next = temp.next.next ;
		    temp.next.previous = temp ;
		    System.out.println("deleted sucessfully");length--;
	}
	
	public void display(boolean reverse) {
		 if(head == null) {
			 System.out.println("list is empty...");
			 return ;
		 }
		 if(!reverse) {
			 Node<T> temp = head ;
			 while(temp != null) {
				 System.out.print(temp.data + " -> ");
				 temp = temp.next ;
			 }
			 System.out.print("null\n");
		 }
		 else {
			 Node<T> temp = tail ;
			 while(temp != null) {
				 System.out.print(temp.data + " -> ");
				 temp = temp.previous ;
			 }
			 System.out.print("null\n");
		 }
	 }	
	
	public void update(int position , T data) {
		
		if(position < 0 || position >= length) {
			System.out.println("Invalid position - enter valid position");
			return;
		}
	    
		if(head == null) {
			System.out.println("list is empty..");
			return ;
		}
		Node<T> temp = head ;
		for(int i = 0 ; i < position ; i++) {
			temp = temp.next ;
		}
		temp.data = data ;
		System.out.println("updated sucessfully");
	}
	}


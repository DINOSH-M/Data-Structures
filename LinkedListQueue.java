package com.ds.queue;

public class LinkedListQueue<Q> {
	
	static class Node<Q>{
		public Q data ;
		public Node<Q> next ;
		
		public Node(Q data){
			this.data = data ;
			next = null ;
		}
	}
	
	private Node<Q> front = null ;
	private Node<Q> rear = null ;
	private int length = 0 ;
	
	public int getLength() {
		return length ;
	}
	
	public void isEmpty() {
		if(front == null)
		System.out.println("queue is Empty");
		else
		System.out.println("queue is NotEmpty");
		
	}
	
	public void enqueue(Q data) {
		length++;
		Node<Q> node = new Node<>(data) ;
		if(rear == null) {
			rear = front = node ;
		}
		else {
			rear.next = node ;
			rear = node ;
		}
		System.out.println("Enqueued Sucessfully");
	}
	
	public Q dequeue(){
		if(front == null) {
			System.out.println("queue is empty");
			return null;
		}
		length--;
		Q value = front.data ;
		if(rear == front) {
			rear = front = null ;
		}
		else {
			front = front.next ;
		}
		System.out.println("Dequeued sucessfully");
		return value ;
	}
	
	public Q peek() {
		if(front == null) {
			System.out.println("queue is empty");
			return null ;
		}
		return front.data ;
	}
	
	public void display() {
		if(front == null) {
			System.out.println("queue is empty..");
			return;
		}
		Node<Q> temp = front ;
		while(temp != null) {
			System.out.print(temp.data+" -> ");
			temp = temp.next ;
		}
		System.out.print("null\n");
	}
}

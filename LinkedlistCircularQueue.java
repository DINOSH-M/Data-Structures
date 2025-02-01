package com.ds.queue;

public class LinkedlistCircularQueue<Q> {
	static class Node<Q> {
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
	
	public void enqueue(Q data) {
		Node<Q> node = new Node<>(data) ;
		length++ ;
		if(front == null) {
			front = rear = node ;
			rear.next = front ;
		}
		else {
			rear.next = node ;
			rear = node ;
			rear.next = front ;
		}
		System.out.println("Enqueued sucessfully");
	}
	
	public Q dequeue() {
		if(front == null) {
			System.out.println("queue is empty");return null ;
		}length -- ;
		 Q value = front.data ;
		 if(front == rear) {
			front = rear = null ;
		}
		 else {
			 front = front.next ;
			 rear.next = front ;
		 }
		 System.out.println("dequeued Sucessfully");
		 return value ;
	}
	
	public void display() {
		if(front == null) {
			System.out.println("queue is empty");return ;
		}
		System.out.print("Elements : ");
		Node<Q> temp = front ;
		do {
			System.out.print(temp.data + " -> ");
			temp = temp.next ;
		}while(temp != front) ;
		System.out.print("null\n");
	}
	
	public Q peek() {
		if(front == null) {
			System.out.println("queue is empty");return null;
		}
		return front.data ;
	}
	
	public Q getRear() {
		if(rear == null) {
			System.out.println("queue is empty");return null;
		}
		return rear.data ;
	}
}

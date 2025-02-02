package com.ds.queue;

public class LinkedlistDoubleEndedQueue<Q> {
	static class Node<Q>{
		private Node<Q> next ;
		private Q data ;
		private Node<Q> previous ;
		
		public Node(Q data) {
			this.data = data ;
			next = null ;
			previous = null ;
		}
	}
	
	private Node<Q> front = null ;
	private Node<Q> rear = null ;
	private int length  = 0;
	
	public void enqueueFront(Q data) {
		Node<Q> node = new Node<>(data) ;
		if(front == null) {
			front = rear = node ;
		}
		else {
			node.next = front ;
			front.previous = node ;
			front = node ;
		}
		System.out.println("Enqueued sucessfully - front ");length++ ;
	}
	
	public void enqueueRear(Q data) {
		Node<Q> node = new Node<>(data) ;
		if(rear == null) {
			front = rear = node ;
		}
		else {
			rear.next = node ;
			node.previous = rear ;
			rear = node ;
		}
		System.out.println("Enqueued sucessfully - rear ");length++ ;
	}
	
	public Q dequeueFront() {
		if(front == null) {
			System.out.println("queue is empty !!! ") ;return null ;
		}
		
		Q value = front.data ;
		if(rear == front) {
			rear = front = null ;
		}
		else {
			front = front.next ;
			front.previous = null ;
		}
		System.out.println("Dequeued sucessfully - front ");length-- ;
		return value ;
	}
	
	public Q dequeueRear() {
		if(rear == null) {
			System.out.println("queue is empty !!!");return null ;
		}
		
		Q value = rear.data ;
		if(rear == front) {
			front = rear = null ;
		}
		else {
			rear = rear.previous ;
			rear.next = null ;
		}
		System.out.println("Dequeued sucessfully - rear ");length-- ;
		return value ;
	}
	
	public int getLength() {
		return length ;
	}
	
	public Q getRear() {
		if(rear == null) {
			System.out.println("queue is empty ");return null;
		}
		return rear.data ;
	}
	
	public Q getFront() {
		if(front == null) {
			System.out.println("queue is empty ");return null;
		}
		return front.data ;
	}
	
	public void display(boolean reverse) {
		if(front == null) {
			System.out.println("queue is empty ");return ;
		}
		
		if(!reverse) {
			Node<Q> temp = front ;
			while(temp != null) {
				System.out.print(temp.data + " -> ");
				temp = temp.next ;
			}
			System.out.print("null\n");
		}
		else {
			Node<Q> temp = rear ;
			while(temp != null) {
				System.out.print(temp.data + " -> ");
				temp = temp.previous ;
			}
			System.out.print("null\n");
		}
	}
}

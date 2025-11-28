package com.ds.queue;

public class CircularQueue<Q> {
	
	private int capacity ;
	private int length ;
	private Object[] queue ;
	
	public CircularQueue(int capacity) {
		this.capacity = capacity ;
		queue = new Object[capacity] ;
		length = 0 ;
	}
	
	private int front = -1 ;
	private int rear = -1 ;
	
	@SuppressWarnings("unchecked")
	public Q peek() {
		if(front == -1) {
			System.out.println("queue is empty");
			return null;
		}
		return (Q) queue[front] ;
	}
	public int getLength() {
		return length ; 
	}
	public void enqueue(Q data) {
		if((rear + 1)% capacity == front) {
			System.out.println("queue is full");
			return;
      }
		else if(rear == -1)
			rear = front = 0 ;
		else
			rear = (rear + 1) % capacity ;
			
			queue[rear] = data ;
		    length++; System.out.println("Enqueued successfully");
		}
	
	public Q dequeue() {
		if(front == -1){
			System.out.println("queue is empty");
			return null;
		}
		else {
			@SuppressWarnings("unchecked")
			Q value = (Q) queue[front] ;
			if(rear == front) 
				rear = front = -1 ;
			else
				front = (front + 1) % capacity ;
			
			length--;
			return value ;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public Q getRear() {
		if(front == -1) {
			System.out.println("queue is empty");
			return null;
		}
		return (Q) queue[rear] ;
	}
	
	public void display() {
		if(front == -1) {
			System.out.println("queue is empty");
			return ;
		}
		
		System.out.print("Elements : ");
		for(int i = 0 ; i < length ; i++) {
			System.out.print(queue[(front + i) % capacity] + " ");
		}
		System.out.println();
	}
}

package com.ds.queue;

public class ArrayQueue<Q> {
	
	private Object[] queue;
	private int size ;
	private int front ;
	private int rear ;
	private int length ;
	
	public ArrayQueue(int size) {
		this.size = size ;
		front = -1 ;
		rear = -1 ;
		queue = new Object[size] ;
		length = 0 ;
	}
	
	public int getSize() {
		return size ;
	}
	
	public void enqueue(Q data) {
		if(rear  == size - 1) {
			System.out.println("OverFlow Condition");
			return ;
		}
		length++;
		if(rear == -1)
			front = rear = 0 ;
		else
			rear++ ;
		
		queue[rear] = (Q) data ;
		System.out.println("enqueued sucessfully");
	}
	
	public Q dequeue() {
		if(front == -1) {
			System.out.println("UnderFlow condition");
			return null ;
		}
		length-- ;
		@SuppressWarnings("unchecked")
		Q value = (Q) queue[front] ;

		if(front == rear)
			front = rear = -1 ;
		else {
			front++ ;
		}
		
		for(int i = 1 ; i < rear ; i++)
			queue[i - 1] = queue[i] ;
		
		return value ;
	}
	
	@SuppressWarnings("unchecked")
	public Q peek() {
		if(front == -1) {
			System.out.println("queue is empty");
			return null ;
		}
		
		return (Q) queue[front] ;
	}
	
	@SuppressWarnings("unchecked")
	public Q rear() {
		if(rear == -1) {
			System.out.println("queue is empty");
			return null ;
		}
		
		return (Q) queue[rear] ;
	}
	
	public void isEmpty() {
		if(front == -1)
		System.out.println("queue is Empty");
		else
		System.out.println("queue is NotEmpty");
		
	}
	
	public void isFull() {
		if(rear == size - 1)
			System.out.println("queue is Full");
			else
			System.out.println("queue is NotFull");
			
	}
	
	public void display(boolean reverse) {
		if(front == -1) {
			System.out.println("queue is Empty");
			return;
		}
		
		System.out.print("queue Elements are : ");
		if(!reverse) {
			for(int i = front ; i <= rear ; i++) {
				System.out.print(queue[i]+" ");
			}
		}
		else {
			for(int i = rear ; i >= front ; i--) {
				System.out.print(queue[i]+" ");
			}
		}
		System.out.println();
	}
}

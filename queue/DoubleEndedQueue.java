package com.ds.queue;

public class DoubleEndedQueue<Q> {
    private int capacity;
    private int length;
    private int front;
    private int rear;
    private Object[] queue;

    public DoubleEndedQueue(int capacity) {
        queue = new Object[capacity];
        this.capacity = capacity;
        length = 0;
        front = rear = -1;
    }

    public void enqueueFront(Q data) {
        if (length == capacity) {
            System.out.println("Queue is full - front");
            return;
        }

        if (front == -1) { 
            front = rear = 0;
        } else {
            front = (front - 1 + capacity) % capacity; 
        }

        queue[front] = data;
        length++;
        System.out.println("Inserted successfully at front!");
    }

    public Q dequeueFront() {
        if (length == 0) {
            System.out.println("Queue is empty");
            return null;
        }

        @SuppressWarnings("unchecked")
        Q value = (Q) queue[front];

        if (front == rear) { 
            front = rear = -1;
        } else {
            front = (front + 1) % capacity;
        }

        length--;
        System.out.println("Deleted at front");
        return value;
    }

    public void enqueueRear(Q data) {
        if (length == capacity) {
            System.out.println("Queue is full - rear");
            return;
        }

        if (rear == -1) { 
            rear = front = 0;
        } else {
            rear = (rear + 1) % capacity;
        }

        queue[rear] = data;
        length++;
        System.out.println("Inserted successfully at rear!");
    }

    public Q dequeueRear() {
        if (length == 0) {
            System.out.println("Queue is empty.");
            return null;
        }

        @SuppressWarnings("unchecked")
        Q value = (Q) queue[rear];

        if (rear == front) { 
            rear = front = -1;
        } else {
            rear = (rear - 1 + capacity) % capacity; 
        }

        length--;
        System.out.println("Dequeued successfully - rear");
        return value;
    }
    
    @SuppressWarnings("unchecked")
	public Q getRear() {
    	 if (rear == -1) {
             System.out.println("Queue is empty.");
             return null;
         }
    	 return (Q)queue[rear] ;
    }
    
    @SuppressWarnings("unchecked")
	public Q getFront() {
    	 if (front == -1) {
             System.out.println("Queue is empty.");
             return null;
         }
    	 return (Q)queue[front] ;
    }
    
    public int getLength() {
		return length ;
	}
    
    public void display() {
        if (front == -1) {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.print("Elements: ");
        for (int i = 0; i < length; i++) {
            System.out.print(queue[(front + i) % capacity] +" ");
        }
        System.out.println();
    }
}

package com.ds.stack;

public class ArrayStack<T> {
	private Object[] stack ;
	private int top ;
	private int length ;
	
	public ArrayStack(int size) {
		stack = new Object[size] ;
		top = -1;
		length = size ;
	}
	
	public void setLength(int length) {
		this.length = length ;
	}
	
	public int getLength() {
		return length ;
	}
	
	public T peek() {
		if(top == -1) {
			System.out.println("stack is empty");
			return null;
		}
		@SuppressWarnings("unchecked")
		T item = (T) stack[top] ;
		return item;
	}
	
	public void push(T data) {
		if(top >= length-1) {
			System.out.println("overflow condition..");
			return;
		}
		
		stack[++top] = data ;
		System.out.println("inserted sucessfully");
	}
	
	public T pop() {
		if(top == -1) {
			System.out.println("underflow condition..");
			return null;
		}
		
		@SuppressWarnings("unchecked")
		T value = (T) stack[top--] ;
		System.out.println("poped sucessfully...");
		return value;
	}
	
	public void update( int position , T data) {
		if(top == -1) {
			System.out.println("underflow condition..");
			return ;
		}
		if(position < 0 || position >= length ) {
			System.out.println("Index out of Bound");
			return;
		}
		stack[position] = data ;
		System.out.println("updated sucessfully");
	}
	
	public void isEmpty() {
		if(top == -1)
			System.out.println("stack is empty");
		else {
			System.out.println("stack is not empty");
		}
	}
	
	public void isFull() {
		if(top == length - 1)
			System.out.println("stack is full");
		else {
			System.out.println("stack is not full");
		}
	}
	
	public void display() {
		if(top == -1) {
			System.out.println("underflow condition..");
			return ;
		}
		System.out.print("Elements are : ");
		for(int i = top ; i >= 0 ; i--) {
			System.out.print(stack[i] + " ");
		}
		
	}
}

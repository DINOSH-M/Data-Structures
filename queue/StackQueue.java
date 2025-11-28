package com.ds.queue;

import java.util.Stack;

public class StackQueue<Q> {
	private Stack<Q> stack1;
	private Stack<Q> stack2;
	private int length;

	public StackQueue() {
		stack1 = new Stack<>();
		stack2 = new Stack<>();
		length = 0;
	}

	public void enqueue(Q data) {
		stack1.push(data);
		System.out.println("inserted sucessfully");
		length++;
	}

	public Q dequeue() {
		if (stack1.isEmpty() && stack2.isEmpty()) {
			System.out.println("queue is empty");
			return null;
		}

		if (stack2.isEmpty()) {
			while (stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		}
		Q value = stack2.pop();
		return value;
	}

	public Q getFront() {
		if (stack1.isEmpty() && stack2.isEmpty()) {
			System.out.println("queue is empty");
			return null;
		}
		if (!stack2.isEmpty())
			return stack2.peek();
		else
			return stack1.firstElement();
	}

	public Q getRear() {
		if (stack1.isEmpty() && stack2.isEmpty()) {
			System.out.println("queue is empty");
			return null;
		}

		if (!stack1.isEmpty())
			return stack1.peek();
		else
			return stack2.firstElement();
	}

	public void display() {
		if (stack1.isEmpty() && stack2.isEmpty()) {
			System.out.println("queue is empty");
			return;
		}
		
		System.out.print("Elements are : ");
		for(int i = stack2.size() - 1 ; i >= 0 ; i--)
			System.out.print(stack2.get(i)+" ");
		for(int i = 0 ; i < stack1.size()  ; i++)
			System.out.print(stack1.get(i)+" ");
		
		System.out.println();
	}
}

package com.ds.heaps;

import java.util.ArrayList;

public class MinBinaryHeap<T extends Comparable<T>> {
	private ArrayList<T> heap;

	public MinBinaryHeap() {
		heap = new ArrayList<T>();
	}

	private int parent(int index) {
		return (index - 1) / 2;
	}

	private int leftChild(int index) {
		return 2 * index + 1;
	}

	private int rightChild(int index) {
		return 2 * index + 2;
	}

	public void insert(T key) {
		if (key == null) {
			System.err.println("null excepted");
			return;
		}

		int i = heap.size();
		heap.add(key);
		heapifyUp(i);
		System.out.println("inserted sucessfully");
	}

	private void heapifyUp(int index) {
		while (index >= 0 && heap.get(index).compareTo(heap.get(parent(index))) < 0) {
			swap(index, parent(index));
			index = parent(index);
		}
	}
	
	public void delete(T key) {
		if(key == null) {
			System.err.println("null is here ");return;
		}
		
		int index = find(key) ;
		if(heap.get(index).compareTo(key) != 0) {
			System.err.println("invalid key");return;
		}
		
		heap.set(index,heap.get(heap.size()-1)) ;
		heap.remove(heap.size()-1) ;
		
		if(heap.get(index).compareTo(heap.get(parent(index))) < 0) {
			heapifyUp(index) ;
		}
		else {
			heapifyDown(index) ;
		}
		
		System.out.println("deleted sucessfully");
	}

	private void heapifyDown(int index) {
		int n = heap.size();
		while (index < n) {
			int left = leftChild(index);
			int right = rightChild(index);

			int min = index;

			if (left < n && heap.get(min).compareTo(heap.get(left)) > 0) {
				min = left;
			}

			if (right < n && heap.get(min).compareTo(heap.get(right)) > 0) {
				min = right;
			}

			if (index == min) {
				break;
			}

			swap(min, index);
			index = min;
		}
	}

	private int find(T key) {
		for (int i = 0; i < heap.size(); i++) {
			if (key.compareTo(heap.get(i)) == 0) {
				return i;
			}
		}

		return 0;
	}
	
	public void update(T oldKey , T newKey) {
		if(oldKey == null || newKey == null) {
			System.err.println("null is here");return;
		}
		
		int index = find(oldKey) ;
		if(heap.get(index).compareTo(oldKey) != 0) {
			System.err.println("invalid key");return;
		}
		
		heap.set(index, newKey);
		
		if(heap.get(index).compareTo(heap.get(parent(index))) < 0) {
			heapifyUp(index);
		}
		else {
			heapifyDown(index) ;
		}
		System.out.println("updated sucessfully");
	}
	
	public boolean search(T key) {
		if(key == null) {
			System.err.println("null is here");return false;
		}
		
		int index = find(key) ;  
		
		return heap.get(index).compareTo(key) == 0;
	}
	
	private void swap(int current, int parent) {
		T temp = heap.get(parent);
		heap.set(parent, heap.get(current));
		heap.set(current, temp);
	}

	public void traverse() {
		System.out.println(heap);
	}
	
	public int size() {
		return heap.size() ;
	}
	
	public T getMin() {
		return heap.get(0);
	}
}

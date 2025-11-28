package com.ds.heaps;

public class MaxBinaryHeap<T extends Comparable<T>> {

	private T[] heap;
	private int capacity;
	private int length;

	@SuppressWarnings("unchecked")
	public MaxBinaryHeap(int capacity) {
		this.capacity = capacity;
		heap = (T[]) new Comparable[capacity];
		length = 0;
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

	private void swap(int current, int parent) {
		T temp = heap[parent];
		heap[parent] = heap[current];
		heap[current] = temp;
	}

	public void insert(T key) {
		if (length == capacity) {
			System.out.println("heap is full");
			return;
		}

		int i = length;
		heap[i] = key;
		length++;
		heapifyUp(i);
		System.out.println("inserted sucessfully");
	}

	public T getMax() {
		return heap[0];
	}

	public boolean search(T key) {
		if (length == 0) {
			System.out.println("heap is full");
			return false;
		}

		int answer = find(key);
		return heap[answer].compareTo(key) == 0;
	}

	public void delete(T key) {
		if (length == 0) {
			System.out.println("heap is empty - delete overflow");
			return;
		}

		int index = find(key);
		if (key.compareTo(heap[index]) != 0) {
			System.out.println("invalid key..");
			return;
		}

		swap(length - 1, index);
		heap[length - 1] = null;
		length--;

		int parent = parent(index);

		if (heap[index].compareTo(heap[parent]) > 0) {
			heapifyUp(index);
		} else {
			heapifyDown(index);
		}

		System.out.println("deleted sucessfully");
	}

	public void update(T oldKey, T newKey) {

		if (oldKey == null || newKey == null) {
			System.out.println("invalid keys.. to update");
			return;
		}

		int index = find(oldKey);
		if (oldKey.compareTo(heap[index]) != 0) {
			System.out.println("invalid key");
			return;
		}

		heap[index] = newKey;
		int parent = parent(index);

		if (heap[parent].compareTo(heap[index]) < 0) {
			heapifyUp(index);
		} else {
			heapifyDown(index);
		}

		System.out.println("updated sucessfully");
	}

	public void traverse() {
		if (length == 0) {
			System.out.println("heap is empty ");
			return;
		}

		for (int i = 0; i < length; i++) {
			System.out.print(heap[i] + " ");
		}
		System.out.println();
	}

	public int size() {
		return length;
	}

	private void heapifyUp(int index) {

		while (index >= 0 && heap[index].compareTo(heap[parent(index)]) > 0) {
			swap(index, parent(index));
			index = parent(index);
		}
	}

	private int find(T data) {
		for (int i = 0; i < length; i++) {
			if (data.compareTo(heap[i]) == 0) {
				return i;
			}
		}
		return 0;
	}

	private void heapifyDown(int index) {
		while (index < length) {
			int left = leftChild(index);
			int right = rightChild(index);
			int max = index;

			if (left < length && heap[max].compareTo(heap[left]) < 0) {
				max = left;
			}

			if (right < length && heap[max].compareTo(heap[right]) < 0) {
				max = right;
			}

			if (index == max) {
				break;
			}

			swap(max, index);
			index = max;
		}
	}
}

package com.ds.tree;

public class FenwickTree {
	private int[] fenwickTree ;
	private int length ;
	
	public FenwickTree(int[] array) {
		this.length = array.length ;
		fenwickTree = new int[length+1] ;
		
		for(int i = 0 ; i < length ; i++) {
			update(i+1 , array[i]);
		}
		
		System.out.println("tree created sucessFully");
	}
	
	public void update(int index , int data) {
		while(index <= length) {
			fenwickTree[index] += data ;
			System.out.print("current : " + index);
			index = index + (index & (-index)) ;
			System.out.print(" next : "+index+"\n");
		}
	}
	
	private int sum(int index) {
		int sum = 0 ;
		while(index > 0 ) {
			sum += fenwickTree[index] ;
			System.out.print("current : " + index);
			index = index - (index & (-index)) ;
			System.out.print(" next : "+index+"\n");
		}
		return sum ;
	}
	
	public int rangesum(int left , int right) {
		if(right > left) {
			System.out.println("invalid index");
		}
		
		if(left == 0) {
			return sum(right) ;
		}
		
		return sum(right) - sum(left-1) ;
	}
	
	public void display() {
		for(int i : fenwickTree) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
}

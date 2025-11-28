package com.ds.tree;

import java.util.function.BiFunction;

public class SegmentTree2<T> {
	private T[] tree ;
	private int length ;
	private BiFunction<T,T,T> operation ;
	private T identity ;
	
	@SuppressWarnings("unchecked")
	public SegmentTree2(T[] array , BiFunction<T,T,T> op , T identity) {
		this.length = array.length ;
		tree = (T[]) new Object[4*length] ;
		this.identity = identity ;
		this.operation = op ;
		build(array ,0 , 0 , array.length - 1) ;
	}

	private void build(T[] array, int node, int start, int end) {
		if(start == end) {
			tree[node] = array[start] ;
		}
		else {
			int mid = (start + end ) / 2 ;
			build(array,2 * node + 1 , start , mid) ;
			build(array,2 * node + 2 , mid + 1 , end) ;
			T merge = operation.apply(tree[2*node+1],tree[ 2 * node + 2]) ;
			tree[node] = merge ;	
		}
	}
	
	public void update(int index , T value) {
		if(index < 0 || index >= length) {
			System.out.println("out of bounds");return;
		}
		update(0,0,length - 1 ,index,value) ;
	}
	
	private void update(int node , int start , int end , int index , T data) {
		if(start == end) {
			tree[node] = data ;
			System.out.println("updated sucessfully");return;
		}
		
		int mid = ( start + end ) / 2 ;
		if(index <= mid) {
			update(2*node+1 , start , mid , index , data) ;
		}
		else {
			update(2 * node +2 , mid + 1 , end , index , data) ;
		}
		
		tree[node] = operation.apply(tree[node * 2 + 1], tree[node * 2 + 2]) ;
	}
	
	public T query(int start , int end) {
		if(start < 0 || end >= length || start > end) {
			System.out.println("invalid rande");
			return identity ;
		}
		return query(0,0,length-1,start,end) ;
	}
	
	private T query(int node , int start , int end , int left , int right) {
		
		if(right < start || left > end) {
			return identity ;
		}
		else if(start >= left && end <= right) {
			return tree[node] ;
		}
		int mid = (start + end) / 2 ;
		T l = query( 2*node+1 , start ,mid , left , right) ;
		T r = query( 2*node+2 , mid+1 ,end , left , right) ;
		
		return operation.apply(l, r) ;
	}
	
	public void display() {
		for(T i : tree) {
			if(i != null) {
				System.out.print(i+" ");
			}
		}
		System.out.println();
	}
}

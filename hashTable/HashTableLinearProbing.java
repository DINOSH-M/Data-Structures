package com.ds.hashTable;

public class HashTableLinearProbing<K,V> {
	
	private int size ;
	private int capacity ;
	private K[] keys;
	private V[] values ;
	
	@SuppressWarnings("unused")
	private K EMPTY_NODE = null;
	private int INITIAL_CAPACITY = 10 ;
	private float LOAD_FACTOR = 0.5f ;
	@SuppressWarnings("unchecked")
	private K DELETED_NODE = (K) new Object() ;
	
	@SuppressWarnings("unchecked")
	public HashTableLinearProbing() {
		this.capacity = INITIAL_CAPACITY ;
		this.size = 0 ;
		keys = (K[]) new Object[capacity] ;
		values = (V[]) new Object[capacity] ;
		EMPTY_NODE = keys[0] ;
	}
	
	@SuppressWarnings("unchecked")
	public HashTableLinearProbing(int capacity) {
		this.capacity = capacity ;
		this.size = 0 ;
		keys = (K[]) new Object[capacity] ;
		values = (V[]) new Object[capacity] ;
		EMPTY_NODE = keys[0] ;
	}
	
	public int hashing(K key) {
		return key.hashCode() % capacity ;
	}
	
	public void add(K key , V value) {
		if(key == null || value == null) {
			System.out.println("Invalid key or value");return;
		}
		int index = hashing(key) ;
		while(keys[index] != null && keys[index] != DELETED_NODE) {
			if(keys[index].equals(key)) {
				values[index] = value ;
				System.out.println("updated sucessfully");
				return;
			}
			index = ( index + 1 ) % capacity ;
		}
		keys[index] = key ;
		values[index] = value ;
		size++ ;
		System.out.println("inserted sucessfully");
		
		if(size / capacity > LOAD_FACTOR) {
			System.out.println(size / capacity +" > " + LOAD_FACTOR);
			rehashing();
		}
	}
	
	public V get(K key) {
		if(key == null) {
			System.out.println("Invalid key");return null;
		}
		int index = hashing(key) ;
		while(keys[index] != null) {
			if(keys[index].equals(key))
				return values[index] ;
			index = index + 1 % capacity ;
		}
		System.out.println("key not found");
		return null ;
	}
	
	public void rehashing() {
		HashTableLinearProbing<K, V> temp = new HashTableLinearProbing<>(capacity * 2) ;
		for(int i = 0 ; i < capacity ; i++) {
			if(keys[i] != null)
				temp.add(keys[i] , values[i]);
		}
		
		keys = temp.keys ;
		values = temp.values ;
		capacity = temp.capacity ;
		System.out.println("rehashed sucessfully " + capacity);
	}
	
	public void delete(K key) {
		if(key == null) {
			System.out.println("Invalid key");return;
		}
		int index = hashing(key) , i = 1;
		while(keys[index] != null) {
			if(keys[index].equals(key) && keys[index] != DELETED_NODE) {
				keys[index] = DELETED_NODE ;
				values[index] = null ;
				System.out.println("deleted sucessfully");
				return;
			}
			index = (index + i ) % capacity ;
		}
		System.out.println("Key Not Found");
	}
	
	public void display() {
		if(size == 0) {
			System.out.println("table is empty");return;
		}
		System.out.println("Elements are : ");
		for(int i = 0 ; i < capacity ; i++) {
			
			if(keys[i] == null)
				System.out.println("index : "+i+" key : "+"EMPTY NODE");
			else if(keys[i] == DELETED_NODE)
				System.out.println("index : "+i+" key : "+"DELETED NODE");
			else
				System.out.println("index : "+i+" key : "+keys[i]+" - value : " + values[i]);
		}
	}
	
	public int getCapacity() {
		return capacity ;
	}
}

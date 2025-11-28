package com.ds.hashTable;

public class HashTableDoubleHashing<K , V> {
	static class Node<K,V>{
		private K key ;
		private V value ;
		
		public Node(K key , V value) {
			this.key = key ;
			this.value = value ;
		}
	}
	
	private Node<K,V>[] table ;
	private int capacity = 7;
	private int size ;
	private Node<K,V> DELETED_NODE = new Node<>(null,null) ;
	private float LOAD_FACTOR = 0.7f ;
	
	@SuppressWarnings("unchecked")
	public HashTableDoubleHashing() {
		table = new Node[capacity] ;
		size = 0 ;
	}
	
	@SuppressWarnings("unchecked")
	public HashTableDoubleHashing(int capacity) {
		this.capacity = capacity ;
		table = new Node[capacity] ;
		size = 0 ;
	}
	
	public int primaryHash(K key) {
		return Math.abs(key.hashCode() % capacity) ;
	}
	
	public int secondaryHash(K key) {
		return Math.abs(key.hashCode() % capacity + 1) % capacity  ;
	}
	
	public void add(K key , V value) {
		if(key == null || value == null) {
			System.out.println("invalid key or value ");return;
		}
		
		int index = primaryHash(key) ;
		int extra = secondaryHash(key) ;
		
		while(table[index] != null && table[index]!= DELETED_NODE) {
			if(table[index].key.equals(key)) {
				table[index].value = value ;
				System.out.println("updated sucessfully");return ;
			}
			index = ( index + extra) % capacity ;
		}
		
		table[index] = new Node<K, V>(key , value) ;
		size++;System.out.println("inserted sucessfully");
		
		if((float)size / capacity > LOAD_FACTOR) {
			System.out.println("we can rehash now - exceed threshold !!!");
			rehash();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void rehash() {
		Node<K,V>[] temp = table ;
		capacity *= 2 ;
		size = 0 ;
		table = new Node[capacity] ;
		
		for(Node<K,V> i : temp) {
			if(i != null)
			add(i.key , i.value) ;
		}
		System.out.println("rehashed sucessfully");
	}
	
	public V get(K key) {
		if(key == null) {
			System.out.println("Invalid key ");return null;
		}
		
		int index = primaryHash(key) , i = 1;
		int extra = secondaryHash(key) ;
		while(table[index] != null) {
			if(table[index].key == key)
				return table[index].value ;
			index = (index + i * i ) % capacity ;
		}
		
		System.out.println("key not found");
		return null;
	}
	
	public void delete(K key) {
		if(key == null) {
			System.out.println("invalid key");return;
		}
		
		int index = primaryHash(key) ;
		int extra = secondaryHash(key) ;
		
		while(table[index] != null) {
			if(table[index] != DELETED_NODE && table[index].key.equals(key)) {
				table[index] = DELETED_NODE ;
				System.out.println("deleted sucessfully");return;
			}
			index =  ( index + extra) % capacity ;
		}
		System.out.println("key not found");
	}
	
	public void display() {
		if(size == 0) {
			System.out.println("table is empty");return;
		}
		
		System.out.println("elements are : ");
		for(int i = 0 ; i < capacity ; i++) {
			if(table[i] == DELETED_NODE )
				System.out.println("index : "+i+" key : "+"DELETED NODE");
				else if(table[i] == null)
					System.out.println("index : "+i+" key : "+"EMPTY NODE");
				else
					System.out.println("index : "+i+" key : "+table[i].key+" - value : " + table[i].value);	
		}
	}
	
	public int getLength() {
		return size ;
	}
	
	public int capacity() {
		return capacity;
	}
}

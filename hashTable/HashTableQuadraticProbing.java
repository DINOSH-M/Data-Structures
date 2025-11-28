package com.ds.hashTable;

public class HashTableQuadraticProbing<K,V> {
	
	static class Node<K,V>{
		private K key ;
		private V value ;
	    public Node(K key , V value) {
	    	this.key = key ;
	    	this.value = value ;
	    }
	}
	
	private Node<K,V>[] table ;
	private int size ;
	private int capacity = 10;
	private final float LOAD_FACTOR = 0.50f ;
	@SuppressWarnings("unchecked")
	private Node<K,V> DELETED_NODE = new Node(null,null);
	
	@SuppressWarnings("unchecked")
	public HashTableQuadraticProbing() {
		table = new Node[capacity] ;
		size = 0 ;
	}
	
	@SuppressWarnings("unchecked")
	public HashTableQuadraticProbing(int capacity) {
		this.capacity = capacity ;
		table = new Node[capacity] ;
		size = 0 ;
	}
	
	public int hashing(K key) {
		return Math.abs(key.hashCode() % capacity) ;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void add(K key , V value) {
		if(key == null || value == null) {
			System.out.println("Invalid key or value ");return;
		}
		Node<K,V> join = new Node<>(key , value) ;
		int index = hashing(key) ;
		int i = 1 ;
		while(table[index] != null) {
			if(table[index].key == key) {
				table[index].value = value ;
				System.out.println("updated sucessfully");
				return;
			}	
			index = Math.abs((index + (i * i)) % capacity) ;i++;
		}
		table[index] = join ;
		size++;
		System.out.println("inserted sucessfully");
		if((float)size / capacity > LOAD_FACTOR ) {
			System.out.println("rehash require");
			 rehash();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void rehash() {
		Node<K,V>[] temp = table ;
		capacity *= 2 ;
		size = 0 ;
		table = new Node[capacity] ;
		for(Node<K,V> node : temp) {
			if(node != null)
			add(node.key , node.value) ;
		}
		System.out.println("sucessfully rehashed");
	}
	
	public V get(K key) {
		if(key == null) {
			System.out.println("Invalid key ");return null;
		}
		
		int index = hashing(key) , i = 1;
		while(table[index] != null) {
			if(table[index].key == key)
				return table[index].value ;
			index = (index + i * i ) % capacity ;
		}
		
		System.out.println("key not found");
		return null;
	}
	
	public int getSize() {
		return size ;
	}
	
	public void delete(K key) {
		if(key == null) {
			System.out.println("key is invalid");return;
		}
		
		int index = hashing(key) , i = 1;
		while(table[index] != null) {
			if(table[index] != DELETED_NODE && table[index].key.equals(key)) {
				table[index] =  DELETED_NODE ;size -- ;
				System.out.println("deleted sucessfully "); return ;
			}
			index = Math.abs((index + (i * i)) % capacity) ;i++;
		}
		System.out.println("key not found");
	}
	
	public void display() {
		if(size == 0) {
			System.out.println("table is empty ");return;
		}
		System.out.println("the elements are : ");
		for(int i = 0 ; i < capacity ; i++) {
			
				if(table[i] == DELETED_NODE )
				System.out.println("index : "+i+" key : "+"DELETED NODE");
				else if(table[i] == null)
					System.out.println("index : "+i+" key : "+"EMPTY NODE");
				else
					System.out.println("index : "+i+" key : "+table[i].key+" - value : " + table[i].value);	
			
		}
	}
}

package com.ds.hashTable;

public class HashTable<K , V> {
	private static class Entry<K , V>{
		private K key ;
		private V value ;
		private Entry<K,V> next ;
		public int length;
		
		@SuppressWarnings("unused")
		public Entry(K key , V value){
			this.key = key ;
			this.value = value ;
			next = null ;
		}
	}
	
	private int size = 0 ;
	private Entry<K , V>[] table ;
	private int INITIAL_CAPACITY = 10 ;
	private float LOAD_FACTOR = 0.7f ;
	
	@SuppressWarnings("unchecked")
	public HashTable() {
		size = 0 ;
		table = new Entry[INITIAL_CAPACITY];
	}
	
	@SuppressWarnings("unchecked")
	public HashTable(int size) {
		table = new Entry[size] ;
		INITIAL_CAPACITY = size ;
	}
	
	private int hashing(K key) {
		return key.hashCode() % table.length ;
	}
	
	public void add(K key , V value) {
		int index = hashing(key) ;
		Entry<K,V> newnode = new Entry<K, V>(key,value);
		
		if(table[index] == null) {
			table[index] = newnode ;
		}
		else {
			Entry<K,V> temp = table[index] ;
			while(temp != null) {
				if(temp.key.equals(key)) {
					temp.value = value ;return ;
				}
				temp = temp.next ;
			}
			newnode.next = table[index] ;
			table[index] = newnode ;
		}
		System.out.println("inserted sucessfully");
		size++;
		if(size / table.length > LOAD_FACTOR) {
			System.out.println("Elements exsists the threshold now rehashing require");
			rehash() ;
		}
	}
	
	public V get(K key) {
		V value = null ;
		int index = hashing(key);
		if(table[index] == null) {
			System.out.println("element not found...");return null;
		}
		
		Entry<K,V> temp = table[index] ;
		while(temp != null) {
			if(temp.key.equals(key)) {
				size--;System.out.println("Key found ");
				return temp.value ;
			}
			temp = temp.next ;
		}
		System.out.println("Key not found");
		return value ;
	}
	
	public void display() {
		if(size == 0) {
			System.out.println("table is empty");return;
		}
		
		System.out.println("elements are : ");
		for(int i = 0 ; i < table.length ; i++) {
			 if(table[i] == null) {
				 continue;
			 }
			 Entry<K,V> temp = table[i] ;
			 System.out.print("Bucket "+i+" : ");
			 while(temp != null) {
				 System.out.print("( "+temp.key + " : " + temp.value + " ) -> ");
				 temp = temp.next ;
			 }
			 System.out.print("null\n");
		}
	}
	
	public void remove(K key) {
		if(size == 0) {
			System.out.println("table is empty");return;
		}
		if(key == null) {
			System.out.println("invalid key");return;
		}
		int index = hashing(key) ;
		Entry<K,V> temp = table[index] ;
		Entry<K,V> previous = null ;
		while(temp != null) {
			if(temp.key.equals(key)) {
				if(previous == null) {
					table[index] = null ;
				}
				else {
					previous.next = temp.next ;
				}
				size--;
				System.out.println("Element deleted sucessflly");
				return;
			}
			previous = temp ;
			temp = temp.next ;
		}
		System.out.println("invalid key");
	}
	
	public boolean isContain(K key) {
		int index = hashing(key) ;
		if(table[index] == null) {
			return false ;
		}
		Entry<K,V> temp = table[index] ;
		while(temp != null) {
			if(temp.key.equals(key))
				return true ;
			temp = temp.next ;
		}
		return false ;
	}
	
	@SuppressWarnings("unchecked")
	public void rehash() {
		Entry<K, V>[] oldtable = table ;
		table = new Entry[oldtable.length * 2] ;
		size--;
		for(Entry<K,V> entry : oldtable) {
			while(entry != null) {
				add(entry.key , entry.value);
				entry = entry.next ;
			}
		}
		System.out.println("sucessfully rehashed");
	}
	
	public int getSize() {
		return size ;
	}
}

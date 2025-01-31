
// fully 1st index based not 0:

package com.ds.linkedlist;

class Node<T>{
	T data ;
	Node<T> next ;
	
	public Node( T data){
		this.data = data ;
		this.next = null ;
		
	}
}

public class SinglyLinkedList<T> {
	
	  private Node<T> head ;
	  private int length = 0;
	   
	   public SinglyLinkedList() {
		   head = null ;
	   }
	   
	   public int getLength() {
		   return length ;
	   }
	   
	  public void insert(T data) {
		  Node<T> node = new Node<>(data);
		  if(head == null) {
			   head = node ;
			   System.out.println("Inserted sucessfully..");
			   length++;
			   return;
		   }
		  Node<T> temp = head ;
		  while(temp.next != null) {
			  temp = temp.next ;
		  }
		  
		  temp.next = node ; 
		  System.out.println("Inserted sucessfully..");
		  length++;
	  }
	  
	  public void insert( int position , T data) {
		  if(position < 0){
			  System.out.println("Index out of bounds");
			  return ;
		  }
		  
		  if(position > length) {
			  insert(data);
			  return;
		  }
		     Node<T> node = new Node<>(data) ;
		     if(position == 0 ) {
		    	 node.next = head;
		    	 head = node ;
		    	 System.out.println("Inserted sucessfully..");
		    	 length++;
		    	 return ;
		     }
		     
		   if(head == null) {
			   head = node ;
			   System.out.println("Inserted sucessfully..");
			   length++;
			   return;
		   }
		   Node<T> temp = head ;
		   
		   for(int i = 0 ; i < position - 1; i++) {
			   temp = temp.next ;
		   }   
		   node.next = temp.next ;
		   temp.next = node ;
		   System.out.println("Inserted sucessfully..");
		   length++ ;
	  }
	  
	  public void delete(int index) {
		  if(index < 0 || index > length) {
			  System.out.println("index out of bound for length "+length);
			  return;
		  }
		  if(head == null) {
			  System.out.println("list is empty...");
			  return;
		  }
		  
		  if(index == 0) {
			  head = head.next;
			  System.out.println("deleted sucessfully...");
			   length-- ;
			   return;
		  }
		    Node<T> temp = head ;
		    for(int i = 1 ; i < index - 1; i++) {
		    	temp = temp.next ;
		    }
		    temp.next = temp.next.next ;
		    System.out.println("deleted sucessfully..");
			   length-- ;
	  }
	    
	  public void display() {
		  if(head == null) {
			  System.out.println("list is empty...");
			  return ;
		  }
		  Node<T> temp = head ;
		  while(temp != null) {
			  System.out.print(temp.data +" -> ");
			  temp = temp.next ;
		  }
		  System.out.print("null\n");
	  }
	  
	  public void update(int position , T data) {
		  if(position < 0 || position > length) {
			  System.out.println("Index out of bound. enter valid index");
			  return;
		  }
		  if(head == null) {
			  System.out.println("list is empty...");
			  return ;
		  }
		  Node<T> temp = head ;
		  for(int i = 1 ; i < position ; i++) {
			  temp = temp.next ;
		  }
		  temp.data = data ;
		  System.out.println("updated sucessfully...");
	  }
	  
	  public T get(int index) {
		  if(index < 0 || index > length) {
			  System.out.println("Index out of bound. enter valid index");
			  return null;  
		  }
			  
		  if(head == null) {
			  System.out.println("list is empty...");
			  return null;
		  }
		  
		  Node<T> temp = head ;
		  for(int i = 1 ; i < index ; i++) {
			  temp = temp.next ;
		  }
		return temp.data;
		  
	  }
	  
	  public int search(T data) {
		  if(head == null) {
			  System.out.println("list is empty...");
			  return 0;
		  }
		  
		  Node<T> temp = head ;
		  int count = 0 ;
		  while(temp != null) {
			  if(temp.data == data)
			  break ;
			  count++;
			  temp = temp.next ;
		  }
		 
		  if(temp == null) {
			  System.out.println("Element not found");
			  return 0 ;
		  }
		return count;
	  }
}


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
	   Node<T> tail ;
	  private int length = 0;
	   
	   public SinglyLinkedList() {
		   head = null ;
	   }
	   
	   public int getLength() {
		   return length ;
	   }
	   
	  public void insertAtBegining(T data) {
		  
		  Node<T> node = new Node<>(data);
		  
		  if(head == null) {
			  tail = node ;
			  head = node ;
			  System.out.println("Inserted sucessfully..");
		  }
		  else {
			  node.next = head ;
			  head = node ;
			  System.out.println("Inserted sucessfully..");
		  }
		  length ++ ;
		 
	  }
	  
	  public void insertAtEnd(T data) {
		  Node<T> node = new Node<>(data) ;
		  
		  if(head == null) {
			  tail = node ;
			  head = node ;
			  System.out.println("Inserted sucessfully..");
		  }
		  else {
			   tail.next = node ;
			   tail = node ;
			   System.out.println("Inserted sucessfully..");
		  }
		  length++;
	  }
	  
	  public void insertAtPosition(int pos , T data) {
		  if(pos == 1) {
			  insertAtBegining(data);
			  return;
		  }
			  
		  if(pos == length) {
			  insertAtEnd(data);
			  return ;
		  }
			  
		  Node<T> node = new Node<>(data) ;
		  if(pos < 0 || pos > length) {
			  System.out.println("Index out of bound. enter valid index");
			  return;  
		  }
		  if(head == null) {
			  head = node ;
			  tail = node ;
			  System.out.println("Inserted sucessfully..");
		  }
		  else {
			  Node<T> temp = head ;
			  for(int i = 1 ; i < pos-1 ; i++)
				  temp = temp.next ;
			  node.next = temp.next ;
			  temp.next = node ;
			  System.out.println("Inserted sucessfully..");
		  }
		  
		  length++;
	  }
	    
	  public void deleteAtBegining() {
		  if(head == null) {
			  System.out.println("list is empty...");
			  return;
		  }
		  else {
			  head = head.next ;
			  System.out.println("deleted sucessfully...");
		  }
		  length--;
	  }
	  
	  public void deleteAtPosition(int pos) {
		  if(pos < 0 || pos > length) {
			  System.out.println("Index out of bound. enter valid index");
			  return;  
		  }
		  
		  if(pos == 1) {
			  deleteAtBegining();
			  return;
		  }
		  if(pos == length) {
			  deleteAtEnd();
			  return;
		  }
		  
		  if(head == null) {
			  System.out.println("list is empty...");
			  return ;
		  }
		  else {
			  Node<T> temp = head ;
			  for(int i = 1 ; i < pos - 1 ; i++) {
				  temp = temp.next ;
			  }
			  
			  temp.next = temp.next.next ;
			  System.out.println("deleted sucessfully...");
		  }
		 length--;
	  }
	  
	  public void deleteAtEnd() {
		  if(head == null) {
			  System.out.println("list is empty...");
			  return;
		  }
		  Node<T> temp = head ;
		  while(temp.next != tail)
			  temp = temp.next ;
		  
		  temp.next = null ;
		  tail = temp ;
		 
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

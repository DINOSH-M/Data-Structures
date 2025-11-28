package com.ds.graph;

public class AdjacentMatrix<T> {
	private int length;
	private T[] vertex ;
	private int[][] matrix;
	private int connected = 1;
	private int disConnect = 0 ;

	@SuppressWarnings("unchecked")
	public AdjacentMatrix(T[] vertex) {
		
		this.length = vertex.length;
		this.vertex = vertex ;
		matrix = new int[length][length] ;
		System.out.println("Graph created");
	}
	
	
	public void addEdges(int i , int j) {
		if(matrix[i][j] == connected) {
			System.out.println("already connected");return;
		}
		
		matrix[i][j] = connected ;
		System.out.println("connected from "+i+" to "+j);
	}
	
	public void removeEdges(int i , int j) {
		if(matrix[i][j] == disConnect) {
			System.out.println("already disconnected");return;
		}
		
		matrix[i][j] = disConnect ;
		System.out.println("disconnected from "+i+" to "+j);
	}
	
	public void display() {
		System.out.print("V ");
		for(int i = 0 ; i < length ; i++) {
			System.out.print(vertex[i]+" ");
		}
		System.out.println();
		for(int i = 0 ; i < length ; i++) {
			System.out.print(vertex[i]+" ");
			for(int j = 0 ; j < length ; j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
}

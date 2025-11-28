package com.ds.tree;
//package com.ds.tree;

public class AvlTree<B extends Comparable<B>> {
	static class Branch<B extends Comparable<B>> {
		private int height;
		private Branch<B> left, right;
		private B data;

		public Branch(B key) {
			this.data = key;
			this.left = this.right = null;
			this.height = 1;
		}
	}

	private Branch<B> root;
	private int length;

	public int getHeight(Branch<B> node) {
		return (node == null) ? 0 : node.height;
	}

	public Branch<B> rightRotate(Branch<B> y) {
		System.out.println("rr");
		Branch<B> x = y.left;
		Branch<B> temp = x.right;

		x.right = y;
		y.left = temp;

		y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
		x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

		return x;
	}

	public B getRoot() {
		return (root != null) ? root.data : null;
	}

	public Branch<B> leftRotate(Branch<B> x) {
		System.out.println("lr");
		Branch<B> y = x.right;
		Branch<B> temp = y.left;

		y.left = x;
		x.right = temp;

		x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
		y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));

		return y;
	}

	public void insert(B data) {
		if (data == null) {
			System.out.println("Invalid data");
			return;
		}

		root = insert(root, data);
		length++;
	}

	public int getBalance(Branch<B> node) {
		return (node == null) ? 0 : getHeight(node.left) - getHeight(node.right);
	}

	public Branch<B> insert(Branch<B> root, B data) {
		if (root == null) {
			return new Branch<>(data);
		}

		if (data.compareTo(root.data) > 0) {
			root.right = insert(root.right, data);
		} else if (data.compareTo(root.data) < 0) {
			root.left = insert(root.left, data);
		} else {
			return root;
		}

		root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
		int balance = getBalance(root);

		if (balance > 1 && data.compareTo(root.left.data) < 0) {
			return rightRotate(root);
		}

		if (balance < -1 && data.compareTo(root.right.data) > 0) {
			return leftRotate(root);
		}

		if (balance > 1 && data.compareTo(root.left.data) > 0) {
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}

		if (balance < -1 && data.compareTo(root.right.data) < 0) {
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}

		return root;
	}

	public void inorder() {
		if (root == null) {
			System.out.println("Tree is empty");
			return;
		}
		inorder(root);
		System.out.println();
	}

	private void inorder(Branch<B> root) {
		if (root != null) {
			inorder(root.left);
			System.out.print(root.data + " ");
			inorder(root.right);
		}
	}
	
	public Branch<B> getMin(Branch<B> node){
		while(node.left != null)
			node = node.left ;
		
		return node ;
	}
	
	public void delete(B key) {
		if(key == null) {
			System.out.println("invalid key");return ;
		}
		root = delete(root , key) ;
	}
	
	public Branch<B> delete(Branch<B> root , B data){
		if(root == null) {
			System.out.println("element not found");return null ;
		}
		
		if(data.compareTo(root.data) > 0) {
			root.right = delete(root.right , data) ;
		}
		else if(data.compareTo(root.data) < 0) {
			root.left = delete(root.left , data) ;
		}
		else {
			if(root.left == null && root.right == null)
				return null ;
			else if(root.left == null)
				return root.right ;
			else if(root.right == null)
				return root.left ;
			else {
				Branch<B> sucessor = getMin(root.right) ;
				root.data = sucessor.data ;
				root.right = delete(root.right , sucessor.data) ;
			}
		}
		
		root.height = Math.max(getHeight(root.left),getHeight(root.right)) + 1;
		int balance = getBalance(root) ;
		
		if (balance > 1 && data.compareTo(root.left.data) < 0) {
			return rightRotate(root);
		}

		if (balance < -1 && data.compareTo(root.right.data) > 0) {
			return leftRotate(root);
		}

		if (balance > 1 && data.compareTo(root.left.data) > 0) {
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}

		if (balance < -1 && data.compareTo(root.right.data) < 0) {
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}


		return root ;
	}
}

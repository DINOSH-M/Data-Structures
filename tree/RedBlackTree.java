package com.ds.tree;

public class RedBlackTree<T extends Comparable<T>> {

    static class Node<T extends Comparable<T>> {
        private Node<T> left, right, parent;
        private T data;
        private char colour;

        public Node(T data) {
            this.data = data;
            right = left = parent = null;
            colour = 'R';
        }
    }

    private Node<T> root;

    public RedBlackTree() {
        this.root = null;
    }

    public Node<T> leftRotate(Node<T> y) {
        Node<T> x = y.right;
        Node<T> t = x.left;

        x.left = y;
        y.right = t;
        x.parent = y.parent;
        y.parent = x;

        if (t != null) t.parent = y;

        if (x.parent == null) root = x;
        else if (x.parent.left == y) x.parent.left = x;
        else x.parent.right = x;

        return x;
    }

    public Node<T> rightRotate(Node<T> y) {
        Node<T> x = y.left;
        Node<T> t = x.right;

        x.right = y;
        y.left = t;
        x.parent = y.parent;
        y.parent = x;

        if (t != null) t.parent = y;

        if (x.parent == null) root = x;
        else if (x.parent.left == y) x.parent.left = x;
        else x.parent.right = x;

        return x;
    }

    public void insert(T data) {
        if (root == null) {
            root = new Node<>(data);
            root.colour = 'B';
        } else {
            root = insert(root, data);
        }
        
        System.out.println("inserted sucessfully");
    }

    private boolean ll = false, rr = false, rl = false, lr = false;

    public Node<T> insert(Node<T> node, T data) {
        boolean conflict = false;

        if (node == null) return new Node<>(data);

        if (data.compareTo(node.data) < 0) {
            node.left = insert(node.left, data);
            node.left.parent = node;

            if (node != root && node.colour == 'R' && node.left.colour == 'R') {
                conflict = true;
            }
        } else {
            node.right = insert(node.right, data);
            node.right.parent = node;

            if (node != root && node.colour == 'R' && node.right.colour == 'R') {
                conflict = true;
            }
        }

        if (ll) {
            node = leftRotate(node);
            node.colour = 'B';
            node.left.colour = 'R';
            ll = false;
        } else if (rr) {
            node = rightRotate(node);
            node.colour = 'B';
            node.right.colour = 'R';
            rr = false;
        } else if (rl) {
            node.right = rightRotate(node.right);
            node.right.parent = node;
            node = leftRotate(node);
            node.colour = 'B';
            node.left.colour = 'R';
            rl = false;
        } else if (lr) {
            node.left = leftRotate(node.left);
            node.left.parent = node;
            node = rightRotate(node);
            node.colour = 'B';
            node.right.colour = 'R';
            lr = false;
        }

        if (conflict) {
            if (node.parent.left == node) {
                if (node.parent.right == null || node.parent.right.colour == 'B') {
                    if (node.left != null && node.left.colour == 'R') rr = true;
                    else if (node.right != null && node.right.colour == 'R') lr = true;
                } else {
                    node.parent.right.colour = 'B';
                    node.colour = 'B';
                    if (node.parent != root) node.parent.colour = 'R';
                }
            } else {
                if (node.parent.left == null || node.parent.left.colour == 'B') {
                    if (node.left != null && node.left.colour == 'R') rl = true;
                    else if (node.right != null && node.right.colour == 'R') ll = true;
                } else {
                    node.parent.left.colour = 'B';
                    node.colour = 'B';
                    if (node.parent != root) node.parent.colour = 'R';
                }
            }
        }
        return node;
    }

    public boolean search(T data) {
        return search(root, data) != null;
    }

    private Node<T> search(Node<T> node, T data) {
        if (node == null || data == null) return null;
        if (node.data.equals(data)) return node;
        return data.compareTo(node.data) > 0 ? search(node.right, data) : search(node.left, data);
    }

    public void delete(T data) {
        Node<T> toDelete = search(root, data);
        if (toDelete != null) {
        	delete(root, toDelete);System.out.println("deleted "+toDelete.data);
        }
        
    }

    private void delete(Node<T> node, Node<T> v) {
        Node<T> u = getU(v);
        boolean dblack = (u == null || u.colour == 'B') && (v.colour == 'B');
        Node<T> parent = v.parent;

        if (u == null) {
            if (v == root) {
                root = null;
            } else {
                if (dblack) fixDoubleBlack(v);
                else if (getSibiling(v) != null) getSibiling(v).colour = 'R';

                if (v == parent.left) parent.left = null;
                else parent.right = null;
            }
            return;
        }

        if (v.left == null || v.right == null) {
            if (v == root) {
                root = u;
                root.colour = 'B';
            } else {
                if (v == parent.left) parent.left = u;
                else parent.right = u;

                u.parent = parent;
                if (dblack) fixDoubleBlack(u);
                else u.colour = 'B';
            }
            return;
        }

        swapValues(v, u);
        delete(node, u);
    }
    
    public T getHead() {
    	return this.root.data ;
    }
    
    private Node<T> successor(Node<T> node){
    	while(node.left != null) {
    		node = node.left ;
    	}
    	return node ;
    }
    private void swapValues(Node<T> v, Node<T> u) {
        T temp = v.data;
        v.data = u.data;
        u.data = temp;
    }

    private Node<T> getU(Node<T> node) {
        if (node.left != null && node.right != null) return successor(node.right);
        return node.left != null ? node.left : node.right;
    }

    public Node<T> getSibiling(Node<T> node){
		if(node.parent == null) {
			return null ;
		}
		else if(isLeft(node)) {
			return node.parent.right ;
		}
		else {
			return node.parent.left ;
		}
	}
	
    private void fixDoubleBlack(Node<T> node) {
        if (node == root) return;

        Node<T> sibling = getSibiling(node);
        Node<T> parent = node.parent;

        if (sibling == null) {
            fixDoubleBlack(parent);
        } else {
            if (sibling.colour == 'R') {
                parent.colour = 'R';
                sibling.colour = 'B';
                if (isLeft(sibling)) rightRotate(parent);
                else leftRotate(parent);
                fixDoubleBlack(node);
            } else {
                if (hasRedChild(sibling)) {
                    if (sibling.left != null && sibling.left.colour == 'R') {
                        if (isLeft(sibling)) rightRotate(parent);
                        else leftRotate(parent);
                    } else {
                        if (isLeft(sibling)) leftRotate(sibling);
                        rightRotate(parent);
                    }
                } else {
                    sibling.colour = 'R';
                    if (parent.colour == 'B') fixDoubleBlack(parent);
                    else parent.colour = 'B';
                }
            }
        }
    }
    public boolean isLeft(Node<T> node) {
		if(node == null) {
			return false ;
		}
		return node.parent.left == node ;
	}
	
    private boolean hasRedChild(Node<T> node) {
		return (node.left != null && node.left.colour == 'R') ||
				(node.right != null && node.right.colour == 'R');
	    }
	
    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node<T> root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }
}

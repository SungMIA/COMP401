package SPLT_A4;

public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;
	BST_Node par; // parent...not necessarily required, but can be useful in
	boolean justMade; // could be helpful if you change some of the return types

	BST_Node(String data) {
		this.data = data;
		this.justMade = true;
	}

	BST_Node(String data, BST_Node left, BST_Node right, BST_Node par) { 
		this.data = data;
		this.left = left;
		this.right = right;
		this.par = par;
		this.justMade = true;
	}

	// --- used for testing ----------------------------------------------
	//
	// leave these 3 methods in, as is (meaning also make sure they do in fact
	// return data,left,right respectively)

	public String getData() {
		return data;
	}

	public BST_Node getLeft() {
		return left;
	}

	public BST_Node getRight() {
		return right;
	}

	// --- end used for testing -------------------------------------------

	public BST_Node splay(BST_Node toSplay) {
		if (toSplay.par == null) {
			return toSplay;
		}
		while (toSplay.par != null) {
			BST_Node parent = toSplay.par;
			BST_Node grandparent = parent.par;
			if (grandparent == null) {//Single rotation
				if (parent.right == toSplay) {//L
					rotateLeft(toSplay);
				} else {//R
					rotateRight(toSplay);
				}
			} else {
				if (parent.left == toSplay && grandparent.left == parent) {//ZIGZIG
					rotateRight(toSplay);
					rotateRight(toSplay);
				}else if (parent.right == toSplay && grandparent.right == parent) {//ZAGZAG
					rotateLeft(toSplay);
					rotateLeft(toSplay);
				}else if (parent.right == toSplay && grandparent.left == parent) {//ZIGZAG
					rotateLeft(toSplay);
					rotateRight(toSplay);
				}else if (parent.left == toSplay && grandparent.right == parent) {//ZAGZIG
					rotateRight(toSplay);
					rotateLeft(toSplay);
				}
			}
		}
		return toSplay;
	}

	private BST_Node rotateLeft(BST_Node toRotate) {
		BST_Node parent = toRotate.par;
		BST_Node grandparent = parent.par;
		if (grandparent != null) {
			if (grandparent.left == parent) {
				grandparent.left = toRotate;
			} else if(grandparent.right == parent){
				grandparent.right = toRotate;
			}
		}
		parent.right = toRotate.left;
		if (toRotate.left != null) {
			parent.right.par = parent;
		}
		toRotate.left = parent;
		parent.par = toRotate;
		toRotate.par = grandparent;
		return toRotate;

	}

	private BST_Node rotateRight(BST_Node toRotate) {
		BST_Node parent = toRotate.par;
		BST_Node grandparent = parent.par;
		if (grandparent != null) {
			if (grandparent.right == parent) {
				grandparent.right = toRotate;
			} else if(grandparent.left == parent){
				grandparent.left = toRotate;
			}
		}
		parent.left = toRotate.right;
		if (toRotate.right != null) {
			parent.left.par = parent;
		}
		toRotate.right = parent;
		parent.par = toRotate;
		toRotate.par = grandparent;
		return toRotate;

	}

	public BST_Node containsNode(String s) {
		if (data.equals(s)) {
			return splay(this);
		}
		else if (data.compareTo(s) > 0) {// s lexiconically less than data
			if (left != null) {
				return left.containsNode(s);
			}
		} else if (data.compareTo(s) < 0) {
			if (right != null) {
				return right.containsNode(s);
			}
		}
		return splay(this);
	} 

	public BST_Node insertNode(String s) {
		justMade = !justMade;
		if (data.compareTo(s) > 0) {
			if (left == null) {
				left = new BST_Node(s, null, null, this); //creates a node with 'this' as the parent and 's' as data
				return splay(left);
			}
			return left.insertNode(s); //recursion
		}
		if (data.compareTo(s) < 0) {
			if (right == null) {
				right = new BST_Node(s, null, null, this);
				return splay(right);
			}
			return right.insertNode(s);
		}
		return splay(this);
	} 

//	public BST_Node removeNode(String s) {
//		BST_Node curr = this;
//		int compare = s.compareTo(curr.data);
//		if(compare < 0){
//			curr.left = curr.getLeft().removeNode(s);
//		}else if(compare > 0){
//			curr.right = curr.getRight().removeNode(s);
//		}else{
//			if(curr.left == null){
//				return curr.right;
//			}
//			else if(curr.right == null){
//				return curr.left;
//			}
//			BST_Node temp = curr;
//			curr = temp.right.findMin();
//			curr.right =temp.right.removeNode(curr.data);
//			curr.left = temp.left;
//		}
//		return curr;
//	} // I personal do not use the removeNode internal method in my impl since
		// it is rather easily done in SPLT, feel free to try to delegate this
		// out, however we do not "remove" like we do in BST

	public BST_Node findMin() {
		if (left != null)
			return left.findMin();
		else
			return splay(this);
	}

	public BST_Node findMax() {
		if (right != null)
			return right.findMax();
		else
			return splay(this);
	}

	public int getHeight() {
		int l = 0;
		int r = 0;
		if (left != null)
			l += left.getHeight() + 1;
		if (right != null)
			r += right.getHeight() + 1;
		return Integer.max(l, r);
	}

	// --------------------------------------------------------------------
	// you may add any other methods you want to get the job done
	// --------------------------------------------------------------------

}

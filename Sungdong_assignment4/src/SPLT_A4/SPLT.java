package SPLT_A4;

public class SPLT implements SPLT_Interface {
	public BST_Node root;
	public int size;

	public SPLT() {
		this.size = 0;
	}

	public BST_Node getRoot() { // please keep this in here! I need your root
								// node to test your tree!
		return root;
	}

	@Override
	public boolean insert(String s) {
		if (empty()) {
			root = new BST_Node(s);
			size = 1;
			return true;
		} else {
			root = root.insertNode(s);
			if (root.justMade) {
				size++;
			}
			return true;
		}
	}

	@Override
	public boolean remove(String s) {
		if (contains(s)) {
			if (root.right == null && root.left == null) {//case where root is a leaf
				root = null;
				size = 0;
				return true;
			}
			if (root.right == null) { //root only has left child
				root = root.left;
				root.par = null;
			} else if (root.left == null) {//root only has right child
				root = root.right;
				root.par = null;
			} else { //root has both left and right childs
				BST_Node rightLink = root.right;
				BST_Node leftLink = root.left;

				root = leftLink.findMax(); //assigns root as the maximum of the left subtree
				root.right = rightLink;	//updating pointers
				rightLink.par = root;
				root.par = null;
			}
			size--;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String findMin() {
		if (empty()) {
			return null;
		} else {
			root = root.findMin();
			return root.data;
		}
	}

	@Override
	public String findMax() {
		if (empty()) {
			return null;
		} else {
			root = root.findMax();
			return root.data;
		}
	}

	@Override
	public boolean empty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(String s) {
		if (empty()) {
			return false;
		}
		
		BST_Node temp = root.containsNode(s); //calls containsNode() from BST_Node which implements splay within it
		if (temp != null) {
			root = temp;
		} 
		if (temp != null && s == temp.data) { 
			return true; 
		} else {
			return false;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int height() {
		if (empty()) {
			return -1;
		} else {
			return root.getHeight();
		}
	}

}

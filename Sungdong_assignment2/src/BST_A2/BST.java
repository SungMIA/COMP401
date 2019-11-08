package BST_A2;

public class BST implements BST_Interface {
  public BST_Node root;
  public int size;
  
  public BST(){ size=0; root=null; }
  
  @Override
  //used for testing, please leave as is
  public BST_Node getRoot(){ return root; }

@Override
public boolean insert(String s) {
	 if(this.root == null) {
		 root = new BST_Node(s);
		 size++;
		 return true;
	 } else {
		if(root.insertNode(s) == true) {
			size++;
			return true;
		} else {
			return false;
		}
	 }
}

@Override
public boolean remove(String s) {
	if(root == null || !contains(s)) {
		return false;
	} else if (root.data == s) {
		BST_Node empty = new BST_Node(null);
		empty.left = root;
		boolean result = root.removeNode(s, empty);
		root = empty.left;
		size--;
		return result;
	} else {
		size--;
		return root.removeNode(s, null);
	}
}

@Override
public String findMin() {
	if(this.size == 0) {
		return null;
	} else {
		return root.findMin().data;
	}
}

@Override
public String findMax() {
	if(this.size == 0) {
		return null;
	} else {
		return root.findMax().data;
	}
}

@Override
public boolean empty() {
	if(size == 0 ) {
		return true;
	} else {
		return false;
	}
}

@Override
public boolean contains(String s) {
	return root.containsNode(s);
}

@Override 
public int size() {
	return size;
}

@Override
public int height() {
	if(root.data == null) {
		return 0;
	} else {
		return root.getHeight();
	}
}

}
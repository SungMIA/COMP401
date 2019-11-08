package BST_A2;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  int cDepth = 0;
  int size = 0;
  
  BST_Node(String data){ this.data=data; }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------

  
  // --- fill in these methods ------------------------------------------
  //
  // at the moment, they are stubs returning false 
  // or some appropriate "fake" value 
  //
  // you make them work properly
  // add the meat of correct implementation logic to them

  // you MAY change the signatures if you wish...
  // make the take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations

  
  public boolean containsNode(String s){     
	  if(data.compareTo(s) == 0) {
		  return true;
	  }
	  if((data.compareTo(s)) > 0) {
		  if(left == null) { /*might return null because get Left dont work */
			  return false;
		  } else {
			  return left.containsNode(s);
		  }
	  } else {
		  if(right == null) {
			  return false;
		  } else {
			  return right.containsNode(s);
		  }
	  }
  }
  public boolean insertNode(String s){
	  if(this.data == s) {
		  return false;
	  } else if(this.data.compareTo(s) == 0) {
		  return false;
	  } else if (this.data.compareTo(s) > 0) {
		 if(this.left == null) {
			 this.left = new BST_Node(s);
			 size++;
			 return true;
		 } else {
			 return this.left.insertNode(s);
		 }
	  } else {
		  if(this.right == null) {
			  this.right = new BST_Node(s);
			  size++;
			  return true;
		  } else {
			  return this.right.insertNode(s);
		  }
	  }
  }
  public boolean removeNode(String s, BST_Node parent){
	  int comparison = s.compareTo(data);
	  
	  if(comparison < 0) {
		  if(left == null) {
			  return false;
		  } else {
			  return left.removeNode(s, this);
		  }
	  } else if(comparison > 0) {
		  if(right == null) {
			  return false;
		  } else {
			  return right.removeNode(s, this);
		  }
	  } else {
		  if(left != null && right != null) { // two-child case
			  data = right.findMin().data;
			  right.removeNode(data, this);
			  return true;
		  } else if (left != null && right == null) { // one left-child case
			  if(parent.left != null && parent.left == this) {
				  parent.left = left;
				  return true;
			  } else {
				  parent.right = left;
				  return true;
			  }
		  } else if (right != null && left == null) { // one right-child case
			  if(parent.left != null && parent.left == this) {
				  parent.left = right;
				  return true;
			  } else {
				  parent.right = right;
				  return true;
			  }
		  }
	  } if (left == null && right == null) {
		  if(parent.left != null && parent.left == this) {
		  parent.left = null;
		  return true;
	  } else {
		  parent.right = null;
		  return true;
	  }
  }
	  return false;
  }
  
  public BST_Node findMin(){
	  if(this.left == null) {
		  return this;
	  } else {
		  return this.left.findMin();
	  }
  }
  public BST_Node findMax(){
	  if(this.right == null) {
		  return this;
	  } else {
		  return this.right.findMax();
	  }
  }
  public int getHeight(){
	  int leftHeight = 0;
	  int rightHeight = 0;
	  if(this.left != null) {
		  leftHeight = this.left.getHeight()+1;
	  }
	  if(this.right != null) {
		  rightHeight = this.right.getHeight()+1;
	  }
	  return Math.max(leftHeight, rightHeight);
  }
	  
  
  public int getSize() {
	  return size;
  }
  
  


  // --- end fill in these methods --------------------------------------


  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
  public String toString(){
    return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
            +",Right: "+((this.right!=null)?right.data:"null");
  }
}
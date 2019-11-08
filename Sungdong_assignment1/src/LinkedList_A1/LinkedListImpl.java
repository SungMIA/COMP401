package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
	Node sentinel;
	int numElts;
	
	public LinkedListImpl() {
		sentinel = new Node(0);
		numElts = 0;
	}
	public Node getRoot() {
		return sentinel;
	}
	@Override
	
	
	public boolean insert(double elt, int index) {
		int size = size();
		if(index > size || index < 0) {
			return false;
		}
		Node newNode = new Node(elt);
		if(index == 0 && size == 0) {
			Node curr = sentinel;
			curr.next = newNode;
			curr.prev = newNode;
			newNode.prev = sentinel;
			newNode.next = sentinel;
		} else {
			Node curr = sentinel;
			for(int x=0; x <= size(); x++) {
				curr = curr.next;
				if(x == index) {
					curr.prev.next = newNode;
					newNode.prev = curr.prev;
					curr.prev = newNode;
					newNode.next = curr;
					
				}
			}
			
		}
		numElts++;
		return true;
	}

	@Override
	public boolean remove(int index) {
		int size = size();
		if(index > size - 1 || index < 0) {
			return false;
		} else {
			Node curr = sentinel;
			for(int x=0; x <= size(); x++) {
				curr = curr.next;
				if(x == index) {
				curr.next.prev = curr.prev;
				curr.prev.next = curr.next;
				}
			}
			numElts--;
			return true;
		}
	}

	@Override
	public double get(int index) {
		Node prev = sentinel.getNext();
		int size = size();
		int count = 0;
		if(index > size -1 || index < 0) {
			return Double.NaN;
		} else {
			while(count < index) {
				prev = prev.next;
				count++;
			}
			return prev.data;
		}
	}

	@Override
	public int size() {
		return numElts;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public void clear() {
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		numElts = 0;
	}

}

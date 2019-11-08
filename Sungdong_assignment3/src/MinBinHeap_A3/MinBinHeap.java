package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
  private EntryPair[] array; //load this array
  private int size;
  private static final int arraySize = 10000; //Everything in the array will initially 
                                              //be null. This is ok! Just build out 
                                              //from array[1]

  public MinBinHeap() {
    this.array = new EntryPair[arraySize];
    array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
                                             //of child/parent computations...
                                             //the book/animation page both do this.
  }
    
  //Please do not remove or modify this method! Used to test your entire Heap.
  @Override
  public EntryPair[] getHeap() { 
    return this.array;
  }

@Override
public void insert(EntryPair entry) {
	if(array[1] == null) { //Base case for an initially empty tree
		array[1] = entry;
		size++;
	} else {
		int hole = size+1; //Establishes the index where tree is first empty
		array[hole] = entry;		
		while((hole/2 != 0) && (array[hole/2].getPriority() > array[hole].getPriority())) { //Check if heap-order is proper
			swap(hole/2, hole);
			hole = hole/2;
		}
		size++;
	}
}

private void swap(int first, int second) {
	EntryPair temp = array[first];
	array[first] = array[second];
	array[second] = temp;
}
@Override
public void delMin() {
	if(size != 0) {
		array[1] = array[size--];
		bubbleDown(1);
	}
}

private void bubbleDown(int hole) {
	int child;
	EntryPair temp = array[hole];
	boolean next = true;
	while(next == true && (hole*2) <= size) {
		child = hole*2;
		if(child != size && (array[child+1].getPriority() < array[child].getPriority())) {
			child++;
		}
		if(array[child].getPriority() < temp.getPriority()) {
			array[hole] = array[child];
		} else {
			next = false;
		}
		if(next) {
			hole = child;
		}
	}
	array[hole] = temp;
}

@Override
public EntryPair getMin() {
	return array[1];
}

@Override
public int size() {
	return size;
}

@Override
public void build(EntryPair[] entries) {
	size = entries.length;
	for(int x=1; x < size + 1; x++) {
		array[x] = entries[x-1];
	}
	for(int x = size/2; x > 0; x--) {
		bubbleDown(x);
	}
}
}
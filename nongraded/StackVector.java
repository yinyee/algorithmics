package nongraded;

import java.util.Vector;

/*
 * Implement the Stack ADT using Vector as the underlying data structure.
 * What are the relative merits of this implementation versus the array-
 * based implementation?
 * 
 * - vectors can grow in size
 * - lots of inherited functions
 */

public class StackVector {
	
	private Vector<Object> vector;
	private int count;
	
	// constructor
	public StackVector() {
		vector = new Vector<Object>();
		count = 0;
	}
	
	// constructor with user-defined size
	public StackVector(int size) {
		vector = new Vector<Object>(size);
		count = 0;
	}
		
	// push() method adds a new element to the top of the stack
	public void push(Object object) {
		vector.add(count++, object);
	}
	
	// peek() method returns the object at the top of the stack but does not remove it
	public Object peek() {
		Object top = vector.lastElement();
		return top;
	}
	
	// pop() method removes and returns the object at the top of the stack
	public Object pop() {
		Object top = vector.get(--count);
		return top;
	}
	
	// isEmpty() method checks if stack is empty
	public boolean isEmpty() {
		return vector.isEmpty();
	}
	
	// clear() method clears the stack
	public void clear() {
		count = 0;
	}
	
	// size() method returns the number of elements in the stack
	public int size() {
		return vector.size();
	}
	
}

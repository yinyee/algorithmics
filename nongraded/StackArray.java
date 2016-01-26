package nongraded;

/*
 * A stack is represented by a linear sequence of objects.
 * Based on the "last in, first out" rule
 */

public class StackArray {
	
	private Object[] array;
	private int count;
	
	// Default constructor
	public StackArray() {
		array = new Object[50];
		count = 0;
	}
	
	// Constructor with user-specified size
	public StackArray(int size) {
		array = new Object[size];
		count = 0;
	}
	
	// push() method adds a new element to the top of the stack
	public void push(Object object) {
		array[count++] = object;
	}
	
	// peek() method returns the object at the top of the stack but does not remove it
	public Object peek() {
		Object top = array[count-1];
		return top;
	}
	
	// pop() method removes and returns the object at the top of the stack
	public Object pop() {
		Object top = array[--count];
		return top;
	}
	
	// isEmpty() method checks if stack is empty
	public boolean isEmpty() {
		return (count == 0);
	}
	
	// clear() method clears the stack
	public void clear() {
		count = 0;
	}
	
	// size() method returns the number of elements in the array
	public int size() {
		return count;
	}
	
}

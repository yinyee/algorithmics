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
	public void push(Object object) throws StackArrayException {
		
		if (count >= 50) {
			throw new StackArrayException("No more free space in stack");
		} else if (count < 0) {
			throw new StackArrayException("Negative index: something's wrong");
		} else {
			array[count] = object;
		}
		
	}
	
	// peek() method returns the object at the top of the stack but does not remove it
	public Object peek() throws StackArrayException {
		if (count != 0) {
			Object top = array[count-1];
			return top;
		} else {
			throw new StackArrayException("Stack is empty");
		}
	}
	
	// pop() method removes and returns the object at the top of the stack
	public Object pop() throws StackArrayException {
		if (count != 0) {
			Object top = array[count--];
			return top;
		} else {
			throw new StackArrayException("Stack is empty");
		}
	}
	
	// isEmpty() method checks if stack is empty
	public boolean isEmpty() {
		return (count == 0);
	}
	
	// clear() method clears the stack
	public void clear() {
		count = 0;
	}
	
	// Define new StackArrayException which extends the ArrayOutOfBoundsException
	public class StackArrayException extends Exception {
		
		// Default constructor
		public StackArrayException() {
			super();
		}
		
		// Constructor with message
		public StackArrayException(String s) {
			super(s);
		}
		
	}
	
}

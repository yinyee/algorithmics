package nongraded;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StackArrayTest {
	
	StackArray stack;
	Object object1, object2, object3;
	
	@Before
	public void setUp() throws Exception {
		stack = new StackArray();
		object1 = new Object();
		object2 = new Object();
		object3 = new Object();
	}

	@Test
	public void testConstructor() {
		assertTrue(stack.isEmpty());
	}
	
	@Test
	public void testPushAndPeek() {	
		stack.push(object1); 
		assertSame(stack.peek(), object1);
		assertEquals(stack.size(), 1);
		stack.push(object2);
		assertSame(stack.peek(), object2);
		assertEquals(stack.size(), 2);
	}

	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testPushException() {
		StackArray smallStack = new StackArray(2);
		smallStack.push(object1);
		smallStack.push(object2);
		smallStack.push(object3); // should throw an ArrayIndexOutOfBoundsException
		assertEquals(smallStack.size(), 2);
	}
	
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testPeekException() {
		stack.peek(); // should throw an ArrayIndexOutOfBoundsException
	}
	
	@Test
	public void testPop() {
		stack.push(object1);
		stack.push(object2);
		assertEquals(stack.size(), 2);
		assertSame(stack.pop(), object2);
		assertEquals(stack.size(), 1);
		assertSame(stack.pop(), object1);
		assertEquals(stack.size(), 0);
	}
	
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testPopException() {
		stack.pop();
	}
		
}

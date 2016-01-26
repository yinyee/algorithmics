package nongraded;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class StackVectorTest {

	StackVector stack;
	Object object1, object2, object3;
	
	@Before
	public void setUp() throws Exception {
		stack = new StackVector();
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
		assertSame(object1, stack.peek());
		assertEquals(1, stack.size());
		stack.push(object2);
		assertSame(object2, stack.peek());
		assertEquals(2, stack.size());
	}

	@Test
	public void testPushException() {
		StackVector smallStack = new StackVector(2);
		assertEquals(0, smallStack.size());
		smallStack.push(object1);
		assertEquals(1, smallStack.size());
		smallStack.push(object2);
		assertEquals(2, smallStack.size());
		smallStack.push(object3);
		assertEquals(3, smallStack.size());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testPeekException() {
		stack.peek(); // should throw a NoSuchElementException
	}
	
	@Test
	public void testPop() {
		stack.push(object1);
		stack.push(object2);
		assertEquals(2, stack.size());
		assertSame(object2, stack.pop());
		assertEquals(1, stack.size());
		assertSame(object1, stack.pop());
		assertEquals(0, stack.size());
	}
	
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testPopException() {
		stack.pop();
	}

}

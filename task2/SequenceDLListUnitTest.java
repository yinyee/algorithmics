package task2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SequenceDLListUnitTest {

	SequenceDLList sequence;
	Integer num1, num2, num3;
	Integer result;
	
	@Before
	public void setUp() throws Exception {
		sequence = new SequenceDLList();
		num1 = new Integer(1);
		num2 = new Integer(2);
		num3 = new Integer(3);
	}

	@Test
	public void testEmpty() {
		assertTrue(sequence.empty());
	}
	
	@Test
	public void testInsertFirst() throws SequenceDLListException {
		
		sequence.insertFirst(num3);
		result = (Integer) sequence.first();
		assertEquals(num3, result);
		result = (Integer) sequence.last();
		assertEquals(num3, result);
		assertEquals(1, sequence.size());
		
		sequence.insertFirst(num1);
		result = (Integer) sequence.first();
		assertEquals(num1, result);
		result = (Integer) sequence.last();
		assertEquals(num3, result);
		assertEquals(2, sequence.size());
		
	}
	
	@Test
	public void testInsertLast() throws SequenceDLListException {
		
		sequence.insertLast(num3);				// 3
		result = (Integer) sequence.first();
		assertEquals(num3, result);
		result = (Integer) sequence.last();
		assertEquals(num3, result);
		assertEquals(1, sequence.size());
		
		sequence.insertLast(num1);				// 3 1
		result = (Integer) sequence.first();
		assertEquals(num3, result);
		result = (Integer) sequence.last();
		assertEquals(num1, result);
		assertEquals(2, sequence.size());
		
	}
	
	@Test
	public void testInsert() throws SequenceDLListException {
		
		sequence.insert(num1, 0);	// 1
		sequence.insert(num2, 1);	// 1 2
		sequence.insert(num3, 2);	// 1 2 3
		
		result = (Integer) sequence.first();
		assertEquals(num1, result);
		result = (Integer) sequence.element(1);
		assertEquals(num2, result);
		result = (Integer) sequence.last();
		assertEquals(num3, result);
		assertEquals(3, sequence.size());
		
	}
	
	@Test
	public void testDeleteFirst() throws SequenceDLListException {
		
		sequence.insertFirst(num3);		// 3
		sequence.insertFirst(num1);		// 1 3
		
		result = (Integer) sequence.first();
		assertEquals(num1, result);
		result = (Integer) sequence.last();
		assertEquals(num3, result);
		assertEquals(2, sequence.size());
		
		sequence.deleteFirst();			// 3
		
		result = (Integer) sequence.first();
		assertEquals(num3, result);
		result = (Integer) sequence.last();
		assertEquals(num3, result);
		assertEquals(1, sequence.size());
		
	}
	
	@Test
	public void testDeleteLast() throws SequenceDLListException {
		
		sequence.insertFirst(num3);		// 3
		sequence.insertFirst(num1);		// 1 3
		
		result = (Integer) sequence.first();
		assertEquals(num1, result);
		result = (Integer) sequence.last();
		assertEquals(num3, result);
		assertEquals(2, sequence.size());
		
		sequence.deleteLast();			// 1
		
		result = (Integer) sequence.first();
		assertEquals(num1, result);
		result = (Integer) sequence.last();
		assertEquals(num1, result);
		assertEquals(1, sequence.size());
				
	}
	
	@Test
	public void testDelete() throws SequenceDLListException {
		
		sequence.insertLast(num1);	// 1
		sequence.insertFirst(num3);	// 3 1
		sequence.insert(num2, 1);	// 3 2 1
		
		result = (Integer) sequence.first();
		assertEquals(num3, result);
		result = (Integer) sequence.last();
		assertEquals(num1, result);
		result = (Integer) sequence.element(1);
		assertEquals(num2, result);
		assertEquals(3, sequence.size());
		
		sequence.delete(2);			// 3 2
		result = (Integer) sequence.last();
		assertEquals(num2, result);
		assertEquals(2, sequence.size());
		
		sequence.delete(0);			// 2
		result = (Integer) sequence.first();
		assertEquals(num2, result);
		result = (Integer) sequence.element(0);
		assertEquals(num2, result);
		assertEquals(1, sequence.size());
		
		sequence.delete(0);
		assertEquals(0, sequence.size());
		
	}

}

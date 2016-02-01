package task3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import task3.LinearLUTM2F.Entry;

public class LinearLUTM2FUnitTest {

	LinearLUTM2F myLUT;
	Entry entry;
	Object expected;
	
	@Before
	public void setUp() throws Exception {
		
		myLUT = new LinearLUTM2F();
		
		myLUT.insert("Priscilla", new Integer(41));
	    myLUT.insert("Travis", new Integer(34));
	    myLUT.insert("Samuel", new Integer(28));
	    myLUT.insert("Helena", new Integer(39));
	    myLUT.insert("Andrew", new Integer(14));
	    myLUT.insert("Kay", new Integer(24));
	    myLUT.insert("John", new Integer(67));
	    
	}

	@Test
	public void testInsert() throws SequenceListException {
		entry = (Entry) myLUT.sequence.first();
		expected = entry.value;
		assertEquals(67, expected);
		System.out.println(myLUT.toString());
		System.out.println("^ John should be the first entry");
	}
	
	@Test
	public void testFindIndex() throws KeyNotFoundInTableException {
		assertEquals(1, myLUT.findIndex("Kay"));
	}
	
	@Test
	public void testFindEntry() throws KeyNotFoundInTableException {
		entry = (Entry) myLUT.findEntry("Andrew");
		assertEquals(new Integer(14), entry.value);
	}
	
	@Test (expected = KeyNotFoundInTableException.class)
	public void testRemove() throws KeyNotFoundInTableException {
		myLUT.remove("Andrew");
		myLUT.findEntry("Andrew");
	}

	@Test
	public void testUpdate() throws KeyNotFoundInTableException {
		myLUT.update("Helena", new Integer(40));
		entry = (Entry) myLUT.findEntry("Helena");
		assertEquals(new Integer(40), entry.value);
	}
	
	@Test
	public void testUpdateM2F() throws KeyNotFoundInTableException, SequenceListException {
		myLUT.update("Helena", new Integer(40));
		entry = (Entry) myLUT.sequence.element(0);
		assertEquals(new Integer(40), entry.value);
		System.out.println(myLUT.toString());
		System.out.println("^ Helena should be the first entry");
	}
	
	@Test
	public void testRetrieve() throws KeyNotFoundInTableException {
		assertEquals(new Integer(28), myLUT.retrieve("Samuel"));
	}
	
	@Test
	public void testRetrieveM2F() throws KeyNotFoundInTableException, SequenceListException {
		myLUT.retrieve("Samuel");
		entry = (Entry) myLUT.sequence.element(0);
		assertEquals(new Integer(28), entry.value);
		System.out.println(myLUT.toString());
		System.out.println("^ Samuel should be the first entry");
	}
}

package task3;

/**
 * Requirement 1: Implement a LUT based on SequenceList
 * Requirement 2: Use a move-to-front strategy i.e.
 * 					>> insert() adds new element at the front
 * 					>> successful update() calls moveToFront()
 * 					>> successful retrieve() calls moveToFront() 
 * References the public interface provided in slide #22, Look-Up
 * Tables by Prof. Jens Krinke.
 */

public class LinearLUTM2F {
	
	protected SequenceList sequence;

	public LinearLUTM2F() {
		sequence = new SequenceList();
	}
	
	/**
	 * Inserts a new entry at the front of the LUT.
	 * Does not check for duplicate keys before insertion.
	 * @param key
	 * @param value
	 */
	public void insert(String key, Object value) {
		Entry newEntry = new Entry(new Key(key), value); 
		sequence.insertFirst(newEntry);
	}
	
	/**
	 * Removes the entry at the specified key.
	 * @param key
	 * @throws KeyNotFoundInTableException
	 */
	public void remove(String key) throws KeyNotFoundInTableException {
		
		try {
			sequence.delete(findIndex(key));
		} catch (Exception e) {
			throw new KeyNotFoundInTableException(e.getMessage());
		}
		
	}

	/**
	 * Updates the value of the entry with the specified key. Entries
	 * are moved to the front of the LUT upon successful update.
	 * @param key
	 * @param newValue
	 * @throws KeyNotFoundInTableException
	 */
	public void update(String key, Object newValue) throws KeyNotFoundInTableException {
		
		try {
			Entry entry = findEntry(key);
			entry.value = newValue;
			moveToFront(entry);
		} catch (Exception e) {
			throw new KeyNotFoundInTableException(e.getMessage());
		}
		
	}
	
	/**
	 * Retrieves the value of the entry with the specified key.
	 * Entries are moved to the front of the LUT upon successful
	 * retrieval.
	 * @param key
	 * @return the value of the entry with the specified key
	 * @throws KeyNotFoundInTableException
	 */
	public Object retrieve(String key) throws KeyNotFoundInTableException {
		
		try {
			moveToFront(findEntry(key));
			Entry entry = (Entry) sequence.first();
			return entry.value;
		} catch (Exception e) {
			throw new KeyNotFoundInTableException(e.getMessage());
		}
		
	}

	public String toString() {
		
		StringBuilder str = new StringBuilder("");
		try {
			for (int i = 0; i < sequence.size(); i++) {
				Entry entry = (Entry) sequence.element(i);
				str.append(entry.key.keyString + ":" + entry.value.toString() + ", ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str.toString();
	}
	
	/**
	 * Returns the index corresponding to the specified key.
	 * @param key
	 * @return the index corresponding to the specified key
	 * @throws KeyNotFoundInTableException
	 */
	protected int findIndex(String key) throws KeyNotFoundInTableException {
		
		try {
			for (int i = 0; i < sequence.size(); i++) {
				Entry entry = (Entry) sequence.element(i);
				if (key.equals(entry.key.keyString)) {
					return i;
				}
			}			
		} catch (Exception e) {
			throw new KeyNotFoundInTableException(e.getMessage());
		}
		return -1;
		
	}
	
	/**
	 * Returns the entry with the specified key.
	 * @param key
	 * @return the entry with the specified key
	 * @throws KeyNotFoundInTableException
	 */
	protected Entry findEntry(String key) throws KeyNotFoundInTableException {
		
		try {
			return (Entry) sequence.element(findIndex(key));
		} catch (Exception e) {
			throw new KeyNotFoundInTableException(e.getMessage());
		}
		
	}
	
	protected void moveToFront(Entry entry) throws KeyNotFoundInTableException {
		
		try {
			sequence.delete(findIndex(entry.key.keyString));
			sequence.insertFirst(entry);
		} catch (Exception e) {
			throw new KeyNotFoundInTableException(e.getMessage());
		}
		
	}
	
	/**
	 * Entry is a helper class.  Each Entry has a Key and a corresponding Value.
	 */
	protected class Entry {
		
		protected Key key;
		protected Object value;
		
		protected Entry (Key key, Object value) {
			this.key = key;
			this.value = value;
		}
	}
	
	/**
	 * Key is a helper class.
	 * References the public interface provided in slides #20-21,
	 * Look-Up Tables by Prof. Jens Krinke.
	 */
	protected class Key {
		
		private String keyString;
		
		public Key() {
			this.keyString = null;
		}
		
		public Key (String keyString) {
			this.keyString = keyString;
		}
		
		public String toString() {
			return keyString;
		}
		
		public boolean equals(Key key) {
			return (keyString.equals(key.toString()));
		}
		
		public boolean lessThan(Key key) {
			return (keyString.compareTo(key.toString()) < 0);
		}
		
		public boolean greaterThan(Key key) {
			return (keyString.compareTo(key.toString()) > 0);
		}
				
	}

}

/**
 * KeyNotFoundInTableException is thrown when the requested key is not
 * found in the look-up table.
 */
@SuppressWarnings("serial")
class KeyNotFoundInTableException extends Exception {
	
	KeyNotFoundInTableException() {
		super();
	}
	
	KeyNotFoundInTableException(String message) {
		super(message);
	}
	
}

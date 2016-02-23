package task5;

import java.util.Vector;

@SuppressWarnings("serial")
class KeyNotFoundInTableException extends Exception {
  /* Rudimentary exception - should be extended */
}

@SuppressWarnings("serial")
class TableOverflowException extends Exception {
  /* Rudimentary exception - should be extended */
}

public class HashTableLProbe {

  /**
   * Entries in the hash table array.
   */
  protected class Entry {
    protected String key;
    protected Object value;

    public Entry(String k, Object v) {
      key = k;
      value = v;
    }
  }

  /**
   * The hash table is implemented by an array of entries.
   */
  protected Entry[] entries;

  /**
   * Compute a hash over a string and map it into a given range.
   */
  private int h1(String key, int M) {
    int n = 0;

    for (int i=0; i < key.length(); i++) {
      n += (int)key.charAt(i);
    }

    return n % M;
  }

  /**
   * Default constructor.
   */
  public HashTableLProbe() {
    entries = new Entry[50];
  }

  /**
   * Constructor for a given hash table size.
   */
  public HashTableLProbe(int size) {
    entries = new Entry[size];
  }

  /**
   * Map a key to a value (insert into the hash table).
   */
  public void insert(String key, Object value)
  throws TableOverflowException {
	  
    // Compute the hash value
    int index = h1(key, entries.length);
    
    // Probe linearly to find empty slot.
    int count = 0;
    
    while (entries[index] != null &&
           (!entries[index].key.equals("Tombstone"))
           && count != entries.length) {
      index = (index+1) % entries.length;
      count += 1;
    }
    
    if (count == entries.length) {
      throw new TableOverflowException();
    } else {
      entries[index] = new Entry(key, value);
    }
  }

  /**
   * Find the value which is mapped to a key.
   */
  public Object retrieve(String key)
  throws KeyNotFoundInTableException {
    // Compute the hash value
    int index = h1(key, entries.length);

    // Probe linearly looking for match.
    int count = 0;

    while (entries[index] != null &&
           (!entries[index].key.equals(key))
           && count != entries.length) {
      index = (index+1) % entries.length;
      count += 1;
    }

    if (entries[index] == null || count == entries.length) {
      throw new KeyNotFoundInTableException();
    }

    return entries[index].value;
  }

  /**
   * Delete a mapping for a key.
   */
  public void delete(String key)
  throws KeyNotFoundInTableException {
    // Compute the hash value
    int index = h1(key, entries.length);

    // Probe linearly looking for match.
    int count = 0;

    while (entries[index] != null &&
           (!entries[index].key.equals(key))
           && count != entries.length) {
      index = (index+1) % entries.length;
      count += 1;
    }

    if (entries[index] == null || count == entries.length) {
      throw new KeyNotFoundInTableException();
    }

    entries[index].key = "Tombstone";
  }

  /**
   * Delete a mapping for a key without creating a tombstone.
   */
  public void delete2(String key)
  throws KeyNotFoundInTableException {
    // Like delete, except that the entry is not replaced by a tombstone.
    // Instead, the entry will be deleted (set to null).
    // This requires a cleanup and rehashing all succeeding entries.
	// Copied from delete()
	
	// Get hash value of the desired key
	int index = h1(key, entries.length);
	
	// Initialise count to zero
	int count = 0;
	
	// Find the position of the desired entry
	while (entries[index] != null && (!entries[index].key.equals(key)) && count != entries.length) {
	   index = (index+1) % entries.length;
	   count += 1;
	}
	
	// index is now pointing at the desired entry
	if (entries[index] == null || count == entries.length) {
	   throw new KeyNotFoundInTableException();
	} else {
		entries[index] = null;
		int i = (index+1) % entries.length;
		String tempKey = "";
		Object tempValue = null;
		while (entries[i] != null) {
			tempKey = entries[i].key.toString(); // passing by reference -- need to pass by value
			tempValue = entries[i].value;
			try {
				insert(tempKey, tempValue);
				entries[i] = null;
				i = (i+1) % entries.length;
			} catch (TableOverflowException e) {
				e.printStackTrace();
			}
		}
	}
	
  }

  /**
   * Resize the current hash table to a different size.
   *
   * Resizing can be done to enlarge or to shrink the hash table. If
   * the resized hash table cannot hold all elements of the previous
   * one, an exception will be thrown.
   */
  public void resize(int m)
  throws TableOverflowException {
    // Replaces the entries array with a new one in which all
    // old entries are inserted fresh (`rehashing').
	// Step 1: Temporarily copy entries into a vector
	Vector<Entry> temp = new Vector<Entry>();
	for (int i = 0; i < entries.length; i++) {
		if (entries[i] != null && entries[i].key != "Tombstone") {
			temp.addElement(entries[i]);
		}
	}
	// Step 2: Make a new array for entries
	if (m < temp.size()) {
		throw new TableOverflowException();
	} else {
		// Step 3: Insert all entries into new array
		entries = new Entry[m];
		Entry current = null;
		for (int i = 0; i < temp.size(); i++) {
			current = temp.elementAt(i);
			insert(current.key, current.value);
		}
	}
  }

  /**
   * Return a textual representation of the hash table.
   */
  public String toString() {
    String str = "";
    for (int i = 0; i < entries.length; ++i) {
      Entry e = entries[i];
      str += i + ": ";
      if (e != null) {
        str += e.key + " " + e.value + "\n";
      } else {
        str += null + "\n";
      }
    }
    return str;
  }
}

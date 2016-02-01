package task2;

/**
 * Based on SequenceList and SequenceDLList classes by Danny Alexander.
 * References the public interface provided in slide #34, Linear Data 
 * Structures by Prof. Jens Krinke.
 */

public class SequenceDLList {
	
	Node listHead;
	Node listTail;
	private int size;
	
	public SequenceDLList () {
		listHead = null;
		listTail = null;
		size = 0;
	}
	
	/**
	 * Inserts a new item into the sequence at the specified position.
	 * Note the zero-based index.
	 * @param object
	 * @param position
	 * @throws SequenceDLListException 
	 */
	public void insert(Object object, int position) throws SequenceDLListException {
		
		// check for negative position
		if (position < 0) {
			throw new SequenceDLListException("Sequence position cannot be negative");
		}
		
		if (position == 0) {
			this.insertFirst(object);
		} else {
			int i = 1;
			Node pointer = listHead;
			while (i < position) {
				if (pointer.next == null) {
					throw new SequenceDLListException("Index out of bounds");
				} else {
					pointer = pointer.next;
					i++;
				}
			}
			// pointer is referencing the node just before the desired position
			Node newNode = new Node(object, pointer, pointer.next); // insert new node
			// update listTail if inserting at the end
			if (pointer.next == null) {
				listTail = newNode;
				pointer.next = newNode;
			} else {
				pointer.next.previous = newNode; // update next node
				pointer.next = newNode; // update previous node
			}
			size +=1;
		}
		
	}
	
	/**
	 * Inserts a new item into the first position of the sequence.
	 * @param object
	 */
	public void insertFirst(Object object) {
		if (this.empty()) {
			listHead = new Node(object, null, null);
			listTail = listHead;
		} else { 
			listHead.previous = new Node(object, null, listHead);
			listHead = listHead.previous;
		}
		size +=1;
	}
	
	/**
	 * Inserts a new item into the last position of the sequence.
	 * @param object
	 */
	public void insertLast(Object object) {
		if (this.empty()) {
			listTail = new Node(object, null, null);
			listHead = listTail;
		} else {
			listTail.next = new Node(object, listTail, null);
			listTail = listTail.next;
		}
		size +=1;
	}
	
	/**
	 * Deletes the item at the specified position.
	 * @param position
	 */
	public void delete(int position) throws SequenceDLListException {
		
		// check if sequence is empty
		if (this.empty()) {
			throw new SequenceDLListException("Sequence is empty");
		}
		
		// check if only one item in the sequence
		if (listHead == listTail) {
			listHead = listTail = null;
			size -=1;
		} else if (position == 0) {
			this.deleteFirst();
		} else {
			int i = 1;
			Node pointer = listHead;
			while (i < position) {
				if (pointer.next == null) {
					throw new SequenceDLListException("Index out of bounds");
				} else {
					pointer = pointer.next;
					i++;
				}
			}
			// pointer is referencing the node just before the desired position
			// special case: deleting last element
			if (pointer.next.next == null) {
				this.deleteLast();
			} else {
				pointer.next = pointer.next.next; // update current node
				pointer.next.previous = pointer; // update next node
				size -=1;
			}
		}
	}
	
	/**
	 * Deletes the item at the first position of the sequence.
	 */
	public void deleteFirst() throws SequenceDLListException {
		
		// check if sequence is empty
		if (this.empty()) {
			throw new SequenceDLListException("Sequence is empty");
		}
		
		// check if only one item in the sequence
		if (listHead == listTail) {
			listHead = listTail = null;
		} else {
			listHead = listHead.next;
			listHead.previous = null;
		}
		size -=1;
	}
	
	/**
	 * Deletes the item at the last position of the sequence.
	 */
	public void deleteLast() throws SequenceDLListException {
		
		// check if sequence is empty
		if (this.empty()) {
			throw new SequenceDLListException("Sequence is empty");
		}
		// check if only one item in the sequence
		if (listHead == listTail) {
			listHead = listTail = null;
		} else {
			listTail = listTail.previous;
			listTail.next = null;
		}
		size -=1;
	}
	
	/**
	 * Uses a zero-based index.
	 * @param position
	 * @return the item at the specified position
	 */
	public Object element(int position) throws SequenceDLListException {
		
		// check if sequence is empty
		if (this.empty()) {
			throw new SequenceDLListException("Sequence is empty");
		} else {
			int i = 0;
			Node pointer = listHead;
			while (i < position) {
				if (pointer.next == null) {
					throw new SequenceDLListException("Element not found");
				} else {
					pointer = pointer.next;
					i++;
				}
			}
			// pointer is referencing the node just before the desired position
			return pointer.datum;
		}
		
	}
	
	/**
	 * @return the first item in the sequence
	 */
	public Object first() throws SequenceDLListException {
		
		// check if sequence is empty
		if (this.empty()) {
			throw new SequenceDLListException("Sequence is empty");
		} else {
			return listHead.datum;
		}
		
	}
	
	/**
	 * @return the last item in the sequence
	 */
	public Object last() throws SequenceDLListException {
		
		// check if sequence is empty
		if (this.empty()) {
			throw new SequenceDLListException("Sequence is empty");
		} else {
			return listTail.datum;
		}
		
	}
	
	/**
	 * Checks if the sequence is empty.
	 * @return true is sequence is empty
	 */
	public boolean empty() {
		if (listHead == null && listTail == null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Clears the sequence of all its items.
	 */
	public void clear() {
		listHead = listTail = null;
	}
	
	/**
	 * @return the number of elements in the sequence
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Node is a helper class.
	 */
	protected class Node {
		
		protected Object datum;
		protected Node previous;
		protected Node next;
		
		protected Node(Object datum) {
			this(datum, null, null);
		}
		
		protected Node(Object datum, Node previous, Node next) {
			this.datum = datum;
			this.previous = previous;
			this.next = next;
		}
	}

}

/**
 * SequenceDLListException is an exception class for SequenceDLList.
 */
@SuppressWarnings("serial")
class SequenceDLListException extends Exception {
	
	SequenceDLListException() {
		super();
	}
	
	SequenceDLListException(String message) {
		super(message);
	}
	
}

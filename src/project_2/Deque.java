package project_2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node<Item> head;
	private Node<Item> tail;
	private int size;
	
	public Deque() {
		this.size = 0;
		this.head = null;
		this.tail = null;
	}
	
	public boolean isEmpty() {
		
		return size == 0;
		
	}
	
	public int size() {
		
		return size;
		
	}
	
	public void addFirst(Item item) {
		
		if (item == null) {
			throw new NullPointerException();
		}
		
		Node<Item> newNode = new Node<Item>(item);
		
		if (size == 0) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.setNext(head);
			head.setPrev(newNode);
			head = newNode;
		}
		size++;
	}
	
	public void addLast(Item item) {
		
		if (item == null) {
			throw new NullPointerException();
		}
		
		Node<Item> newNode = new Node<Item>(item);
		
		if (size == 0) {
			head = newNode;
			tail = newNode;
		} else {
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
		}
		size++;
		
	}
	
	public Item removeFirst() {
		
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Node<Item> toRemove = head;
		size--;
		if (size == 0) {
			head = null;
			tail = null;
		} else {
			head = head.getNext();
		}
		
		return toRemove.getItem();	
	}
	
	public Item removeLast() {
		
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Node<Item> toRemove = tail;
		size--;
		if (size == 0) {
			head = null;
			tail = null;
		} else {
			tail = tail.getPrev();
			tail.setNext(null);
		}
		
		return toRemove.getItem();	
		
	}
	

	@Override
	public Iterator<Item> iterator() {
		
		return new DequeueIterator<Item>(head);
		
	}
	
	
	public static void main(String[] args) {
		Deque<String> stringList = new Deque<String>();
		stringList.addFirst("Aaron");
		System.out.println("size is: " + stringList.size());
		stringList.addFirst("Sharon");
		System.out.println("size is: " + stringList.size());
		stringList.addFirst("SH");
		System.out.println("size is: " + stringList.size());
		stringList.addLast("Wisconsin");
		System.out.println("size is: " + stringList.size());
		System.out.println("First element is: " + stringList.removeFirst());
		System.out.println("Last element is: " + stringList.removeLast());
		Iterator<String> itr = stringList.iterator();
		while (itr.hasNext()) {
			String current = itr.next();
			System.out.println("Current element is: " + current);
		}
	}

	private class Node<Item> {
		private Item item;
		private Node<Item> next;
		private Node<Item> prev;
		
		public Node(Item item) {
			this.item = item;
			this.next = null;
			this.prev = null;
		}
		
		public void setNext(Node<Item> node) {
			
			next = node;
			
		}
		
		public void setPrev(Node<Item> node) {
			
			prev = node;
			
		}
		
		public Node<Item> getNext() {
			
			return next;
			
		}
		
		public Node<Item> getPrev() {
			
			return prev;
			
		}
		
		public Item getItem() {
			
			return item;
			
		}
	}
		
		private class DequeueIterator<Item> implements Iterator<Item> {
			
			private Node<Item> current;
			
			public DequeueIterator(Node<Item> node) {
				this.current = node;
			}

			@Override
			public boolean hasNext() {

				return current != null;
			}

			@Override
			public Item next() {
				if (this.hasNext()) {
					Item item = current.getItem();
					current = current.getNext();
					return item;
				} else {
					throw new NoSuchElementException();
				}
				
			}
			
		}

	
}



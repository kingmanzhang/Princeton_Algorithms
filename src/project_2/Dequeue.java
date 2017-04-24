package project_2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Dequeue<Item> implements Iterable<Item> {
	private Node<Item> head;
	private Node<Item> tail;
	private int size;
	
	public Dequeue() {
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
		
		if(item == null) {
			throw new NoSuchElementException();
		}
		
		Node<Item> newNode = new Node<Item>(item);
		
		if(size == 0) {
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
		
		if(item == null) {
			throw new NoSuchElementException();
		}
		
		Node<Item> newNode = new Node<Item>(item);
		
		if(size == 0) {
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
		
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		Node<Item> toRemove = head;
		size--;
		if(size == 0) {
			head = null;
			tail = null;
		} else {
			head = head.getNext();
		}
		
		return toRemove.getItem();	
	}
	
	public Item removeLast() {
		
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		Node<Item> toRemove = tail;
		size--;
		if(size == 0) {
			head = null;
			tail = null;
		} else {
			tail = tail.getPrev();
		}
		
		return toRemove.getItem();	
		
	}
	

	@Override
	public Iterator<Item> iterator() {
		
		return null;
	}
	
	public static void main(String[] args) {
		
		
	}

}

class Node <Item> {
	Item item;
	Node next;
	Node prev;
	
	public Node(Item item) {
		this.item = item;
		next = null;
		prev = null;
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
	
	class DequeueIterator<Item> implements Iterator<Item> {
		
		private Node<Item> current;
		private Item next;
		
		public DequeueIterator(Node<Item> node) {
			this.current = node;
			next = null;
		}

		@Override
		public boolean hasNext() {

			return current.getNext() != null;
		}

		@Override
		public Item next() {
	
			return current.getItem();
		}
		
	}
}

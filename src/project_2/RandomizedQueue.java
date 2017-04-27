package project_2;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item>{
	
	private Item[] list;
	private int count;
	
	public RandomizedQueue() {
		
		this.list = (Item[]) new Object();
		
	}
	
	public boolean isEmpty() {
		
		return count == 0; 
		
	}
	
	public int size() {
		
		return count;
		
	}
	
	public void enqueue(Item item) {
		
		if (item == null) {
			throw new NullPointerException();
		}
		
		if (count == list.length) {
			resize();
		}
		
		list[count] = item;
		count++;
		
	}
	
	private void resize() {
		
		Item[] newlist = (Item[]) new Object[2 * list.length + 1];
		for (int i = 0; i < list.length; i++) {
			if (list[i] != null) {
				newlist[i] = list[i];
			}
		}
		list = newlist;
	}
	
	public Item dequeue() {
		
		return null;
		
	}
	
	public Item sample() {
		
		return null;
		
	}
	

	public static void main(String[] args) {
		
		
	}
	
	

	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

class RandomizedQueueIterator<Item> {
	
	
	
}

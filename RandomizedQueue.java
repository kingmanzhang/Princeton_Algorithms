
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>{
	
	private Item[] list;
	private int count;
	
	public RandomizedQueue() {
		
		this.list = (Item[]) new Object[1];
		this.count = 0;
		
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
			resize(2 * list.length);
		}
		
		list[count++] = item;
		
	}
	
	private void resize(int capicity) {
		
		Item[] newlist = (Item[]) new Object[capicity];
		for (int i = 0; i < count; i++) {
			if (list[i] != null) {
				newlist[i] = list[i];
			}
		}
		list = newlist;
	}
	
	public Item dequeue() {
		
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		int n = randPosition();
		Item randItem = list[n];
		if (n != count - 1) {
			list[n] = list[count - 1];
		}
		list[count - 1] = null;
		count--;
		if (count > 0 && count == list.length / 4) {
			resize(list.length / 2);
		}
		
		return randItem;
		
	}
	
	public Item sample() {
		
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return list[randPosition()];
		
	}
	
	private int randPosition() {
		
		return StdRandom.uniform(count);	 
		
	}

	public static void main(String[] args) {
		
		RandomizedQueue<String> randQueue = new RandomizedQueue<String>();
		randQueue.enqueue("Aaron");
		randQueue.enqueue("Sharon");
		randQueue.enqueue("Wisconsin");
		randQueue.enqueue("Chicago");
		System.out.println("size of queue is: " + randQueue.size());
		System.out.println("first element is: " + randQueue.sample());
		Iterator<String> itr = randQueue.iterator();
		while (itr.hasNext()) {
			String next =  itr.next();
			if (next != null) {
				System.out.println("current element is: " + next);
			}
		}
		
	}
	
	

	@Override
	public Iterator<Item> iterator() {
		
		return new RandomizedQueueIterator<Item>();
		
	}
	
	private class RandomizedQueueIterator<Item> implements Iterator<Item> {
		
		private Item[] shuffledList;
		private int current;
		
		public RandomizedQueueIterator() {
			shuffledList = (Item[]) new Object[count];
			for (int i = 0; i < count; i++) {
				shuffledList[i] = (Item) list[i];
			}
			StdRandom.shuffle(shuffledList);
			current = 0;
			
		}

		@Override
		public boolean hasNext() {
			
			return current < shuffledList.length;
		}

		@Override
		public Item next() {
			
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			
			return shuffledList[current++];
			
		}
		
		
		@Override
		public void remove() {
			
			throw new UnsupportedOperationException();
			
		}
	
}
}

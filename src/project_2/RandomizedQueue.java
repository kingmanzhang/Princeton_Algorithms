package project_2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item>{
	
	private Item[] list;
	private int count;
	
	public RandomizedQueue() {
		
		this.list = (Item[]) new Object[1];
		
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
		
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		int n = randPosition();
		Item randItem = list[n];
		list[n] = null;
		return randItem;
		
	}
	
	public Item sample() {
		
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return list[randPosition()];
		
	}
	
	private int randPosition() {
		
		Random rand = new Random();
		int randNum = rand.nextInt(size());
		return randNum;
		
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
		while(itr.hasNext()) {
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
			
			shuffledList = (Item[]) list.clone();
			shuffle(shuffledList);
			current = 0;
			
		}

		@Override
		public boolean hasNext() {
			
			return current < shuffledList.length;
		}

		@Override
		public Item next() {
			
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			return shuffledList[current++];
			
		}
		
		private void shuffle(Item[] list) {
			
			for (int i = 0; i < list.length - 1; i++) {
				Random rand = new Random();
				int j = i + rand.nextInt(list.length - i - 1) + 1;
				Item swap = list[j];
				list[j] = list[i];
				list[i] = swap;
				
		
			}
			
		}
		
		@Override
		public void remove() {
			
			throw new UnsupportedOperationException();
			
		}
	
}
}

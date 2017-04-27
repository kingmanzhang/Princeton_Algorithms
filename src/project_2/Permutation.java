package project_2;

import java.util.Iterator;
import java.util.Scanner;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
	
	
	public static void main(String[] args) {
		RandomizedQueue<String> randQueue = new RandomizedQueue<String>();
		if(args.length == 0) {
			System.out.println("need a number");
			return;
		}
		
		int k = Integer.parseInt(args[0]); //number of strings to print
		int i = 0;
		
		String input = StdIn.readLine();
		Scanner scnr = new Scanner(input);
		while (scnr.hasNext()) {
			randQueue.enqueue(scnr.next());
		}
		scnr.close();

		
		
		Iterator<String> itcr = randQueue.iterator();
		while (itcr.hasNext() && i < k) {
			String next =  itcr.next();
			if (next != null) {
				System.out.println(next);
				i++;
			}
		}
		
		
		
	}

}

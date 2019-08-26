package practiceProblems;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class PrimeFactorsLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// The Sieve Method: Credited to a greek mathematician Erathostenes
		// linked list approach. Remove the non prime elements 
		// then the list will eventually be prime only
		
		// then find the prime factors
		
		LinkedList<Integer> primeNumbers = new LinkedList<Integer>();
		LinkedList<Integer> allNumbers = new LinkedList<Integer>();
		
		int n = 7777;
		
		int m = n;
		
		allNumbers.add(2);
		
		// skip adding even numbers. help speed this up a little. 
		// we know theyre just going to be deleted because even numbers arent prime except 2.
		for (int i = 3; i <= n; i=i+2) {
			allNumbers.add(i);
		}
		
		int current;
		int num;
		
		while (!allNumbers.isEmpty()) {
			
			current = allNumbers.pop();
			primeNumbers.add(current);

			// System.out.println(current);
			// System.out.println(allNumbers);
			// System.out.println(primeNumbers);

			

			for (Iterator<Integer> i = allNumbers.iterator(); i.hasNext();) {
				num = i.next();
				if (current == 2) {
					// we already took care of this
					/*
					if ((num & 1) == 0) {
						i.remove();
					}
					*/
				}
				else {
					if (num % current == 0) {
						i.remove();
					}
				}

			}
			//System.out.println(allNumbers);
		}
		
		System.out.println(primeNumbers);
		System.out.println("Above is a list of all the positive prime numbers <= " + n + ":");
		

		Stack<Integer> s = new Stack<Integer>();
		
		while (n != 1) {

			for (Iterator<Integer> i = primeNumbers.iterator(); i.hasNext();) {
				num = i.next();
				if (num == 2) {
					while ((n & 1) == 0) {
						s.add(2);
						n = n >> 1;
						
					}
					i.remove();
				} else {
					while (n % num == 0) {
						s.add(num);
						n /= num;
						
					}
					i.remove();
				}

			}
			
		}
		System.out.println("\nPrime Factors of " + m + ":");
		System.out.println(s);
	}

}

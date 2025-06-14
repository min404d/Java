package java_collection_collections;

import java.util.Set;
import java.util.TreeSet;

public class setexample {
	public static void main(String[] args) {
		Set<Integer> numbers = new TreeSet<>();
		numbers.add(5);
		numbers.add(2);
		numbers.add(8);
		numbers.add(2); 
		numbers.add(5);
	

		System.out.println("Sorted numbers: " + numbers);
}  
	}

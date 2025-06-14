package java_collection_collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Listofjava {
	
 public static void main(String[] args) {
	 List<String> Phone= new ArrayList<>();
	 Phone.add("Redmi");
	 Phone.add("Oppo");
	 Phone.add("Infinix");
	 Phone.add("Samsung");
	 Phone.add("IPhone");
	 
	 System.out.println("Phones :"+Phone.get(1));
	 Phone.set(1,"IPhone");
	 Phone.remove("IPhone");
	 System.out.println("updated phones :"+Phone) ;
	 
	 	Collections.sort(Phone);
	 	System.out.println("Sort : "+ Phone );
	 	
	 	List<String> startsWithi = Phone.stream()
	 			.filter(f -> f.startsWith("I"))
	 			.collect(Collectors.toList());
	 	
	 	System.out.println("Phone start with I : "+startsWithi);
	 	
	 	System.out.println("Size :"+ Phone.size());
	 	System.out.println("Contain IPhone "+ Phone.contains("IPhone"));
	 	System.out.println("IS empty "+ Phone.isEmpty());
 }
}

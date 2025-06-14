package java_collection_collections;


import java.util.HashMap;
import java.util.Map;

public class mapexample {

    public static void main(String[] args) {

        Map<String, String> countryMap = new HashMap<>();

       
        countryMap.put("US", "United States");
        countryMap.put("IN", "India");
        countryMap.put("JP", "Japan");

       
        countryMap.put("IN", "Bharat");

      
        System.out.println("All Countries:");
        for (Map.Entry<String, String> entry : countryMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

      
        String country = countryMap.get("US");
        System.out.println("\nCountry with code US: " + country);

        System.out.println("Contains JP? " + countryMap.containsKey("JP"));
        System.out.println("Contains value 'India'? " + countryMap.containsValue("India"));

    
        countryMap.remove("JP");

     
        System.out.println("\nAfter removing JP:");
        countryMap.forEach((code, name) -> System.out.println(code + ": " + name));
    }
}

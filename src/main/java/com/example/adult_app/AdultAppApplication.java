package com.example.adult_app;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdultAppApplication {

	public static void main(String[] args) throws InterruptedException  {
		SpringApplication.run(AdultAppApplication.class, args);
		
        Map<String, String> map1 = new HashMap<>();
        Map<List<Video> , String> map2 = new HashMap<>();
        List<Video>  vid1 = new ArrayList<>();
        map2.put(vid1, "");
//        map1.put("Apple", "Fruit");
//        map1.put("Table", "Furniture");
//        map1.put("Car", "Vehicle");
//        map1.put("Apple1", "Fruit");
//        map1.put("Table1", "Furniture");
//        map1.put("Car1", "Vehicle");
//        map1.put("Apple12", "Fruit");
//        map1.put("Table2", "Furniture");
//        map1.put("Car2", "Vehicle");
//        map1.put("Apple123", "Fruit");
//        map1.put("Table12", "Furniture");
//        map1.put("Car13", "Vehicle");

        for (String key : map1.keySet()) {
            int hash = key.hashCode();
            int bucket = hash & (16 - 1); // simulate default capacity bucket index
            System.out.println("Key: " + key + 
                               ", HashCode: " + hash + 
                               ", Bucket: " + bucket);
        }
        
        
        for(Entry<String, String> map : map1.entrySet()) {
        	System.out.println("KEys are:"+map.getKey()+":"+map.getValue());
        }
        
        
    		
	}
	
}

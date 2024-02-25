// CoryCaddellAssignment3B.java

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CoryCaddellAssignment3B {
	
	/** Program to demonstrate methods of user-defined Hash Map and Set */
	public static void main(String[] arg) throws FileNotFoundException  {
		
		CoryCaddellHashMap<String, Integer> CoryCaddellHashMap = getHashMap();	// Hash map containing US state/territories and respective estimated population
		Set<CoryCaddellMap.Entry<String, Integer>> entrySet = new HashSet<>();	// Set of CoryCaddellHashMap entries
		Set<String> keySet = new HashSet();										// Set of CoryCaddellHashMap keys
		Set<Integer> valueSet = new HashSet();									// Set of CoryCaddellHashMap values
		
		CoryCaddellHashSet<String> CoryCaddellHashSet = getHashSet();			// Hash set containing US state/territories
		Iterator<String> iterator;												// Hash set iterator

		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Map - containsKey() >>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("Contains \"U.S. Virgin Islands\"?\t" + CoryCaddellHashMap.containsKey("U.S. Virgin Islands"));				// true
		System.out.println("Contains \"Canada\"?\t" + CoryCaddellHashMap.containsKey("Canada"));										// false
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Map - containsValue >>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("Contains \"106,235\"?\t" + CoryCaddellHashMap.containsValue(CoryCaddellHashMap.get("U.S. Virgin Islands")));	// true
		System.out.println("Contains \"-1\"?\t" + CoryCaddellHashMap.containsValue(CoryCaddellHashMap.get("-1")));						// false
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Map - entrySet >>>>>>>>>>>>>>>>>>>>>>>>>");
		entrySet = CoryCaddellHashMap.entrySet();
		entrySet.forEach(e -> System.out.println(e));
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Map - get >>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("Getting value for \"U.S. Virgin Islands\" key: " + CoryCaddellHashMap.get("U.S. Virgin Islands"));
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Map - isEmpty >>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("Is the original map empty? " + CoryCaddellHashMap.isEmpty());	// false
		System.out.println("What about this map? " + new CoryCaddellHashMap().isEmpty());	// true
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Map - keySet >>>>>>>>>>>>>>>>>>>>>>>>>");
		keySet = CoryCaddellHashMap.keySet();
		keySet.forEach(e -> System.out.println(e));

		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Map - put >>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("Adding \"testing, -1\" to map:");
		CoryCaddellHashMap.put("TESTING", -1);
		System.out.print(CoryCaddellHashMap.CoryCaddellOutput());
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Map - remove >>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("Removing \"testing, -1\" to map:");
		CoryCaddellHashMap.remove("TESTING");
		System.out.print(CoryCaddellHashMap.CoryCaddellOutput());
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Map - size >>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("Map size: " + CoryCaddellHashMap.size());
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Map - values >>>>>>>>>>>>>>>>>>>>>>>>>");
		valueSet = CoryCaddellHashMap.values();
		valueSet.forEach(e -> System.out.println(e));
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Map - toString >>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.print(CoryCaddellHashMap.toString());
		System.out.println();
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Map - Output >>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.print(CoryCaddellHashMap.CoryCaddellOutput());
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Map - Clear >>>>>>>>>>>>>>>>>>>>>>>>>");
		CoryCaddellHashMap.clear();
		System.out.println(CoryCaddellHashMap.toString());
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Set - Contains >>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("Contains \"U.S. Virgin Islands\"?\t" + CoryCaddellHashSet.contains("U.S. Virgin Islands"));	// true
		System.out.println("Contains \"Canada\"?\t" + CoryCaddellHashSet.contains("Canada"));							// false
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Set - add >>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("Adding \"testing\" to set:");
		CoryCaddellHashSet.add("TESTING");
		System.out.print(CoryCaddellHashSet.CoryCaddellOutput());
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Set - remove >>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("Removing \"testing\" from set:");
		CoryCaddellHashSet.remove("TESTING");
		System.out.print(CoryCaddellHashSet.CoryCaddellOutput());
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Set - isEmpty >>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("Is the original set empty? " + CoryCaddellHashSet.isEmpty());	// false
		System.out.println("What about this set? " + new CoryCaddellHashSet().isEmpty());	// true
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Set - size >>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("Set size: " + CoryCaddellHashSet.size());
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Set - iterator >>>>>>>>>>>>>>>>>>>>>>>>>");
		iterator = CoryCaddellHashSet.iterator();
		
		System.out.println("Iterator remove method will remove the following: ");
		while(iterator.hasNext()) {
			System.out.println(iterator.next() + " ");
			iterator.remove();
		}
		System.out.println(CoryCaddellHashSet.toString());
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Set - addAll >>>>>>>>>>>>>>>>>>>>>>>>>");
		CoryCaddellHashSet.addAll(keySet);	// add the keys we got from Hash Map
		System.out.print(CoryCaddellHashSet.CoryCaddellOutput());
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Set - toString >>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.print(CoryCaddellHashSet.toString());
		System.out.println();
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Set - containsAll >>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(CoryCaddellHashSet.containsAll(keySet));	// true
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Set - toArray Object[] >>>>>>>>>>>>>>>>>>>>>>>>>");
		Object[] myArray = CoryCaddellHashSet.toArray();
		for(Object e: myArray) {
			System.out.println(e + " ");
		}
		System.out.println();
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Set - toArray T[] >>>>>>>>>>>>>>>>>>>>>>>>>");
		myArray = CoryCaddellHashSet.toArray(myArray);
		for(Object e: myArray) {
			System.out.println(e + " ");
		}
		System.out.println();
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Hash Set - Output >>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(CoryCaddellHashSet.CoryCaddellOutput());
				
	}
	
	/** Read data from file.  Store in hash map.  Return hash map. */
	public static CoryCaddellHashMap<String, Integer> getHashMap( ) throws FileNotFoundException {
		String dataPerLine;	// contents of each line of data from file
		String[] mapEntry;	// split content of data to array
		String key;			// map structure key.  second to last element of mapEntry array.
		Integer value;		// map structure value. last element of mapEntry array
		CoryCaddellHashMap<String, Integer> CoryCaddellMap = new CoryCaddellHashMap<>();	// data structure containing state and population data
		
		// Read data from file
		File file = new File("Assignment3BData.txt");
		if (!file.exists())
		{
			System.out.print("File does not exist.");
			System.exit(0);
		}

		Scanner input = new Scanner(file);
		while(input.hasNext()) {
			dataPerLine = input.nextLine();
			mapEntry = dataPerLine.split("	");
			
			// First to last element is key
			key = mapEntry[mapEntry.length - 2].trim();
			
			// Last element is value
			value = Integer.valueOf(mapEntry[mapEntry.length - 1]);
			
			// Add entry to data structure
			CoryCaddellMap.put(key, value);
		}
		input.close();
		
		return CoryCaddellMap;
	}
	
	/** Read data from file.  Store in Hash Set.  Return hash set. */
	public static CoryCaddellHashSet<String> getHashSet( ) throws FileNotFoundException {
		String dataPerLine;		// contents of each line of data from file
		String[] dataSplit;		// split content of data to array
		CoryCaddellHashSet<String> CoryCaddellHashSet = new CoryCaddellHashSet<>();	// list of states/territories
		
		// Read data from file
		File file = new File("Assignment3BData.txt");
		if (!file.exists())
		{
			System.out.print("File does not exist.");
			System.exit(0);
		}

		Scanner input = new Scanner(file);
		while(input.hasNext()) {
			dataPerLine = input.nextLine();
			dataSplit = dataPerLine.split("	");
			
			CoryCaddellHashSet.add(dataSplit[0]);
		}
		input.close();
		
		return CoryCaddellHashSet;
	}

}

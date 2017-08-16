package com.naunce.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/*

A sample array of names is below. The format of each element is  "firstname middlename lastname" 
each separated by a space or tab. The firstname and lastname are both  required, middlename is optional. 
Write a method that takes an array of names and a  stream as inputs.
It must print each last name that occurs more than once, followed by a count of how many times it occurs.
The result must be ordered by the count of occurrences, in descending order. 
Write a unit test to validate your solution.

Sample output is below

Gates 3
Johnson 2


A skeleton  Java program is below to help you get started. 
Keep the code simple. No need to use any advanced language features. 

Good Luck!

*/

public class CountLastNames {

	static final String[] names = { "Melinda Ann Gates", "John Michael", "Bill Gates", "Michael Peterson",
			"George W Bush", "Rod Johnson", "Michael Johnson", "Mary Sue Gates"

	};

	public static void main(String[] args) {
		printLastNameCounts(names, System.out);
	}

	private static void printLastNameCounts(String[] names, java.io.PrintStream out) {

		// check if the input array is not null
		if (names != null) {

			List<String> namesList = Arrays.asList(names);
			Map<String, Integer> lastNameCounterMap = new HashMap<String, Integer>();

			for (String fullName : namesList) {
				String lastName = getLastName(fullName);
				Integer lastNameCount = lastNameCounterMap.get(lastName);

				if (lastNameCount != null) {
					lastNameCounterMap.put(lastName, lastNameCount + 1);
				} else {
					lastNameCounterMap.put(lastName, 1);
				}

			}
			
			//print the sorted map of lastNames counter
			out.println(sortMapByValue(lastNameCounterMap));

		} else {
			out.println("Input is null");
		}

	}

	/**
	 * This method sorts the input Map by values in the map
	 * 
	 * @param map
	 * @return
	 */
	private static <K, V extends Comparable<? super V>> Map<K, V> sortMapByValue(Map<K, V> map) {
		return map.entrySet()
				  .stream()
				  .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
				  .collect(
						Collectors.toMap(
								Map.Entry::getKey, 
								Map.Entry::getValue, 
								(e1, e2)	 -> e1, 
								LinkedHashMap::new));
	}

	/**
	 * Get LastName returns the last name from the name (FirstName Middle LastName)
	 * Assumption is, Middle Name is optional in the input, but first and last names
	 * are mandatory
	 * 
	 * @param fullName
	 * @return the lastname from the given fullName
	 */
	private static String getLastName(String fullName) {
		String[] names = fullName.split(" ");
		return names.length == 3 ? names[2] : names[1];
	}
}
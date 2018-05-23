package com.sergio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {

		List<String> someBingoNumbers = Arrays.asList(
				"N40", "N36",
				"B12", "B6",
				"G53", "G49", "G60", "G50", "g64",
				"I26", "I17", "I29",
				"071");
		
		List<String> gNumbers = new ArrayList<>();

		// old style of adding to List, sorting and printing
//		someBingoNumbers.forEach( number -> {
//			if(number.toUpperCase().startsWith("G")) {
//				gNumbers.add(number);
////				System.out.println(number);
//			}
//		});
//		
//		gNumbers.sort((String s1, String s2) -> s1.compareTo(s2));
//		gNumbers.forEach((String s) -> System.out.println(s));
//		
		// we can do all of the above in one line of code
		someBingoNumbers
			.stream()
			.map(String::toUpperCase) // :: is called a "method reference" , Class::method
			//.map(s -> s.toUpperCase) // would be the same as above
			.filter(s -> s.startsWith("G")) //expects a predicate, returns true or false
			.sorted()
			.forEach(System.out::println); // called a terminal operation, returns void or non-stream result

		
		Stream<String> ioNumberStream = Stream.of("I26", "I17", "I29", "O71");
		Stream<String> inNumberStream = Stream.of("N40", "N36", "I26", "I17", "I29", "O71");
		Stream<String> concatStream = Stream.concat(ioNumberStream, inNumberStream);
		System.out.println("----------------------");
		System.out.println(concatStream.distinct()
				.peek(System.out::println)
				.count());
		
	}

}

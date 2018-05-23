package com.sergio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
		
		Employee john = new Employee("John Doe", 30);
		Employee jane = new Employee("Jane Deer", 25);
		Employee jack = new Employee("Jack Hill", 40);
		Employee snow = new Employee("Snow White", 22);

		Department hr = new Department("Human Resources");
		hr.addEmployee(jane);
		hr.addEmployee(jack);
		hr.addEmployee(snow);
		
		Department accounting = new Department("Accounting");
		accounting.addEmployee(john);
		
		List<Department> departments = new ArrayList<>();
		departments.add(hr);
		departments.add(accounting);
		
		//using flatMap. It is called flatMap because it is often used to flaten nested lists
		departments.stream()
			.flatMap(department -> department.getEmployees(). stream())
					.forEach(System.out::println);
		
//		List<String> sortedGNumbers = someBingoNumbers
//				.stream()
//				.map(String::toUpperCase)
//				.filter(s -> s.startsWith("G"))
//				.sorted()
//				.collect(Collectors.toList()); // we now have a list we can continue to work with
				
		List<String> sortedGNumbers = someBingoNumbers
				.stream()
				.map(String::toUpperCase)
				.filter(s -> s.startsWith("G"))
				.sorted()
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		
				for(String s : sortedGNumbers) {
					System.out.println(s);
				}
		
			Map<Integer, List<Employee>> groupedByAge = departments.stream()
					.flatMap(department -> department.getEmployees().stream())
					.collect(Collectors.groupingBy(employee -> employee.getAge()));
		 System.out.println(groupedByAge);
		 
		 departments.stream()
		 	.flatMap(department -> department.getEmployees().stream())
		 	.reduce((e1,e2) -> e1.getAge() < e2.getAge() ? e1 : e2) // Bifunction , this is a terminal operation
		 	.ifPresent(System.out::println); // print the result
		 
		 //stream operations are lazy-instantiated
		 Stream.of("ABC", "AC", "BAA", "CCCC", "XY", "ST")
		 .filter(s -> {
			 System.out.println(s);
			 return s.length() == 3;
		 })
		 .count();
	}

}

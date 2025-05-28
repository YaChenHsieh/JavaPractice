//Java 8 introduced the Stream API to simplify and improve the processing of collections.
//It allows for functional-style operations such as filtering, mapping, and sorting through a fluent and declarative syntax.
//A stream pipeline consists of a data source, intermediate operations (which are lazy), and a terminal operation (which triggers execution).
//Intermediate operations can be stateless (like filter) or stateful (like sorted).
//Stream API also supports parallel processing with .parallelStream() for better performance on large data sets.

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamAPIReview {
  public static void main(String[] args) {

    // List
    // 1st
    List<String> names = Arrays.asList("Alice", "Bob", "Andy", "Ben");
    names.stream()
        .filter(name -> name.startsWith("A")) // Keep name start with A
        .forEach(System.out::println);

    // 2st
    List<String> filtered = names.stream()
        .filter(name -> name.startsWith("B"))
        .map(name -> name.toUpperCase())
        .sorted()
        .collect(Collectors.toList());

    filtered.forEach(System.out::println);

    // Set
    Set<Integer> numbers = new HashSet<>();
    numbers.add(12);
    numbers.add(3);
    numbers.add(21);
    numbers.add(23);
    numbers.add(4);
    numbers.stream()
        .map(number -> number + 2)
//        .filter(number -> number % 2 == 0) // keep Even
        .sorted()
        .forEach(System.out::println);

    // Queue

    // Map -> not in collection, but can turn into set then do the streamAPI
    // map.entrySet().stream()  -> Stream<Map.Entry<K,V>> operation Key + Value
    // map.keySet().stream() -> Stream<K> only processes Key
    // map.values().stream() -> Stream<V> only processes Value
    Map<String, Integer> scores = new HashMap<>();
    scores.put("Tom", 90);
    scores.put("Jerry", 75);
    scores.put("Alice", 85);
    scores.put("Apple", 88);

    // filter name from score > 80
    List<String> highScorers = scores.entrySet().stream()
        .filter(entry -> entry.getKey().startsWith("A"))
        .map(Map.Entry::getKey) // keep only name
//        .filter(entry -> entry.getValue() > 80)
//        .map(Map.Entry::getKey) // keep only name
        .collect(Collectors.toList());

    System.out.println(highScorers); // [Tom, Alice]

  }
}
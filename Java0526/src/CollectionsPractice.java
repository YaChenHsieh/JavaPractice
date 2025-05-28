import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.swing.Spring;

// SQL question
// Q1
// Write a SQL query to count the number of employees in each department."
// All employee records include a department name column


// Q2 (use join and groupby)
// Write a SQL query to display the number of employees in each department,
// using the employees and departments tables.
// The employees table contains a department_id column that references the id column in the departments table


public class CollectionsPractice {

  public static void main(String[] args) {

    // create comparator
    Comparator<TTEmployee> maxHeapComparator = (e1, e2) -> Integer.compare(e2.getAge(), e1.getAge());
    Comparator<TTEmployee> minHeapComparator = (e1, e2) -> Integer.compare(e1.getAge(), e2.getAge());

    // Add Employees
    TTEmployee emp1 = new TTEmployee(101, 21 , "Alice", List.of("Java", "SQL")); // List.of() -> immutable
    TTEmployee emp2 = new TTEmployee(102, 35, "Bob", List.of("Python", "AWS"));
    TTEmployee emp3 = new TTEmployee(103, 29, "Cathy", List.of("JavaScript", "React"));
    TTEmployee emp4 = new TTEmployee(104, 31, "Denial", List.of("Ruby", "React"));

    // Create Arraylist
    List<TTEmployee> workers = new ArrayList<>();
    workers.add(emp1);
    workers.add(emp2);
    workers.add(emp3);
    workers.add(emp4);

    // Collections.sort with Comparator
    Collections.sort(workers, minHeapComparator);

    System.out.println("ArrayList sorted by minHeapComparator:");
    for (TTEmployee e : workers) {
      System.out.println("ID: " + e.getId() + ", Name: " + e.getName() + ", Age: " + e.getAge());
    }

    // Create priority queue
    PriorityQueue<TTEmployee> minHeap = new PriorityQueue<>(minHeapComparator);
    PriorityQueue<TTEmployee> maxHeap = new PriorityQueue<>(maxHeapComparator);

    // minHeap -> add employee
    minHeap.add(emp1);
    minHeap.add(emp2);
    minHeap.add(emp3);
    minHeap.add(emp4);

    // maxHeap -> add employee
    maxHeap.add(emp1);
    maxHeap.add(emp2);
    maxHeap.add(emp3);
    maxHeap.add(emp4);

    System.out.println("PriorityQueue sorted by minHeapComparator:");
    while (!minHeap.isEmpty()) {
      TTEmployee empMin = minHeap.poll();
      System.out.println("ID: " + empMin.getId() + ", Name: " + empMin.getName() + ", Age: " + empMin.getAge());
    }

    System.out.println("PriorityQueue sorted by maxHeapComparator:");
    while (!maxHeap.isEmpty()) {
      TTEmployee empMax = maxHeap.poll();
      System.out.println("ID: " + empMax.getId() + ", Name: " + empMax.getName() + ", Age: " + empMax.getAge());
    }

    // Map
    SortedMap<String, Integer> map = new TreeMap<>(Comparator.reverseOrder());

    // Add key value pair
    map.put(emp1.getName(), emp1.getAge());
    map.put(emp2.getName(), emp2.getAge());
    map.put(emp3.getName(), emp3.getAge());
    map.put(emp4.getName(), emp4.getAge());

    // Map using Entry to make it iterable
    System.out.println("Map sorted by reverse order:");
    for (SortedMap.Entry<String, Integer> entry : map.entrySet()) {
      System.out.println("Name: " + entry.getKey() + ", Age: " + entry.getValue());
    }

  }
}

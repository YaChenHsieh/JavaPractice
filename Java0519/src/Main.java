import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

  public static void main(String[] args) {

    // Arr vs List
    //          ---> Interface ====> ArrayList LinkedList

    // int[]
    // Integer[]
    // List<int>
    // List<Integer>


    // Comparable -> compareTo(1 input parameter)
    // Comparator -> compare(2 input parameters)


    // Comparable -> directly implementation to the source code -> Employee inherit Comparable interface
    // Comparator -> lambda expression
    // intrusion vs non-intrusion

    // micro-service architecture

    // spring cloud --> intrusion springboot source
    //  kubernetes cluster -> k8s non-intrusion





    int i = 777777;
    List<Integer> list = new ArrayList<>();
    list.add(i); // auto-boxing
    System.out.println(list);

    // 20% algorithm + data structure
//    coding --> 1. java syntax 10%
//               2. idea -> convert the idea to java 20%
    // 50% ---> 30% communication ----> 1%   declare ambiguities -> // input + output
    //          20% ----> corner test, unit test, space/time complexity, code quality, code optimization etc...


    // heap public
    // stack private
    // thread




//    // ===========================
//    // 1. Basic Syntax
//    // ===========================
//    // Input (Scanner)
//    java.util.Scanner sc = new java.util.Scanner(System.in);
//    int input = sc.nextInt();
//    String strInput = sc.nextLine();
//
//    // ===========================
//    // 2. Arrays
//    // ===========================
//
//    // 1D Array
//    int[] arr = new int[5];
//    arr[0] = 1;
//    int[] arr2 = {1, 2, 3, 4, 5};
//
//    // 2D Array
//    int[][] matrix = new int[3][3];
//    matrix[0][0] = 1;
//    int[][] matrix2 = {{1, 2}, {3, 4}};
//
//    // Array Length
//    int len = arr.length;
//
//    // Sorting
//    java.util.Arrays.sort(arr);
//
//    // ===========================
//    // 3. Strings
//    // ===========================
//
//    // String Methods
//    String s = "Hello";
//    int sLen = s.length();
//    char c = s.charAt(0);
//    String sub = s.substring(1, 3); // index between [1, 3)
//    String sub2 = s.substring(3); // index between [3, +)
//    String upper = s.toUpperCase();
//    String lower = s.toLowerCase();
//    boolean contains = s.contains("ell");
//    String replaced = s.replace("H", "J");
//    int index = "+-*/".indexOf("c"); // not contained: -1
//
//    // Check if a character is a digit or letter
//    char ch = 'A';
//    boolean isDigit = Character.isDigit(ch); // true if '0'-'9'
//    boolean isLetter = Character.isLetter(ch); // true if 'A'-'Z' or 'a'-'z'
//
//    // StringBuilder
//    StringBuilder sb = new StringBuilder();
//    sb.append("Hello");
//    sb.append(" ");
//    sb.append("World");
//    String result = sb.toString();
//
//    // ===========================
//    // 4. Collections
//    // ===========================
//
//    // List
//    java.util.List<Integer> list = new java.util.ArrayList<>();
//    list.add(1);
//    list.add(2);
//    int first = list.get(0);
//    list.remove(0);
//    int size = list.size();
//
//    // Set
//    java.util.Set<Integer> set = new java.util.HashSet<>();
//    set.add(1);
//    set.add(2);
//    set.remove(3);
//    boolean setContains = set.contains(1);
//
//    // Map
//    java.util.Map<String, Integer> map = new java.util.HashMap<>();
//    map.put("a", 1);
//    map.put("b", 2);
//    int value = map.get("a");
//    Integer a1 = map.remove("a");
//    boolean containsKey = map.containsKey("a");
//
//    // Iterate over a Map
//    for (java.util.Map.Entry<String, Integer> entry : map.entrySet()) {
//      System.out.println(entry.getKey() + ": " + entry.getValue());
//    }
//
//    // getOrDefault
//    int valueOrDefault = map.getOrDefault("c", 0); // Returns 0 if key "c" doesn't exist
//
//    // putIfAbsent
//    Map<Integer, Set<Integer>> map2 = new java.util.HashMap<>();
//    map2.putIfAbsent(1, new HashSet<>(List.of(2)));
//
//    // Sorting a HashMap by Value
//    java.util.Map<String, Integer> map1 = new java.util.HashMap<>();
//    map1.put("Alice", 25);
//    map1.put("Bob", 30);
//    map1.put("Charlie", 20);
//    map1.put("David", 25);
//    java.util.List<java.util.Map.Entry<String, Integer>> entryList = new java.util.ArrayList<>(map1.entrySet());
//    entryList.sort(java.util.Map.Entry.comparingByValue());
//    java.util.Map<String, Integer> sortedMap = new java.util.LinkedHashMap<>();
//    for (java.util.Map.Entry<String, Integer> entry : entryList) {
//      sortedMap.put(entry.getKey(), entry.getValue());
//    }
//    // Print the sorted map
//    System.out.println(sortedMap);
//
//    // ===========================
//    // 5. Queue and Stack
//    // ===========================
//
//    // Queue (FIFO) using ArrayDeque
//    java.util.Deque<Integer> queue = new java.util.ArrayDeque<>();
//    queue.offerLast(1); // Enqueue (add to the end)
//    queue.offerLast(2);
//    int front = queue.pollFirst(); // Dequeue (remove from the front)
//    boolean queueIsEmpty = queue.isEmpty();
//
//    // Stack (LIFO) using ArrayDeque
//    java.util.Deque<Integer> stack = new java.util.ArrayDeque<>();
//    stack.offerFirst(1); // Push (add to the front)
//    stack.offerFirst(2);
//    int top = stack.pollFirst(); // Pop (remove from the front)
//    boolean stackIsEmpty = stack.isEmpty();
//
//    // ===========================
//    // 6. Priority Queue
//    // ===========================
//    // Min-Heap (default)
//    java.util.PriorityQueue<Integer> minHeap = new java.util.PriorityQueue<>();
//    minHeap.offer(3);
//    minHeap.offer(1);
//    minHeap.offer(2);
//    int min = minHeap.poll(); // 1
//
//    // Max-Heap
//    java.util.PriorityQueue<Integer> maxHeap = new java.util.PriorityQueue<>((a, b) -> Integer.compare(b, a));
//    maxHeap.offer(3);
//    maxHeap.offer(1);
//    maxHeap.offer(2);
//    int max = maxHeap.poll(); // 3
//
//    // Custom Sorting (e.g., by string length)
//    java.util.PriorityQueue<String> pq = new java.util.PriorityQueue<>((a, b) -> Integer.compare(a.length(), b.length()));
//    pq.offer("apple");
//    pq.offer("banana");
//    pq.offer("kiwi");
//    String shortest = pq.poll(); // "kiwi"
//    // Comparator for alphabetical sorting (case-sensitive)
//    java.util.Comparator<String> alphabeticalComparator = String::compareTo;
//
  }
}

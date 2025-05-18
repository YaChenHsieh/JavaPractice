public class Main {
  public static void main(String[] args) {
    System.out.printf("Hello and welcome!\n");

    Integer i1 = new Integer(1);
    Integer i2 = new Integer(1);
    System.out.println(i1 == i2); // false

    Integer i3 = Integer.valueOf(1);
    Integer i4 = Integer.valueOf(1);
    System.out.println(i3 == i4); // true


  }
}

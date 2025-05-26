import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

  public static void main(String[] args) {
    List<String> names = Arrays.asList("Alice", "Bob", "Andy", "Cathy");

    List<String> result = names.stream()
        .filter(name -> name.startsWith("A"))
        .collect(Collectors.toList());

    System.out.println(result); // 輸出: [Alice, Andy]

  }
}
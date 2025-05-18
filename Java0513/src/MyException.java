package Exception;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import javax.management.RuntimeErrorException;

// Exception
// input validation ->
// exception vs error
// checked/Compiled exception vs unchecked/RuntimeException exception
// who is checking ???? Compiler
public class MyException extends RuntimeException{

  // SerilizationUID ---> serlizable interface
  private String errorCode;
  //  tier0 - 7
  // 1 000-999
  // 1      00       10
  // tier   layer    tupe
  // 100
  // 200
  // 300
  // 400 --> clietn side exception
  // 500 --> server side exception
  private String message;

  public void myMethod1() {

    try {
      myMethod2();
    } catch (Exception e) {

    }


  }

  public void myMethod2() throws IOException {
    myMethod3();
  }

  public void myMethod3() throws IOException {
    myMethod4();
  }


  public void myMethod4() throws IOException {
    myMethod5();
  }

  public void myMethod5() throws IOException {
    myMethod();
  }


  public void myMethod() throws IOException {
    throw new FileNotFoundException("File not found");
//      try {
//         throw new FileNotFoundException("File not found");
//      } catch (FileNotFoundException e) {
//         e.printStackTrace();
//      }
//      catch (IOException e) {
//        e.printStackTrace();
//      }
//      catch (NumberFormatException e) {
//        e.printStackTrace();
//      }
//      catch (ConcurrentModificationException e) {
//        e.printStackTrace();
//      } catch (Exception e) {
//        e.printStackTrace();
//      }finally {
//        // exception popped
//        // close resources
//        // release locks
//      }
  }
}

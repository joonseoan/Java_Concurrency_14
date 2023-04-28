package concurrent_lib_4;

import java.util.List;

import static concurrent_lib_4.RunConcurrent.EOF;

public class MyConsumer implements Runnable {
  private List<String> buffer;
  private String color;

  public MyConsumer(List<String> buffer, String color) {
    this.buffer = buffer;
    this.color = color;
  }

  @Override
  public void run() {
    while(true) {
      // To prevent not to be synchronized from the buffer

      synchronized (buffer) {
        // buffer can be suspended because ArrayList does not support synchronization.
        // Therefore `buffer.isEmpty` can keep false!
        if (buffer.isEmpty()) {
          // infinite loop because
          continue;

        }
        // It should remove "EOF" string.
        // If it did that, it will generate infinite loop.
        if (buffer.get(0).equals(EOF)) {
          System.out.println(color + "Exiting");
          break;
        } else {
          System.out.println(color + "Removed " + buffer.remove(0));
        }
      }
    }
  }
}

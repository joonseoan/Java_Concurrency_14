package concurrent_lib_4;

import java.util.List;
import java.util.Random;

public class MyProducer implements Runnable {
  private List<String> buffer;
  private String color;

  public MyProducer(List<String> buffer, String color) {
    this.buffer = buffer;
    this.color = color;
  }

  @Override
  public void run() {
    Random random = new Random();
    String[] nums = { "1", "2", "3", "4", "5" };

    for (String num: nums) {
      try {
        // can be suspended because ArrayList is not synchronized.
        System.out.println(color + "Adding..." + num);

        // It can prevent unsynchronized in ArrayList
        synchronized (buffer) {
          buffer.add(num);
        }

        Thread.sleep(random.nextInt(1000));
      } catch (InterruptedException err) {
        System.out.println("Producer was interrupted.");
      }
    }

    System.out.println(color + "Adding EOF and exiting...");

    // It can prevent unsynchronized in ArrayList
    synchronized (buffer) {
      buffer.add("EOF");
    }
  }
}

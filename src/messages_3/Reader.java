package messages_3;

import java.util.Random;

public class Reader implements Runnable {
  private Message message;

  public Reader(Message message) {
    this.message = message;
  }

  public void run() {
    Random random = new Random();

    // String latestMessage = message.read();
    // String latestMessage = message.read(); generates an infinite loop in the `read()` method in Message class.
    // Then for loop can't finish.
    for(String latestMessage = message.read(); !latestMessage.equals("Finished"); latestMessage = message.read()) {
      System.out.println(latestMessage);

      try {
        Thread.sleep(random.nextInt(2000));
      } catch (InterruptedException err) {

      }
    }
  }
}

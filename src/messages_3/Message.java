package messages_3;

public class Message {
  private String message;
  private boolean empty = true;

  public synchronized String read() {
    System.out.println("read empty: " + empty);
    // [Consumer]
    while (empty) {

    }



    empty = true;
    return message;
  }

  // [Producer]
  public synchronized void write(String message) {
    System.out.println("write empty: " + empty);

    while (!empty) {
//      System.out.println("write!!!!");
    }

    empty = false;
    this.message = message;
  }
}

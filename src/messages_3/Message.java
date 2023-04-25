package messages_3;

public class Message {
  private String message;
  private boolean empty = true;

  public synchronized String read() {
    // [Consumer]
    while (empty) {
    }

    empty = true;
    return message;
  }

  // [Producer]
  public synchronized void write(String message) {
    while (!empty) {

    }

    empty = false;
    this.message = message;
  }
}

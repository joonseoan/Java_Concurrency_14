package messages_3;

import java.util.Random;

class Writer implements Runnable {
  private Message message;

  public Writer(Message message) {
    this.message = message;
  }

  @Override
  public void run() {
    String messages[] = {
      "Humpty Dumpty sat on a all",
      "Humpty Dumpty had a great fall",
      "All the king's horses and all the king's men",
      "Could not put Humpty together again"
    };

    Random random = new Random();

    for (int i = 0; i < messages.length; i++) {
      message.write(messages[i]);

      try {
        /**
         * java.util.Random.nextInt(int n) : The nextInt(int n) is used to get a random number between 0(inclusive)
         * and the number passed in this argument(n), exclusive.
         */
        Thread.sleep(random.nextInt(2000));
      } catch (InterruptedException err) {

      }
    }

    message.write("Finished");
  }
}

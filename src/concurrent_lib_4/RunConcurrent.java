package concurrent_lib_4;

import java.util.ArrayList;
import java.util.List;

// Just a lib for the developer easily implements concurrency and thread.
public class RunConcurrent {
  public static final String EOF = "EOF";

  public static void runConcurrent() {
    // [What we have learned so far]
    // If thread A is running the synchronized method X in class Y,
    // then thread B can't run any methods in class Y until thread A has finished running method X.
    // it is even if thread A is suspended.

    // [IMPORTANT]
    // Now it does not mean that if thread A were to call three methods in a row in class Y,
    // it can run all three methods without worrying about interference from another thread.
    // If thread A is suspended, after it has finished running a method in class Y,
    // Another thread can run a method in class Y before thread A has the chance to run again.
    // The following can happen when using a class, that is class Y that is synchronized or synchronized thread safe.


    // ArrayList itself is not synchronized discussed before.
    List<String> buffer = new ArrayList<>();
    MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_YELLOW);
    MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE);
    MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_CYAN);

    new Thread(producer).start();
    new Thread(consumer1).start();
    new Thread(consumer2).start();

  }
}

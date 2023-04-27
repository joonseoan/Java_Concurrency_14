package messages_3;

public class Message {
  private String message;
  private boolean empty = true;

  // [IMPORTANT!!] FYI.
  // Atomic operation. An atomic operation is an operation which is performed as a single unit of work
  // without the possibility of interference from other operations.
  // There's a few atomic operations in Java that happens all at once.
  // Thread cannot be suspended in the middle of doing the atomic operations.
  // For instance,
  // 1. Reading and Writing reference variable: myObject1.equals(myObject2) would be
  // atomic operation. In this case, thread can't be suspended
  // 2. if reading and writing primitive variables
  //   ([IMPORTANT] except for long and double type that can be suspended
  //    So JVM requires two operations to read and write long and double type) --> thread can be suspended.
  //    int a = 20; cannot be suspended
  //    long / int b = 2.23232323232 can be suspended
  // 3. reading and writing all variables declared volatile (we will see it later on)

  // Collection classes. Some class in Collections are not synchronized (not safe area)
  // ArrayList is not thread safe. If we use ArrayList and multiple threads can access that at a time
  // then we have to be responsible for synchronizing the code that uses the ArrayList. After creating the ArrayList
  // we can call collections.synchronize list method and pass it to ArrayList, alternatively.

  // Vector class is synchronized




  public synchronized String read() {
    System.out.println("read empty: " + empty);
    // [Consumer]
    while (empty) {
      try {
        // works only when it has lock (which means that is an infinite loop
        // from the suspense from the other thread
        // Please keep in mind again that when we use `synchronized`,
        // the thread always can be suspended!!! while executing a single line of the code
        // So the single line of the code calls the methods that contains the hundred of lines of the code.
        wait();
      } catch (InterruptedException err) {

      }
    }

    empty = true;
    notifyAll();
    return message;
  }

  // [Producer]
  public synchronized void write(String message) {
    System.out.println("write empty: " + empty);

    // Why have to use infinite loop?
    // Why not use an if statement and if that fails, why not then call `wait`?
    // The answer is that we always want to call `wait` within a infinite for loop
    // that is testing.
    while (!empty) {
      try {
        // works only when it has lock (which means that is an infinite loop
        // from the suspense from the other thread
        // Please keep in mind again that when we use `synchronized`,
        // the thread always can be suspended!!!
        wait();
      } catch (InterruptedException err) {

      }
    }

    empty = false;
    this.message = message;
    // Why `notifyAll` rather than `notify`?
    // because we can't notify a specific thread
    // that is because the notify thread does not accept any parameters.
    // It is conventional to use `notifyAll` unless we are dealing with a situation
    // when there are significant number of threads that all perform a similar task waiting for a lock.
    // In this case, we do not want to wake every thread because when there is a lot of them,
    // that could result in a huge performance hit.
    notifyAll();
  }
}

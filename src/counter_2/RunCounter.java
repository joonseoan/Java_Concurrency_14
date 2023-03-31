package counter_2;

public class RunCounter {

  // [Interfering] with instance field
  public static void runCounter() {
    // BTW, if we use local variable, it would not have interfering.
    CountDown countDown = new CountDown();

    // We passed the same `countDown` object to two threads.
    CountdownThread t1 = new CountdownThread(countDown);
    t1.setName("Thread 1");
    CountdownThread t2 = new CountdownThread(countDown);
    t2.setName("Thread 2");

//    t1.start();
//    t2.start();
  }

  // Racing - Synchronization
  public static void runRacing() {
    // [Synchronization] Racing

    // Instead of two objects, we can use "SYNCHRONIZATION" to control shared heap memory (instance field).
    // We can synchronize methods and statements.
    // When we can synchronize methods, only one thread can execute that at a time.
    // So when a thread is executing the method, or other threads that we want to call the method,
    // or any other synchronized method in that class will suspend until the thread running the method exits.
    // It will always have the same result.

    // Adding 'synchronized' keyword to the method. (FYI, constructor must not have this keyword)

    CountDown cd = new CountDown();

    CountdownThread cdt1 = new CountdownThread(cd);
    cdt1.setName("Thread 1");
    CountdownThread cdt2 = new CountdownThread(cd);
    cdt2.setName("Thread 2");

    cdt1.start();
    cdt2.start();

  }
}

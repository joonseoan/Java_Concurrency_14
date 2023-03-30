package counter_2;

public class RunCounter {
  public static void runCounter() {
    CountDown countDown = new CountDown();

    CountdownThread t1 = new CountdownThread(countDown);
    t1.setName("Thread 1");
    CountdownThread t2 = new CountdownThread(countDown);
    t2.setName("Thread 2");

    t1.start();
    t2.start();
  }
}

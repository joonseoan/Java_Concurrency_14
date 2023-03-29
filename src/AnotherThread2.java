public class AnotherThread2 extends Thread {
  @Override
  public void run() {
    // Run this first!
    System.out.println(ThreadColor.ANSI_CYAN + "Hello from " + currentThread().getName());

    try {
      Thread.sleep(3000);
    } catch (InterruptedException err) {
      System.out.println(ThreadColor.ANSI_CYAN + "Another thread woke me up!");
    }

    // Run after three seconds after running main and Runnable run methods!
    System.out.println(ThreadColor.ANSI_CYAN +  "Three seconds have passed and I am awake! in another thread!");
  }
}

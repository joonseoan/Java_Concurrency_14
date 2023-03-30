public class AnotherThread2 extends Thread {
  @Override
  public void run() {
    // Run this first!
    System.out.println(ThreadColor.ANSI_CYAN + "Hello from " + currentThread().getName());

    try {
      Thread.sleep(3000);
      // [IMPORTANT]
      // Like join, `sleep` also can be terminated prematurely if it is interrupted by another method.
    } catch (InterruptedException err) {
      // [Interrupt]
      // Call this area when main call `anotherThread2.interrupt()`
      // It will call without 3 seconds.
      System.out.println(ThreadColor.ANSI_CYAN + "Another thread woke me up!");
      // for .interrupt method. Then, we would not get the `System.out.println` below
      return;
    }

    // [Without Interrupt]
    // Run after three seconds after running main and Runnable run methods!
    System.out.println(ThreadColor.ANSI_CYAN +  "Three seconds have passed and I am awake! in another thread2!");
  }
}

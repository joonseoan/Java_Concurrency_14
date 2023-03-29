public class MyRunnable implements Runnable {
  @Override
  public void run() {
    System.out.println(ThreadColor.ANSI_RED +  "Hello from MyRunnable's implements of run()");
  }
}

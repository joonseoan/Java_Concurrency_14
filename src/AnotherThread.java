
public class AnotherThread extends Thread {
  @Override
  public void run() {
    System.out.println(ThreadColor.ANSI_BLUE +  "Hello from another thread1");
  }

  public void additionalMethod() {
    System.out.println("Is this working?");
  }
}

package counter_2;

public class CountdownThread extends Thread {
  private CountDown threadCountdown;

  public CountdownThread(CountDown countdown) {
    threadCountdown = countdown;
  }

  @Override
  public void run() {
    threadCountdown.doCountdown();
  }
}

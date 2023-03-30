package counter_2;

public class CountDown {
  // [IMPORTANT]
  // Using field variable.
  // In this case, some of `i` cannot be not available in for loop.
  // Also, it is possible that some numbers are even duplicated.
  // even though we use the same CountDown instance.
  private int i;
  public void doCountdown() {
    String color;

    switch (Thread.currentThread().getName()) {
      case "Thread 1":
        color = ThreadColor.ANSI_CYAN;
        break;
      case "Thread 2":
        color = ThreadColor.ANSI_PURPLE;
        break;
      default:
        color = ThreadColor.ANSI_GREEN;
        break;
    }

    for (i = 10; i > 0; i--) {
      System.out.println(color + Thread.currentThread().getName() + ": i = " + i);
    }
  }
}

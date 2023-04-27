package messages_3;

public class RunMessages {
  public static void runMessages1() {
    Message message = new Message();
    /**
     * Problem is that once one of the threads starts looping `while(true)`
     * the other one can't change or update the value because that second thread is blocked
     *
     * Please remember the synchronized works at a time. So the thread which is looping
     * is holding the lock for the message object and the second one is blocked
     * waiting for the other thread to release.
     *
     * Now thread looping is called "deadlock".
     * This can be solved with wait and notify.
     * So when a thread calls the `wait` method,
     * it will suspend execution and release whatever locks `lock` (loop) is holding
     * until another thread issues a notification that something important has happened.
     * The other thread issues the notification by calling `notify` or `notifyAll` methods.
     *
     *
     */
    (new Thread(new Writer(message))).start();
    (new Thread(new Reader(message))).start();
  }
}

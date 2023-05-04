package concurrent_lib_4;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class MyProducer implements Runnable {
  private List<String> buffer;
  private String color;
  private ReentrantLock bufferLock;

  public MyProducer(List<String> buffer, String color, ReentrantLock bufferLock) {
    this.buffer = buffer;
    this.color = color;
    this.bufferLock = bufferLock;
  }

  @Override
  public void run() {
    Random random = new Random();
    String[] nums = { "1", "2", "3", "4", "5" };

    for (String num: nums) {
      try {
        // can be suspended because ArrayList is not synchronized.
        System.out.println(color + "Adding..." + num);

        // 2) Using Reentrant buffer lock.
        bufferLock.lock();
        buffer.add(num);
        // releasing the lock.
        // It is very important when we should release the lock.
        // The intrinsic locks we use with `synchronized` blocks
        // are always released when the thread holding the lock
        // exits the `synchronized` block. ----> This is a drawback
        // which means that we have to manage this lock process ourselves
        // by making sure we call the lock and the lock methods.

        // Now when a thread calls the lock, it will either obtain the lock and continue executing.
        // or it won't be able to get the lock because a thread already has it.
        // In that case, the thread can be suspended until it can get the lock.

        // Just in case, if we forget the release the lock, the thread is waiting for the lock
        // and then start blocking forever.
        bufferLock.unlock();

        // 1) By using synchronized
        // It can prevent asynchronized in ArrayList
//        synchronized (buffer) {
//          buffer.add(num);
//        }

        Thread.sleep(random.nextInt(1000));
      } catch (InterruptedException err) {
        System.out.println("Producer was interrupted.");
      }
    }

    System.out.println(color + "Adding EOF and exiting...");
    // 2)
    // By using Reentrant bufferLock
    bufferLock.lock();
    buffer.add("EOF");
    bufferLock.unlock();

    // 1)
    // It can prevent unsynchronized in ArrayList
//    synchronized (buffer) {
//      buffer.add("EOF");
//    }
  }
}

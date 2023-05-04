package concurrent_lib_4;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static concurrent_lib_4.RunConcurrent.EOF;

public class MyConsumer implements Runnable {
  private List<String> buffer;
  private String color;
  private ReentrantLock bufferLock;

  public MyConsumer(List<String> buffer, String color, ReentrantLock bufferLock) {
    this.buffer = buffer;
    this.color = color;
    this.bufferLock = bufferLock;
  }

  @Override
  public void run() {
    while(true) {
      // 2) By implementing reentrantLock (it is not recommended because it is messier than `synchronized`
      bufferLock.lock();
      if (buffer.isEmpty()) {
        bufferLock.unlock();
        continue;

      }
      if (buffer.get(0).equals(EOF)) {
        System.out.println(color + "Exiting");
        bufferLock.unlock();
        break;
      } else {
        System.out.println(color + "Removed " + buffer.remove(0));
      }
      bufferLock.unlock();

        // 1) By implementing `synchronized`
//      synchronized (buffer) {
//        // buffer can be suspended because ArrayList does not support asynchronization without `synchronized`.
//        // Therefore `buffer.isEmpty` can keep false!
//        if (buffer.isEmpty()) {
//          // System.out.println("buffer is empty now.");
//          // infinite loop because
//          continue;
//
//        }
//        // It should remove "EOF" string.
//        // If it did that, it will generate infinite loop.
//        if (buffer.get(0).equals(EOF)) {
//          System.out.println(color + "Exiting");
//          break;
//        } else {
//          System.out.println(color + "Removed " + buffer.remove(0));
//        }
//      }
    }
  }
}

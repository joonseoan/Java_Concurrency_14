package counter_2;

public class CountDown {
  /**
   * [IMPORTANT] Using field variable without `synchronized`.
   * In this case, some of `i` cannot be available in for loop.
   * Also, it is possible that some numbers are even duplicated.
   * even though we use the same CountDown instance.
   *
   * Why?
   * - Heap memory. The heap memory is for a `process` or `application`.
   *   The heap memory is the one that all threads share.
   *
   *   Also, every thread has a `thread stack` and that is a memory that
   *   each thread can access. In other words, thread can't access two `thread stacks`.
   *   But they both can access to the heap memory. The `local variables` are stored
   *   in the thread stack. That means that each thread has its own copy of
   *   local variable.
   *
   *   [IMPORTANT]!!!! A class instance uses a heap memory, not a stack memory.
   *   In contrast, the memory required to store an object instance value
   *   is allocated on the heap, not the stack. In other words, when multiple threads are working
   *   with the same object, *** they in fact share the same object or share that object ***. So they do
   *   not have their own copy as such and so when one thread changes the values of one of the objects
   *   instance variable, the other thread will see the new value from that point forward.
   */
  private int i;

  public void doCountdown() {
    // [IMPORTANT]
    // Required to specify `synchronized` on the method declaration like 'public synchronized void doCountdown() {}'
    // However, when we use it directly for for-loop
    // we need to do something differently because `String` is object to use heap memory.
    // Be sure that we print String in the `for loop`.
     String color;

    // Switch function is not affected by threads.
    // However, > < == .. is this affected???
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

    // ----------- [IMPORTANT] -------------
    // 3) Using Synchronization
    synchronized (this) {
      // when we use the field variable and when apply `synchronized` for the for loop
      for (i = 10; i > 0; i--) {
        System.out.println(color + Thread.currentThread().getName() + ": i = " + i);
      }
    }

    // 2) Using Field Variable without `synchronized` ---> [Interference]

    // By using instance variable in shared heap memory
    // When we are using instance variable, JVM allocated the space for `i`
    // to the shared heap memory. Then, in this case, the two threads in `main method`
    // don't have their own copy of the variable 'i'.

    // [Duplication]
    // The points the current thread can be suspended before the second thread comes in.
    // [IMPORTANT]
    // Possible suspended points: 1. before System.out.println, 2. before decrementing, 3. before condition
    // Therefore, the first thread can be suspended before decrementing, and then
    // the second thread comes in (when 'i' value is still 10.) --> generates the duplicated 10
    // at the beginning of the loop process/application.

    // [Thread interference]
    // 1. The first thread works with 'i',
    // 2. and then the execution of the first thread will be suspended (because of the end of method)
    // 3. The second thread starts working for the updated 'i' from the first thread.
    // 4. Therefore, it does skip the previous value that was run by the other thread.

    // [IMPORTANT] There would not be a problem if both threads were only reading the shared resource
    // like the instance fields, but the problem will only see when at least one of the threads
    // is writing or updating that resource.

    // Sometimes the specific possible suspension point does not work.
    // Over here, the potential suspense point within the 'print statement itself'.
    // Because Thread 2 finishes printing 4, and then it is suspended. After that Thread 1
    // executes and then decrementing 'i', and then suspended before printing. In the meantime,
    // Thread 2 executes, decremented 'i', and printed the number. After this, Thread 1 executes printing.
    // that is what's happening here. (4, 2, 3, 1) --> out of order.

    // for (i = 10; i > 0; i--) {
    //   System.out.println(color + Thread.currentThread().getName() + ": i = " + i);
    // }

    // 1) Using local variable

    // [IMPORTANT]!!!! FOR LOCAL VARIABLE!
    // [Synchronization] with local variable
    // 1) We can add `synchronized` keyword in method name
    //  public synchronized void doCountdown() {

    // By using local variable in stack memory - no strange
    // because the JVM allocated 'j' space for the local variable.
    // It uses each `thread stack`.

    // [IMPORTANT] j = 10: thread (stack), j--: thread (stack), System.out.print: thread (stack)
    // for (int j = 10; j > 0; j--) {
    //    System.out.println(color + Thread.currentThread().getName() + ": j = " + j);
    // }
  }
}

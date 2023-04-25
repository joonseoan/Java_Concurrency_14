package counter_2;

public class RunCounter {

  // [Interfering] with instance field
  public static void runCounter() {
    CountDown countDown = new CountDown();

    // We passed the same `countDown` object to two threads.
    Thread t1 = new CountdownThread(countDown);
    t1.setName("Thread 1");
    Thread t2 = new CountdownThread(countDown);
    t2.setName("Thread 2");

    // [IMPORTANT]
    // (2) For testing field variable.

    // [IMPORTANT]
    // (1) For testing local variable
    // BTW, the local variable would not have interfering.
    // And, also, each thread in for loop is in disorder.

    /*
    * For thread vs thread, we can use `synchronized` in the method that wrapped by run() method.,
    * For main vs thread, we can use `thread.isAlive()`
    * */

    t1.start();
    t2.start();
  }

  // Racing - Synchronization
  public static void runRacing() {
    // [Synchronization] Racing

    // We can use "SYNCHRONIZATION" to control shared heap memory (instance field).
    // We can synchronize methods and statements.
    // When we can synchronize methods so that only one thread can execute that at a time.
    // So when a thread is executing the method, or other threads that we want to call,
    // or any other synchronized method in that class will suspend until the thread running the method exits.
    // It will always have the same result.

    // Adding 'synchronized' keyword to the method.

    // [IMPORTANT]
    // (FYI, constructor must not have this keyword)

    /*
      [IMPORTANT!!!!] Lock
      As shown in this method, we can synchronize static methods and use static objects.
      Now when we do that, the `lock` that is used runnable or named thread class.

      Now synchronization is re-entrant.

      public ConcurrencyLockExample(Resource r){
		    this.resource = r;
		    // [IMPORTANT]: Synchronization is reenterant.
		    this.lock = new ReentrantLock();
	    }

      What that means is that
      if a thread acquires an object's lock and, within the synchronized code, it calls a method
      that using the same object to synchronize same code. The thread can keep executing
      because it already has the object's `lock`.

      In other words, a thread can acquire a lock it already owns. Now if this wasn't the case,
      synchronization would be a lot trickier.

      [Critical Section]
      Now, we'll sometimes see the term `critical section` used
      when discussing threads and synchronization, `critical section` just refers to the code
      that's referencing a shared resource like a field variable.
      Only one thread at a time should be able to execute a *** critical section ****.

      [Thread Safe]
      Now, we'll also see the term `thread safe` used. When a class or a method is thread safe,
      what that means is that the developer has synchronized all the `critical sections` within the code
      so that we as a developer don't have to worry about the thread interference. *** So if we're using
      a method or class that isn't `thread safe`, then the developer hasn't added any synchronization. ***
      In that case, we'd be responsible for adding synchronization if we want multiple threads
      to be able to safely use the code.

      [IMPORTANT] Rule of synchronization
      When we're synchronizing code,we should synchronize only the code that must be synchronized.
      So in our example, *** only the for loop has to be synchronized so that's why we put that
      inside the synchronized block ***. I demonstrated how to synchronize the doCountdown method,
      but doing it that way would be synchronizing too much code. We also don't want to synchronize
      the setting of the color variable because frankly there's no reason to do so.
      We don't want threads to be suspended or blocked unnecessarily because that can affect
      the performance of an application, as well as the user experience. So in other words,
      we *** really want to keep the code we synchronize to *** an absolute minimum***. So at this point,
      we've now talked about and learned that we can prevent thread interference or a race condition
      by synchronizing critical sections of code.

      Let's go ahead now and look at methods that can only be called within synchronized code,
      namely the weight, notify, and notify all methods. The classic example that demonstrates the use
      of these two methods is the `producer` and `consumer` example.



    * */

    CountDown cd = new CountDown();

    CountdownThread cdt1 = new CountdownThread(cd);
    cdt1.setName("Thread 1");
    CountdownThread cdt2 = new CountdownThread(cd);
    cdt2.setName("Thread 2");

    cdt1.start();
    cdt2.start();

  }
}

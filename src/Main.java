/**
 * A `process` is a unit of execution that has its own memory space.
 * Each instance of a Java Virtual Machine (JVM) runs as a process (This is not true
 * for all JVM implementations, but for most of them). When we run a Java console application,
 * we are kicking off a `process`. When we run a JavaFX, we are kicking off a 'process'.
 * <p>
 * The term of `process` and `application` can be used interchangeably.
 * If one Java application is running, and we run another one, each application
 * has its own memory space of `heap`. The first Java application can't
 * access the heap that belongs to the second Java application. In other words,
 * the heap is not shared between them. They each have their own.
 * <p>
 * A `Thread` is a unit of execution within a process. Each process can have multiple `threads`.
 * In Java, every `process` (or `application`) has at least one thread, the `main thread`
 * (for UI applications, this is called the JavaFx application thread). In fact, just about every
 * Java process also has multiple system threads that handle tasks like memory management and I/O.
 * The developers don't explicitly create and code those threads. Our code runs on the `main thread,`
 * or in `other threads` that we explicitly create.
 * <p>
 * Creating a thread does not require as many resources as creating a process. Every thread
 * created by a process shares the process's memory and files. The can create problems!!!!!
 * <p>
 * In addition to the process's memory, or heap, each thread has what's called a `thread stack`,
 * which is the memory that only that thread can access.
 * <p>
 * So every Java application runs as a single `process`, and each process can have multiple `threads`.
 * Every process has a `heap`, and every thread has a `thread stack`.
 * <p>
 * Why would wen want to use multiple threads in out application? Why not just stick with the main thread?
 * There are two main reasons.
 * <p>
 * First of all, we sometimes want to perform a task that is going to take a long time.
 * For example, we might want to query a database, or we might want to fetch data from somewhere
 * on the internet. We could do this on the main thread, but the code within each main thread executes
 * in a linear fashion. The main thread won't be able to do anything else while it is waiting for the data.
 * <p>
 * Another way of putting this is that the execution of the main thread will be suspended. It has to wait
 * for the data to be returned before it can execute the next line of code. To the user, this could appear
 * as if our application died or is frozen, especially when we are dealing with a UI application.
 * <p>
 * Instead of tying up the main thread, we can create another thread and execute the long-running task
 * on that thread. This would free up the main thread, so that it can continue executing. It can report
 * progress or accept user input while the long-running task continues to execute in the background.
 * <p>
 * The second reason we might want to use threads is because an API requires us to provide one. Sometimes
 * we have to provide the code that will run when a method we have called reaches a certain point in it's
 * execution. In this instance, we usually do not crate the thread. We pass in the code that we want to
 * run on the thread.
 * <p>
 * [IMPORTANT]
 * That brings us to `concurrency`, which refers to an application doing more than one thing at a time.
 * Now, that does not necessarily mean that the application is doing more than one thing at the same time.
 * It means that progress can be made on more than one task. Let's say that an application wants to download
 * data and draw a shape on the screen.
 * <p>
 * If it is a concurrent application, it can download a bit of data, then switch to drawing part of the shape,
 * then switch back to downloading some more data, then switch back to drawing more of the shape, etc.
 * Concurrency means that one task does not have to complete before another can start. Java provides
 * thread-related classes so that we can create Java concurrent applications.
 * <p>
 * Before we do, we have to understand that when working with threads, we are at the mercy of the JVM
 * and the operating system when it comes to when threads are scheduled to run
 * <p>
 *
 * [IMPORTANT]
 * Thread terminates when it returns from its `run` method either implicitly because it reaches the
 * method's end or explicitly because it executes a return statement. Now a common mistake when creating
 * and running threads is to call this thread instance `run` method instead of the *** `start`*** method.
 * JVM calls `run` method but we do not call `run` method.
 *
 */

public class Main {
  public static void main(String[] args) {
    // single process in a heap memory
    // There is a main thread in `System`
    System.out.println(ThreadColor.ANSI_PURPLE + "Hello from the 'main thread'.");

    // 1. [subclass]
    // [Kick off a thread that is going to run some code]
    // So we need a way to tell the thread what code want to run.
    // *** We are going to do that by creating a subclass of the thread class and then
    // overriding the `run` method. So rather than creating a thread instance,
    // we are going to create an instance of our subclass by extending `Thread` interface.
    Thread anotherThread1 = new AnotherThread();
    // we will use `start` method. What it does is enable JVM to run the `run` method for the thread.
    anotherThread1.start();

    // using `setName` to deliver the parameter to the `run` method.
    Thread anotherThread2 = new AnotherThread2();
    anotherThread2.setName("==== Another Thread2 ====");

    // [IMPORTANT]
    // if we use `anotherThread2.run()`, it won't generate an error but
    // it will bring up the strange result.
    // anotherThread2.run(); // it show "Hello from main method!!!"

    anotherThread2.start();

    // 2. [subclass - using anonymous class.]
    // when using anonymous class, we have to start the thread immediately,
    // so that another consideration when deciding whether to use a named or an anonymous class.
    // Of course, the name class is the one that we already defined above, for example.
    // In result, it runs after the another thread, not in the `first thread`
    // Once again, the Thread does not generate the same order with main thread.!
    new Thread() {
      public void run() {
        System.out.println(ThreadColor.ANSI_GREEN + "Hello from the anonymous class thread.");
      }
    }.start();

    /**
     * When using subclass and when using Runnable Interface?
     * The most of time developers use the Runnable way of doing things.
     * The reason is that it is more convenient and there is also many methods
     * in the Java api that want a Runnable interface passed to them.
     *
     * For example since the introduction of Lambda expressions, it becomes more convenient
     * to use anonymous runnable instances. So when we have a choice because we are not calling a method
     * that requires one or the other, there is not really a right or wrong answer.
     * But the most of the developer use Runnable interface because it is more flexible.
     */

    // Thread terminates when it returns from its run method.

    // 3. [Runnable Interface]
    // It is similar to the way of creating threads, we need to implement `run` method.
    // However, instead of implementing the run method of a class that subclass thread,
    // We can have any class implement the runnable interface and then
    // all we have to do is to add a run method to that class.
    // Of course, we will then want to write the code in the run mehtod.
    Thread myRunnableClass = new Thread(new MyRunnable());
    // need to use start method as well.
    myRunnableClass.start();

    // 4. [Runnable Interface - using anonymous class]
    Thread myRunnableAnonymousClass = new Thread(
            new MyRunnable() {
              @Override
              public void run() {
                System.out.println(ThreadColor.ANSI_RED + "Hello from the anonymous class's implementation of run()");
              }
            }
    );
    myRunnableAnonymousClass.start();

    // Now this runs in different order. It can run at the second
    // or third after Thread subclass works.
    // It means that we can't guarantee that it runs on the consistent order.
    System.out.println(ThreadColor.ANSI_PURPLE + "Hello again from the 'main thread'!!!");

    // [IMPORTANT]
    // It will generate IllegalThreadStateException.
    // Using a subclass of Thread means that we have to define 'the run method once'
    // but we cannot reuse the same instance!!!!
    // BTW, we can create another instance and can call it (It is ok.)
    //    anotherThread.start();

  }
}
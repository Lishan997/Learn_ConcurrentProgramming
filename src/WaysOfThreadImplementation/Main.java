package WaysOfThreadImplementation;

public class Main {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        Thread thread1 = new MyThread("T1");
        System.out.println("Thread 01 state after initilise" + thread1.getState());
        thread1.setPriority(10);
        thread1.start();
        System.out.println("Thread 01 state after start " + thread1.getState());
        System.out.println("Thread 01 Priority " + thread1.getPriority());

        Thread thread2 = new MyThread("T2");
        thread2.setDaemon(true); // make thread as a daemon thread
        thread2.start();
        System.out.println("Thread 02 Priority " + thread2.getPriority());

        Thread thread3 = new MyThread("T3");
        thread3.start();

        /**
         * why here we cant directly execute rt.start()
         * */
        Runnable rt = new RunnableThread("RunnableThread 01");
        Thread thread4 = new Thread(rt, ((RunnableThread)rt).getThreadName());
        thread4.start();
    }
}

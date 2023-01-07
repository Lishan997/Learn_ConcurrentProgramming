package JoinMethods;

public class MainApp {
    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName() + " thread state is : " + Thread.currentThread().getState());
        System.out.println(Thread.currentThread().getName() + " thread is live ?  : " + Thread.currentThread().isAlive());

        Runnable r1 = new MyThread();
        Thread t1 = new Thread(r1, "Thread 01");

        System.out.println("Thread 01" + " state is : " + t1.getState());
        System.out.println("Thread 01" + " is live ?  : " + t1.isAlive());

        t1.start();

        System.out.println("Thread 01" + " state is : " + t1.getState());
        System.out.println("Thread 01" + " is live ?  : " + t1.isAlive());
        try {
            //t1.join();
            // who calls the join() on t1? the main thread, there for the main thread give up processor time and goes in to WAITING and let the thread which join is called allowed to execute until its complete the execution,
            // in our case it is the t1, once the execution is over the main thread is join back.
            t1.join(500);

            System.out.println(Thread.currentThread().getName() + " thread state is : " + Thread.currentThread().getState());
            System.out.println(Thread.currentThread().getName() + " thread is live ?  : " + Thread.currentThread().isAlive());

            System.out.println("Thread 01" + " state is : " + t1.getState());
            System.out.println("Thread 01" + " is live ?  : " + t1.isAlive());
            //when we use t1.join(t) Main thread is in timed waiting state to complete t1 task or time expire, what ever happened first

//            t1.join(500);
//            Thread 01 : 0
//            Thread 01 : 1
//            Thread 01 : 2
//            Thread 01 : 3
//            Thread 01 : 4
//            main : 0
//            Thread 01 : 5
//            main : 1
//            Thread 01 : 6
//            main : 2
//            Thread 01 : 7
//            main : 3
//            Thread 01 : 8
//            main : 4
//            Thread 01 : 9
//            main : 5
//            main : 6
//            main : 7
//            main : 8
//            main : 9

            // when we use t1.join(300) Thread 01, execute 03 times and after that Main thread execute like above
            // when we use t1.join(3000), Thread 01 completed the task in 1000ms and main thread come and get execute here Main thread execute because t1 thread completed the task not the expired the time

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //there are two threads here Main thread and t1 thread
        //Main thread is the thread that creating t1 thread

        for (int i = 0; i < 10; i++){
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + " thread state is : " + Thread.currentThread().getState());
        System.out.println(Thread.currentThread().getName() + " thread is live ?  : " + Thread.currentThread().isAlive());

        System.out.println("Thread 01" + " state is : " + t1.getState());
        System.out.println("Thread 01" + " is live ?  : " + t1.isAlive());
    }
}

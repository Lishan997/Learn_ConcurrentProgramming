package WaysOfThreadImplementation;

/**
 * we should never make any assumption order of the execution time at which execution of a thread
 */

public class MyThread extends Thread{

    public MyThread(String threadName){
        super(threadName);
    }

    public void run(){
        for (int i = 0; i < 10; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

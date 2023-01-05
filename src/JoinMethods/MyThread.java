package JoinMethods;

public class MyThread implements Runnable{
    @Override
    public void run(){
        for(int i = 0; i < 10; i++){
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(100); //only currently executing thread will be sleeping.
            } catch (InterruptedException e) {
                e.printStackTrace(); // something can interrupt thread sleep, if interrupted then this thread again go to the runnable state
            }
        }
    }
}

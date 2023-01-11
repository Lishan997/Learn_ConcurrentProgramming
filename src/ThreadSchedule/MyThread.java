package ThreadSchedule;

public class MyThread implements Runnable{

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + " priority " + Thread.currentThread().getPriority());

        Thread.currentThread().setPriority(10);
        System.out.println(Thread.currentThread().getName() + " priority " + Thread.currentThread().getPriority());

        Thread thread02 = new Thread("Thread-02");
        thread02.start();
        System.out.println(thread02.getName() + " priority " + thread02.getPriority());

        for (int i =0; i <10; i++){
            System.out.println(Thread.currentThread().getName() + " priority " + Thread.currentThread().getPriority() + " : " + i);
        }
    }
}

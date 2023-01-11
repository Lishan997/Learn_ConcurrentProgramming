package ThreadSchedule;

public class Main {

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " " + " priority " + thread.getPriority());

        thread.setPriority(3);
        System.out.println(thread.getName() + " " + " priority " + thread.getPriority());

        Thread myThread = new Thread(new MyThread(), "Thread 01");
        myThread.start();

        //once a thread is created we can change it priority at anytime
        //if we set thread1 priority it is inherited to thread2 because , thread2 is created by thread1
        //thread inherit it is priority from the thread it is created it (parent thread)
        //priority influence a thread scheduling
        //thread priority is not gurenty higest priority thread is going to execute first allways
    }
}

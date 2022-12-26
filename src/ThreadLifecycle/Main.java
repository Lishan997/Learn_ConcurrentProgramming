package ThreadLifecycle;

public class Main {
    public static void main(String[] args) {

        SimpleMailBox mailBox = new SimpleMailBox(0);
        Producer p = new Producer("P1", mailBox);
        Consumer c = new Consumer("C1", mailBox);

        //when we instantiate a thread it enters the NEW state
        Thread producerThread = new Thread(p, p.getThreadName());
        Thread consumerThread = new Thread(c, c.getThreadName());
        //when a thread is in NEW state can invoke the start() method which will move the thread from NEW state to RUNNABLE state

        producerThread.start();
        consumerThread.start();

        //check state and liveliness
        System.out.println("State of producer thread : " + producerThread.getState());
        System.out.println("liveliness of producer thread : " + producerThread.isAlive());

    }
}

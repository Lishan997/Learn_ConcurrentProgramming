package MonitorConcepts;

public class SimpleMailBox<E> implements MailBox<E> {

    private E content; //share variable can hold only one value at any given time
    private boolean available = false;

    @Override
    public synchronized void put(E value) {
        while(available){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.content = value;
        System.out.println(Thread.currentThread().getName() + " produced " + value);
        available = true;
        notifyAll(); //will notify all the consumer in the wait set - set of thread are waiting on the lock
    }

    @Override
    public synchronized E get() {

        while (!available){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        available = false;
        System.out.println(Thread.currentThread().getName() + " get " + this.content);
        notifyAll();
        return this.content;
    }

    //Monitor is a passive object
    //Monitor is used by thread , it is not going to anything its own
}

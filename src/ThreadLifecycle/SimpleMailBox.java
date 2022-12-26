package ThreadLifecycle;

public class SimpleMailBox {

    private int data;
    private boolean available = false;

    public SimpleMailBox(int data) {
        this.data = data;
    }

    public synchronized int getData() { //consumer call this method
        while (!available){
            try {
                wait(); //IllegalMonitorStateException is called because the wait() method can be called only in the synchronized block
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.available = false;
        notifyAll();
        return data;
    }

    public synchronized void setData(int data) { //producer call this method
        //is meaning of available that we can consume ?
        while (available){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.data = data;
        this.available = true;
        notifyAll(); //Notify all the thread which are waiting (inform consumers come and consume)
    }
}

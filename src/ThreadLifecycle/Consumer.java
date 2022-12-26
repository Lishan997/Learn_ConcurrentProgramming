package ThreadLifecycle;

public class Consumer implements Runnable{
    private String threadName;
    private SimpleMailBox mailBox;

    public Consumer(String threadName, SimpleMailBox mailBox) {
        this.threadName = threadName;
        this.mailBox = mailBox;
    }

    public String getThreadName() {
        return threadName;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++){
            int data = mailBox.getData();
            System.out.println(Thread.currentThread().getName()+" get the data " + data);
        }
    }
}

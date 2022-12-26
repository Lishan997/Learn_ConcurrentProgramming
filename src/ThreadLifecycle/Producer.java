package ThreadLifecycle;

public class Producer implements Runnable{

    private String threadName;
    private SimpleMailBox mailBox;

    public Producer(String threadName, SimpleMailBox mailBox){
        this.threadName = threadName;
        this.mailBox = mailBox;
    }

    public String getThreadName() {
        return threadName;
    }

    @Override
    public void run(){
        for (int i = 0; i < 10; i++){
            mailBox.setData(i);
            System.out.println(Thread.currentThread().getName()+" set data " + i);
        }
    }
}

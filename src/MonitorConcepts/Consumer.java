package MonitorConcepts;

public class Consumer implements Runnable{
    private String name;
    private MailBox<Integer> mailBox;
    private final int NO_OF_ITEMS;

    public Consumer(String name, MailBox<Integer> mailBox, int nuOfItem) {
        this.name = name;
        this.mailBox = mailBox;
        this.NO_OF_ITEMS = nuOfItem;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run(){
        for (int i = 0; i < NO_OF_ITEMS; i++){
            int value = mailBox.get();
//            System.out.println(Thread.currentThread().getName() + " get " + value);
        }
    }
}

package MonitorConcepts;

public class Main {
    public static void main(String[] args) {
        MailBox<Integer> simpleMailBox = new SimpleMailBox<>();
        final int nuOfItems = 20;

        Producer producer = new Producer("Producer", simpleMailBox, nuOfItems);
        Consumer consumer = new Consumer("Consumer", simpleMailBox, nuOfItems);

        Thread producerThread = new Thread(producer, producer.getName());
        Thread consumerThread = new Thread(consumer, consumer.getName());

        producerThread.start();;
        consumerThread.start();
    }
}

package MonitorConcepts;

public interface MailBox<E> {

    public void put(E value);
    public E get();
}

package MonitorConcepts;

import java.util.LinkedList;
import java.util.Queue;

public class MultiSlotMailBox<E> implements MailBox<E> {

    private Queue<E> queue = new LinkedList<E>();
    private final int NO_OF_ITEMS = 10;

    @Override
    public void put(E value) {

    }

    @Override
    public E get() {
        return null;
    }
}

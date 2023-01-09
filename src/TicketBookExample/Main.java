package TicketBookExample;

public class Main {

    public static void main(String[] args) {

        Bus bus = new Bus("Monaragala-To-Colombo");

        User user1 = new User("Mithila", 2, bus);
        User user2 = new User("Venura", 2, bus);

        Thread user1Thread = new Thread(user1);
        Thread user2Thread = new Thread(user2);

        user1Thread.start();
        user2Thread.start();
    }
}

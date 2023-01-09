package TicketBookExample;

public class User implements Runnable{

    private String name;
    private int numberOfSeatsWants;
    Bus bus;

    public User(String name, int numberOfSeatsWants, Bus bus) {
        this.name = name;
        this.numberOfSeatsWants = numberOfSeatsWants;
        this.bus = bus;
    }

    @Override
    public void run(){
        bus.bookTicket(this.name, this.numberOfSeatsWants);
    }

    public String getName() {
        return name;
    }
}

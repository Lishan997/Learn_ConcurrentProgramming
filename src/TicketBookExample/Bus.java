package TicketBookExample;

public class Bus {

    private String busName;
    private int numberOfSeatAvailable;

    public Bus(String busName) {
        this.busName = busName;
        this.numberOfSeatAvailable = 3;
    }

    public synchronized void bookTicket(String passengerName, int numberOfSeatWants){
        if ((numberOfSeatAvailable >= numberOfSeatWants) && (numberOfSeatWants > 0)) {
            System.out.println("Hi," + passengerName + " : " + numberOfSeatWants+ " Seats booked Successfully to " + busName);
            numberOfSeatAvailable = numberOfSeatAvailable- numberOfSeatWants;
        } else
            System.out.println("Hi," + passengerName + " : Seats Not Available to" + busName);
    }

}

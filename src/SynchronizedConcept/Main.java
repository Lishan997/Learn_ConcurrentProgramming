package SynchronizedConcept;

public class Main {
    public static void main(String[] args) {
        //critical section example
        BankAccount sharedAccount = new BankAccount(20000, "CMF+HBH");

        CareerMindedWife cmw = new CareerMindedWife(sharedAccount);
        HouseBasedHusband hbh = new HouseBasedHusband(sharedAccount);

        Thread cmwThread = new Thread(cmw, cmw.getName());
        Thread hbhThread = new Thread(hbh, hbh.getName());

        cmwThread.start();
        hbhThread.start();
    }
}

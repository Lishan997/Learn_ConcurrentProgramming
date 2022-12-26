package SynchronizedConcept;

public class HouseBasedHusband implements Runnable{

    private String name;
    private BankAccount bankAccount;

    public HouseBasedHusband(BankAccount bankAccount) {
        this.name = "House Based Husband";
        this.bankAccount = bankAccount;
    }

    public String getName() {
        return name;
    }

    public void run(){
        for (int i = 0; i < 10; i++){
            double balance = bankAccount.withdraw(20000);
            System.out.println(Thread.currentThread().getName() + " the balance after withdraw " + balance);
        }
    }
}


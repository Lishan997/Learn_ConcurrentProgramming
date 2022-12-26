package SynchronizedConcept;

public class CareerMindedWife implements Runnable {

    private String name;
    private BankAccount bankAccount;

    public CareerMindedWife(BankAccount bankAccount) {
        this.name = "Career Minded Wife";
        this.bankAccount = bankAccount;
    }

    public String getName() {
        return name;
    }

    public void run(){
        for (int i = 0; i < 10; i++){
            double balance = bankAccount.deposit(20000);
            System.out.println(Thread.currentThread().getName() + " the balance after deposit " + balance);
        }
    }
}

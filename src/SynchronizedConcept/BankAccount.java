package SynchronizedConcept;

public class BankAccount {

    //this is the shared resource , accessing balance is critical section, therefor access to balance must be mutual exclusive
    private double balance;
    private String name;

    public BankAccount(double balance, String name) {
        super();
        this.balance = balance;
        this.name = name;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public synchronized double deposit(double depositAmount){
        balance += depositAmount;
        return balance;
    }

    public synchronized double withdraw(double withdrawAmount){
        if(balance >= withdrawAmount){
            balance -= withdrawAmount;
        }else{
            System.out.println("Insufficient Account Balance");
        }

        return balance;
    }
}

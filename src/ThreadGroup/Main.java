package ThreadGroup;

public class Main {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getThreadGroup().getName());

        Thread th1 = new Thread("TH-01");
        System.out.println(th1.getName() + " : " + th1.getThreadGroup().getName());

        ThreadGroup tg01 = new ThreadGroup("TG01");
        System.out.println(tg01.getName() + " parent thread is : " + tg01.getParent().getName());

        ThreadGroup tg02 = new ThreadGroup(tg01, "TG02");
        System.out.println(tg02.getName() + " parent thread is : " + tg02.getParent().getName());

        Thread th2 = new Thread(tg01, "TH-02");
    }
}

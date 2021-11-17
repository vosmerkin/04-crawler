package test.racecondition;

public class Incrementer implements Runnable {

    private final Counter counter;
    private final int amount;

    public Incrementer(Counter counter, int amount) {
        this.counter = counter;
        this.amount = amount;
    }

    @Override
    public void run() {
        System.out.println("Thread inc=" + Thread.currentThread().getName());
        for (int i = 0; i < amount; i++) {
            counter.increment();
        }
    }
}

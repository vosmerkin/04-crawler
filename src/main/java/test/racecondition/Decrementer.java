package test.racecondition;

public class Decrementer implements Runnable {

    private final Counter counter;
    private final int amount;

    public Decrementer(Counter counter, int amount) {
        this.counter = counter;
        this.amount = amount;
    }

    @Override
    public void run() {
        System.out.println("Thread dec=" + Thread.currentThread().getName());
        for (int i = 0; i < amount; i++) {
            counter.decrement();
        }
    }


}

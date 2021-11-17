package test.racecondition;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private final AtomicInteger counter = new AtomicInteger(0);

    public int getCounter() {
        return counter.get();
    }

    public void increment() {
        counter.incrementAndGet();
    }

    public void decrement() {
        counter.decrementAndGet();
    }
}

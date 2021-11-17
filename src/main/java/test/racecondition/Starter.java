package test.racecondition;

/**
 * TODO: remove static methods in crawler classes,
 * read about Atomic classes and Lock's (avoid synchronize)
 * read about unit testing - https://www.vogella.com/tutorials/JUnit/article.html
 */
public class Starter {

    public static final int AMOUNT = 10_000;

    public static void main(String[] args) throws Exception {
        var counter = new Counter();
        var incrementer01 = new Thread(new Incrementer(counter, AMOUNT));
        var incrementer02 = new Thread(new Decrementer(counter, AMOUNT));

        System.out.println("Thread =" + Thread.currentThread().getName());
        incrementer01.start();
        incrementer02.start();

        incrementer01.join();
        incrementer02.join();

        System.out.println("Result =" + counter.getCounter());
    }
}

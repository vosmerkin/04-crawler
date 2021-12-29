package crawler;

public interface Db <T> {
    void add(T element) throws InterruptedException;

    T getNext() throws InterruptedException;

}

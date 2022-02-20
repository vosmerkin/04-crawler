package crawler.queue;

public interface Queue<T> {
    boolean addElement(T element) throws InterruptedException;

    T getNextElement() throws InterruptedException;

}

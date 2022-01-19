package crawler.queue;

public interface Queue<T> {
    void addElement(T element) throws InterruptedException;

    T getNextElement() throws InterruptedException;

}

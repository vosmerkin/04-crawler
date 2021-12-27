package crawler;

public interface Db <T> {
    public boolean hasElementsToProceed();
    public void add(T element) throws InterruptedException;
    public T getNext() throws InterruptedException;

}

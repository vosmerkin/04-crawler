package crawler.queue;

import crawler.Params;
import org.jsoup.nodes.Document;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class AnalyzeQueue implements Queue<Document> {
    public final BlockingQueue<Document> analyzeQueue = new ArrayBlockingQueue<>(Params.PAGE_QUEUE_SIZE);

    @Override
    public void addElement(Document element) throws InterruptedException {
        analyzeQueue.offer(element, Params.TIMEOUT_IF_ANALYZEDB_IS_FULL, TimeUnit.SECONDS);
    }

    @Override
    public Document getNextElement() throws InterruptedException {
        return analyzeQueue.poll(Params.TIMEOUT_IF_QUEUE_IS_EMPTY, TimeUnit.SECONDS);
    }
}
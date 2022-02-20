package crawler.queue;

import crawler.Params;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class AnalyzeQueue implements Queue<Document> {
    private static final Logger log = LoggerFactory.getLogger(AnalyzeQueue.class);
    public final BlockingQueue<Document> analyzeQueue = new ArrayBlockingQueue<>(Params.PAGE_QUEUE_SIZE);

    @Override
    public boolean addElement(Document element) throws InterruptedException {
        log.debug("AnalyzeQueue addElement {}", Thread.currentThread().getName());
        if (null == element) return false;
        return analyzeQueue.offer(element, Params.TIMEOUT_IF_ANALYZEDB_IS_FULL, TimeUnit.SECONDS);
    }

    @Override
    public Document getNextElement() throws InterruptedException {
        log.debug("AnalyzeQueue getNextElement {}", Thread.currentThread().getName());
        return analyzeQueue.poll(Params.TIMEOUT_IF_QUEUE_IS_EMPTY, TimeUnit.SECONDS);
    }
}
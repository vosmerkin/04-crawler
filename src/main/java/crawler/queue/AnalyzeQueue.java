package crawler.queue;

import crawler.Params;
import org.jsoup.nodes.Document;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class AnalyzeQueue implements Queue<Document> {

    public  final ArrayBlockingQueue<Document> analyzeQueue = new ArrayBlockingQueue<>(Params.PAGE_QUEUE_SIZE);

    @Override
    public void addElement(Document element) throws InterruptedException {

//        System.out.print("ID " + Thread.currentThread().getId() + " AnalyzeList adding page " + element.baseUri());
//        System.out.println("  size before " + queue.size());
        analyzeQueue.offer(element, Params.TIMEOUT_IF_ANALYZEDB_IS_FULL, TimeUnit.SECONDS);
//        System.out.println("AnalyzeList size after " + queue.size());

    }

    @Override
    public Document getNextElement() throws InterruptedException {
        return analyzeQueue.poll(Params.TIMEOUT_IF_QUEUE_IS_EMPTY,TimeUnit.SECONDS );
    }
}

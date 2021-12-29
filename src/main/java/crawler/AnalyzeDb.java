package crawler;

import org.jsoup.nodes.Document;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class AnalyzeDb implements Db <Document> {
//    private  final Set<Document> list = new ConcurrentSkipListSet<>();
    public  final ArrayBlockingQueue<Document> queue = new ArrayBlockingQueue<>(params.PAGE_QUEUE_SIZE);

    @Override
    public void add(Document element) throws InterruptedException {

//        System.out.print("ID " + Thread.currentThread().getId() + " AnalyzeList adding page " + element.baseUri());
//        System.out.println("  size before " + queue.size());
        queue.offer(element, 10, TimeUnit.SECONDS);
//        System.out.println("AnalyzeList size after " + queue.size());
//        notifyAll();
    }

    @Override
    public Document getNext() throws InterruptedException {



        return queue.poll(params.WAIT_FOR_ELEMENT_TO_ADD,TimeUnit.SECONDS );

    }
}

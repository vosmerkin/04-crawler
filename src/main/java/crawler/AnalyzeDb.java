package crawler;

import org.jsoup.nodes.Document;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.TimeUnit;

public class AnalyzeDb implements Db <Document> {
    private  final Set<Document> list = new ConcurrentSkipListSet<>();
    public  final ArrayBlockingQueue<Document> queue = new ArrayBlockingQueue<>(params.PAGE_QUEUE_SIZE);


    @Override
    public boolean hasElementsToProceed() {
//        System.out.print("AnalyzeList isEmpty = " + list.isEmpty());
//        System.out.println(" AnalyzeList size " + list.size());
        return !queue.isEmpty();

    }

    @Override
    public void add(Document element) throws InterruptedException {
        System.out.print("AnalyzeList adding page " + element.baseUri());
        System.out.println("  size before " + queue.size());
        queue.offer(element,10, TimeUnit.SECONDS );
        System.out.println("AnalyzeList size after " + queue.size());
//        notifyAll();
    }

    @Override
    public Document getNext() throws InterruptedException {



        return queue.poll(20,TimeUnit.SECONDS );
//
//        Document returnValue = null;
//
//        while (list.isEmpty()) {
//            try {
//                System.out.println("AnalyzeList size " + list.size() + ". Waiting");
//                wait();
//            } catch (InterruptedException e) {
//            }
//        }
//
//        if (!list.isEmpty()) {
//            for (Document doc : list) {
//                returnValue = doc.clone();
//                if (!(null == returnValue)) {
//                    list.remove(doc);
//                    break;
//                }
//            }
//        }
//        System.out.println("AnalyzeList size" + list.size());
//
//        return returnValue;
    }
}

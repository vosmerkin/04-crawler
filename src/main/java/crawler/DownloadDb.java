package crawler;

import org.jsoup.nodes.Document;


import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

public class DownloadDb implements Db <String> {


    private final Set<String> list = new ConcurrentSkipListSet<>();
    private final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(params.URL_QUEUE_SIZE);


    public boolean hasElementsToProceed() {
//        System.out.print("UrlList isEmpty = " + list.isEmpty());
//        System.out.println(" UrlList size " + list.size());
        return !queue.isEmpty();
    }




@Override
    public void add(String element) throws InterruptedException {
        if (!list.contains(element)) {
            System.out.print("UrlList adding url " + element);
            System.out.println("  size before " + list.size()+" "+ queue.size());
            list.add(element);
//            queue.put(element);
            queue.offer(element,10,TimeUnit.SECONDS );
            System.out.println("UrlList size after " + list.size()+" "+ queue.size());
        }
//        notifyAll();
    }

    public  String getNext() throws InterruptedException {

        String returnValue = "";
//        System.out.println("UrlList size" + list.size());
        return queue.poll(10,TimeUnit.SECONDS );
//
//        while (list.isEmpty()) {
//            try {
//                System.out.println("UrlList size" + list.size() + ". Waiting");
//                wait();
//            } catch (InterruptedException e) {
//            }
//        }
//
//        if (!list.isEmpty()) {
//            for (String url : list) {
//                returnValue = url;
//                if (!("".equals(returnValue))) {
//                    list.remove(url);
//                    break;
//                }
//
//            }
//        }
//
////        lock.unlock();
//
//        return returnValue;
    }



}

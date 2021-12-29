package crawler;

import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.TimeUnit;

public class DownloadDb implements Db<String> {


    private final Set<String> list = new ConcurrentSkipListSet<>();
    private final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(params.URL_QUEUE_SIZE);


    @Override
    public void add(String element) throws InterruptedException {
        if (!list.contains(element)) {
//            System.out.print("UrlList adding url " + element);
//            System.out.println("  size before " + list.size()+" "+ queue.size());
            list.add(element);
            queue.put(element);
//            queue.offer(element,10,TimeUnit.SECONDS );
//            System.out.println("UrlList size after " + list.size()+" "+ queue.size());
        }

    }

    public  String getNext() throws InterruptedException {


        return queue.poll(params.WAIT_FOR_ELEMENT_TO_ADD,TimeUnit.SECONDS );

    }



}

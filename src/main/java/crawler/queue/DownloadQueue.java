package crawler.queue;

import crawler.Params;

import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.TimeUnit;

public class DownloadQueue implements Queue<String> {
    private final Set<String> fullUrlList = new ConcurrentSkipListSet<>();
    private final ArrayBlockingQueue<String> downloadQueue = new ArrayBlockingQueue<>(Params.URL_QUEUE_SIZE);

    @Override
    public void addElement(String element) throws InterruptedException {
        if (!fullUrlList.contains(element)) {
            fullUrlList.add(element);
            downloadQueue.put(element);
        }
    }

    public String getNextElement() throws InterruptedException {
        return downloadQueue.poll(Params.TIMEOUT_IF_QUEUE_IS_EMPTY, TimeUnit.SECONDS);
    }
}

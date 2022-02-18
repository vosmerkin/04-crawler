package crawler.queue;

import crawler.Params;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.TimeUnit;

public class DownloadQueue implements Queue<String> {
    private final Set<String> fullUrlList = new ConcurrentSkipListSet<>();
    private final ArrayBlockingQueue<String> downloadQueue = new ArrayBlockingQueue<>(Params.URL_QUEUE_SIZE);
    private static final Logger log = LoggerFactory.getLogger(DownloadQueue.class);

    @Override
    public void addElement(String element) throws InterruptedException {
        log.debug("DownloadQueue addElement {}", Thread.currentThread().getName());
        if (null != element) {
            if (!fullUrlList.contains(element)) {
                fullUrlList.add(element);
                downloadQueue.put(element);
            }
        }
    }

    public String getNextElement() throws InterruptedException {
        log.debug("DownloadQueue getNextElement {}", Thread.currentThread().getName());
        return downloadQueue.poll(Params.TIMEOUT_IF_QUEUE_IS_EMPTY, TimeUnit.SECONDS);
    }

    public void addElements(Set<String> elements) throws InterruptedException {
        log.debug("DownloadQueue addElements {}", Thread.currentThread().getName());
        if (null != elements) {
            for (String element : elements) {
                this.addElement(element);
            }
        }
    }
}

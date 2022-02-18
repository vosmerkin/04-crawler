package crawler.thread;

import crawler.queue.AnalyzeQueue;
import crawler.queue.DownloadQueue;
import crawler.services.DownloadUrl;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DownloadThread implements Runnable {
    private final DownloadQueue downloadDb;
    private final AnalyzeQueue analyzeDb;
    private static final Logger log = LoggerFactory.getLogger(AnalyzeThread.class);

    static {
        Thread.currentThread().setName("DownloadThread_ " + Thread.currentThread().getId());
    }

    public DownloadThread(DownloadQueue downloadDb, AnalyzeQueue analyzeDb) {
        this.downloadDb = downloadDb;
        this.analyzeDb = analyzeDb;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("DownloadThread_ " + Thread.currentThread().getId());
        log.info("DownloadThread run {}", Thread.currentThread().getName());
        String url = null;
        Document doc;
        DownloadUrl downloadUrl = new DownloadUrl();
        boolean hasElementsToDownload = true;
        while (hasElementsToDownload) {
            log.info("DownloadThread Requesting URL {}", Thread.currentThread().getName());
            try {
                url = downloadDb.getNextElement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!(null == url)) {
                log.info("DownloadThread Downloading URL {} {}", url, Thread.currentThread().getName());
                try {
                    doc = downloadUrl.getByUrl(url);
                    analyzeDb.addElement(doc);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                hasElementsToDownload = false;
            }
        }
        log.info("DownloadThread finishing {}", Thread.currentThread().getName());
    }
}

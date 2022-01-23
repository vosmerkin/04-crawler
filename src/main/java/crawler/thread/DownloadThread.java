package crawler.thread;

import crawler.queue.AnalyzeQueue;
import crawler.queue.DownloadQueue;
import crawler.services.DownloadUrl;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.logging.Logger;

public class DownloadThread implements Runnable {
    private final DownloadQueue downloadDb;
    private final AnalyzeQueue analyzeDb;
    private static Logger log = Logger.getLogger(DownloadThread.class.getName());

    static {
        Thread.currentThread().setName("DownloadThread_ " + Thread.currentThread().getId());
    }

    public DownloadThread(DownloadQueue downloadDb, AnalyzeQueue analyzeDb) {
        this.downloadDb = downloadDb;
        this.analyzeDb = analyzeDb;
    }

    public Long getId() {
        return Thread.currentThread().getId();
    }

    @Override
    public void run() {
        Thread.currentThread().setName("DownloadThread_ " + Thread.currentThread().getId());
        log.info(Thread.currentThread().getName()  + " started");
        String url = null;
        Document doc;
        DownloadUrl downloadUrl = new DownloadUrl();
        boolean hasElementsToDownload = true;
        while (hasElementsToDownload) {
            log.info(Thread.currentThread().getName() + "_Requesting URL ");
            try {
                url = downloadDb.getNextElement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!(null == url)) {
                log.info(Thread.currentThread().getName() + " Downloading URL " + url);
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
        log.info(Thread.currentThread().getName() +  " Download finishing");
    }
}

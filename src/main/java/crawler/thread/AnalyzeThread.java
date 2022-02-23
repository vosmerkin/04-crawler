package crawler.thread;

import crawler.queue.AnalyzeQueue;
import crawler.queue.DownloadQueue;
import crawler.services.AnalyzePage;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnalyzeThread implements Runnable {
    private final DownloadQueue downloadDb;
    private final AnalyzeQueue analyzeDb;
    private static final Logger log = LoggerFactory.getLogger(AnalyzeThread.class);

    static {
        Thread.currentThread().setName("AnalyzeThread_" + Thread.currentThread().getId());
    }

    public AnalyzeThread(DownloadQueue downloadDb, AnalyzeQueue analyzeDb) {
        this.downloadDb = downloadDb;
        this.analyzeDb = analyzeDb;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("AnalyzeThread_" + Thread.currentThread().getId());
        log.info("AnalyzeThread run {}", Thread.currentThread().getName());
        Document doc;
        AnalyzePage analyzePage = new AnalyzePage();
        boolean hasElementsToAnalyze = true;
        while (hasElementsToAnalyze) {
            log.info("AnalyzeThread Requesting page {}", Thread.currentThread().getName());
            try {
                doc = analyzeDb.getNextElement();
                if (doc == null) {
                    hasElementsToAnalyze = false;
                } else {
                    downloadDb.addElements(analyzePage.analyze(doc));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        log.info("AnalyzeThread finishing {}", Thread.currentThread().getName());
    }

}

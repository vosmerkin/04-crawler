package crawler.thread;

import crawler.services.AnalyzePage;
import crawler.queue.AnalyzeQueue;
import crawler.queue.DownloadQueue;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.logging.Logger;

public class AnalyzeThread implements Runnable {
    private final DownloadQueue downloadDb;
    private final AnalyzeQueue analyzeDb;
    static {
        Thread.currentThread().setName("AnalyzeThread_" + Thread.currentThread().getId());
    }

    private static Logger log = Logger.getLogger(AnalyzeThread.class.getName());

    public AnalyzeThread(DownloadQueue downloadDb, AnalyzeQueue analyzeDb) {
        this.downloadDb = downloadDb;
        this.analyzeDb = analyzeDb;
    }

    public Long getId() {
        return Thread.currentThread().getId();
    }


    @Override
    public void run() {
        Thread.currentThread().setName("AnalyzeThread_" + Thread.currentThread().getId());
        log.info(Thread.currentThread().getName() + " started");
        Document doc = null;
        AnalyzePage analyzePage = new AnalyzePage();
        boolean hasElementsToAnalyze = true;
        while (hasElementsToAnalyze) {
            log.info(Thread.currentThread().getName() + "_Requesting page");
            try {
                doc = analyzeDb.getNextElement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (null != doc) {
                log.info(Thread.currentThread().getName() + " Analyzing page " + doc.baseUri());
                try {
                    downloadDb.addElement(analyzePage.analyze(doc);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                hasElementsToAnalyze = false;
            }
        }
        log.info(Thread.currentThread().getName() + "_Analyze finishing");
    }
}

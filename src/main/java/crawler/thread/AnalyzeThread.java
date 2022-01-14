package crawler.thread;

import crawler.services.AnalyzePage;
import crawler.queue.AnalyzeQueue;
import crawler.queue.DownloadQueue;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class AnalyzeThread implements Runnable {
    private final DownloadQueue downloadDb;
    private final AnalyzeQueue analyzeDb;

    public AnalyzeThread(DownloadQueue downloadDb, AnalyzeQueue analyzeDb) {
        this.downloadDb = downloadDb;
        this.analyzeDb = analyzeDb;
    }

    public Long getId() {
        return Thread.currentThread().getId();
    }

    @Override
    public void run() {
        System.out.println("ID " + getId() + " Analyze started");
        Document doc = null;
        AnalyzePage analyzePage = new AnalyzePage();
        boolean hasElementsToAnalyze = true;
        while (hasElementsToAnalyze) {
            System.out.println("ID " + getId() + " Requesting page ");
            try {
                doc = analyzeDb.getNextElement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!(null == doc)) {
                System.out.println("ID " + getId() + " Analyzing page " + doc.baseUri());
                try {
                    analyzePage.analyze(doc, downloadDb);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                hasElementsToAnalyze = false;
            }
        }
        System.out.println("ID " + getId() + " Analyze finishing");
    }
}

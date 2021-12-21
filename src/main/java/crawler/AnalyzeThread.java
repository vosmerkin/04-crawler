package crawler;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AnalyzeThread implements Runnable {
    String id;
    private  UrlDb urlDb;
//    private final Lock lock = new ReentrantLock();

    AnalyzeThread (String id,UrlDb db ) {
        this.id = id;
        urlDb=db;
    }


    @Override
    public void run() {
        Document doc = null;
        AnalyzePage analyzePage = new AnalyzePage();

//        while (db.hasURLsToDownload() || db.hasPagesToAnalyze()) {
        while (true) {

            System.out.println("ID " + id + " Requesting page ");
            doc = urlDb.getNextPage();
            System.out.println("ID " + id + " Received page " + doc.baseUri());

            if (!(null == doc)) {
                System.out.println("ID " + id + " Analyzing page " + doc.baseUri());
                try {
                    analyzePage.analyze(doc);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("ID " + id + " Analyze idling for " + params.nextIsNullTimeout + "ms");
                try {
                    TimeUnit.MILLISECONDS.sleep(params.nextIsNullTimeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
//        System.out.println("ID " + id + " Analyze finished");
    }
}

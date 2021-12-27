package crawler;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class AnalyzeThread implements Runnable {
    String id;
    private DownloadDb downloadDb;
    private AnalyzeDb analyzeDb;



    AnalyzeThread (String id, DownloadDb downloadDb, AnalyzeDb analyzeDb) {
        this.id = id;
        this.downloadDb =downloadDb;
        this.analyzeDb=analyzeDb;
    }


    @Override
    public void run() {
        Document doc = null;
        AnalyzePage analyzePage = new AnalyzePage();


        while (downloadDb.hasElementsToProceed() || analyzeDb.hasElementsToProceed()) {
//        while (true) {

            System.out.println("ID " + id + " Requesting page ");
            try {
                doc = analyzeDb.getNext();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("ID " + id + " Received page " + doc.baseUri());

            if (!(null == doc)) {
                System.out.println("ID " + id + " Analyzing page " + doc.baseUri());
                try {
                    analyzePage.analyze(doc, downloadDb);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("ID " + id + " Analyze idling for " + params.NEXT_IS_NULL_TIMEOUT + "ms");
                try {
                    TimeUnit.MILLISECONDS.sleep(params.NEXT_IS_NULL_TIMEOUT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
//        System.out.println("ID " + id + " Analyze finished");
    }
}

package crawler;

import org.jsoup.nodes.Document;

import java.io.IOException;

public class AnalyzeThread implements Runnable {
    String id;
    private final DownloadDb downloadDb;
    private final AnalyzeDb analyzeDb;


    AnalyzeThread(String id, DownloadDb downloadDb, AnalyzeDb analyzeDb) {
        this.id = id;
        this.downloadDb = downloadDb;
        this.analyzeDb = analyzeDb;
    }


    @Override
    public void run() {
        Thread.currentThread().setName(id);
        System.out.println("ID " + Thread.currentThread().getId() + " Analyze started");
        Document doc = null;
        AnalyzePage analyzePage = new AnalyzePage();
        boolean alive = true;


        while (alive) {

            System.out.println("ID " + Thread.currentThread().getId() + " Requesting page ");
            try {
                doc = analyzeDb.getNext();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("ID " + id + " Received page " + ((null==doc)? null :doc.baseUri()));

            if (!(null == doc)) {
                System.out.println("ID " + Thread.currentThread().getId() +" Analyzing page " + doc.baseUri());
                try {
                    analyzePage.analyze(doc, downloadDb);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            } else {

                alive = false;


            }


        }
        System.out.println("ID " + Thread.currentThread().getId() + " Analyze finishing");
    }
}

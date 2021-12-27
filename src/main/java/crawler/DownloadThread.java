package crawler;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DownloadThread implements Runnable {
    String id;
    private DownloadDb downloadDb;
    private AnalyzeDb analyzeDb;

    DownloadThread(String id, DownloadDb downloadDb, AnalyzeDb analyzeDb) {
        this.id = id;
        this.downloadDb =downloadDb;
        this.analyzeDb=analyzeDb;
    }


    @Override
    public void run() {
        String url = null;
        Document doc = null;
        DownloadUrl downloadUrl = new DownloadUrl();


        while (downloadDb.hasElementsToProceed() || analyzeDb.hasElementsToProceed()) {
//        while (true) {


            System.out.println("ID " + id + " Requesting URL ");
            try {
                url = downloadDb.getNext();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ID " + id + " Received URL " + url);


            if (!(null ==url)) {
                System.out.println("ID " + id + " Downloading URL " + url);
                try {
                    doc = downloadUrl.getByUrl(url);
                    analyzeDb.add(doc);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("ID " + id + " Download idling for " + params.NEXT_IS_NULL_TIMEOUT + "ms");
                try {
                    TimeUnit.MILLISECONDS.sleep(params.NEXT_IS_NULL_TIMEOUT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
//        System.out.println("ID " + id + " Download finished");
    }
}

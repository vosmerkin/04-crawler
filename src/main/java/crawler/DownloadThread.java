package crawler;

import org.jsoup.nodes.Document;

import java.io.IOException;

public class DownloadThread implements Runnable {
    String id;
    private final DownloadDb downloadDb;
    private final AnalyzeDb analyzeDb;

    DownloadThread(String id, DownloadDb downloadDb, AnalyzeDb analyzeDb) {
        this.id = id;
        this.downloadDb = downloadDb;
        this.analyzeDb = analyzeDb;
    }


    @Override
    public void run() {
        Thread.currentThread().setName(id);
        System.out.println("ID " + Thread.currentThread().getId() + " Download started");
        String url = null;
        Document doc;
        DownloadUrl downloadUrl = new DownloadUrl();
        boolean alive = true;


        while (alive) {


            System.out.println("ID " + Thread.currentThread().getId() + " Requesting URL ");
            try {
                url = downloadDb.getNext();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("ID " + Thread.currentThread().getId() + " Received URL " + url);


            if (!(null == url)) {
                System.out.println("ID " + Thread.currentThread().getId() + " Downloading URL " + url);
                try {
                    doc = downloadUrl.getByUrl(url);
                    analyzeDb.add(doc);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            } else {

                alive = false;

            }


        }
        System.out.println("ID " + Thread.currentThread().getId() + " Download finishing");
    }
}

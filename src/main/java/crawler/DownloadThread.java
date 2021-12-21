package crawler;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DownloadThread implements Runnable {
    String id;
    private final Lock lock = new ReentrantLock();

    DownloadThread(String id) {
        this.id = id;
    }


    @Override
    public void run() {
        String url = null;
        Document doc = null;
        UrlDb db = new UrlDb();
        GetByUrl getByUrl = new GetByUrl();


//        while (db.hasURLsToDownload() || db.hasPagesToAnalyze()) {
        while (true) {


            System.out.println("ID " + id + " Requesting URL ");
            url = db.getNextUrl();
            System.out.println("ID " + id + " Received URL " + url);


            if (!"".equals(url)) {
                System.out.println("ID " + id + " Downloading URL " + url);
                try {
                    doc = getByUrl.getByUrl(url);
                    db.addPage(doc);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("ID " + id + " Download idling for " + params.nextIsNullTimeout + "ms");
                try {
                    TimeUnit.MILLISECONDS.sleep(params.nextIsNullTimeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
//        System.out.println("ID " + id + " Download finished");
    }
}

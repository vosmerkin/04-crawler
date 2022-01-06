package crawler.thread;

import crawler.DownloadUrl;
import crawler.queue.AnalyzeQueue;
import crawler.queue.DownloadQueue;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class DownloadThread implements Runnable {

    private final DownloadQueue downloadDb;
    private final AnalyzeQueue analyzeDb;

    public DownloadThread(DownloadQueue downloadDb, AnalyzeQueue analyzeDb) {

        this.downloadDb = downloadDb;
        this.analyzeDb = analyzeDb;
    }

    public Long getId(){
        return Thread.currentThread().getId();
    }

    @Override
    public void run() {
        System.out.println("ID " + getId() + " Download started");
        String url = null;
        Document doc;
        DownloadUrl downloadUrl = new DownloadUrl();
        boolean hasElementsToDownload = true;


        while (hasElementsToDownload) {


            System.out.println("ID " + getId() + " Requesting URL ");
            try {
                url = downloadDb.getNextElement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("ID " + Thread.currentThread().getId() + " Received URL " + url);


            if (!(null == url)) {
                System.out.println("ID " + getId() + " Downloading URL " + url);
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
        System.out.println("ID " + getId() + " Download finishing");
    }
}

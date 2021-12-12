package crawler;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DownloadThread implements Runnable {
    String id;

    DownloadThread (String id) {
        this.id=id;
    }


    @Override
    public void run() {
        String url=null;
        Document doc=null;
        UrlDb db=new UrlDb();
        GetByUrl getByUrl=new GetByUrl();


        while (db.hasURLsToDownload() || db.hasPagesToAnalyze()) {

            url = db.getNextUrl();
            if (!"".equals(url)) {
                System.out.println("ID "+ id + " Downloading URL "+url);
                try {
                    doc = getByUrl.getByUrl(url);
                    db.addPage(doc);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

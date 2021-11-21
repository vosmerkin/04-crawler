package crawler;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DownloadThread implements Runnable {
    String id;

    DownloadThread (String id) {
        this.id=id;
    }


    @Override
    public void run() {
        String url=null;
        Document doc=null;
        AnalizePage analizePage=new AnalizePage();
        UrlDb db=new UrlDb();
        GetByUrl getByUrl=new GetByUrl();

        while (db.hasURLsToDownload) {
            url = db.getNextUrl();
            System.out.println("ID "+ id + " URL "+url);
            //download url
            if (!"".equals(url)) {
                try {
                    doc = getByUrl.getByUrl(url);

                    analizePage.analize(doc);
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

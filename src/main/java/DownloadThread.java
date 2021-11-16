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
        //        while (UrlDb.hasNext()) {
//            //get new link from db
//            if (UrlDb.hasNext()) {
//                url = UrlDb.getNextUrl();
//            } else {
//                if (!UrlDb.allDownloadedLinksAnalized()) {
//                    try {
//                        TimeUnit.MILLISECONDS.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }

        while (UrlDb.hasURLsToDownload) {
            url = UrlDb.getNextUrl();
            System.out.println("ID "+ id + " URL "+url);
            //download url
            if (!"".equals(url)) {
                try {
                    doc = GetByUrl.getByUrl(url);

                    AnalizePage.Analize(doc);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.yield();
        }



    }
}

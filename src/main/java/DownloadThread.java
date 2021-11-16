import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DownloadThread implements Runnable {
    @Override
    public void run() {
        String url;
        Document doc;

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
        url = UrlDb.getNextUrl();

        //download url
        if (!"".equals(url)) {
            try {
                doc = GetByUrl.getByUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}

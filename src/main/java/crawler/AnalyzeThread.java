package crawler;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AnalyzeThread implements  Runnable{
    String id;

    AnalyzeThread (String id) {
        this.id=id;
    }


    @Override
    public void run() {
        UrlDb db=new UrlDb();
        Document doc=null;
        AnalyzePage analyzePage=new AnalyzePage();

        while (db.hasURLsToDownload() || db.hasPagesToAnalyze()) {

            doc = db.getNextPage();
            if (!(null == doc)){
                System.out.println("ID "+ id + " Analyzing url "+doc.baseUri());
                try {
                    analyzePage.analyze(doc);
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

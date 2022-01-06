package crawler;

import crawler.queue.DownloadQueue;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class AnalyzePage {


    public void analyze(Document doc, DownloadQueue downloadDb) throws IOException, InterruptedException {

        Elements hrefs = doc.select("a[href]");
        for (Element href : hrefs) {
//            System.out.println(href.attr("abs:href"));
            if ((href!=null)&&(href.attr("abs:href").contains(doc.baseUri()))) {
                downloadDb.addElement(href.attr("abs:href"));
            }
        }
//        System.out.println("AnalyzePage quiting");
    }
}

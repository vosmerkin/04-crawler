package crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class AnalyzePage {

    public void analyze(Document doc) throws IOException {
        UrlDb db = new UrlDb();

        Elements hrefs = doc.select("a[href]");
        for (Element href : hrefs) {
//            System.out.println(href.attr("abs:href"));
            if ((href!=null)&&(href.attr("abs:href").toString().contains(doc.baseUri()))) {
                db.addUrl(href.attr("abs:href").toString());
//               System.out.println(href.attr("abs:href"));
            }
        }
//        System.out.println("AnalyzePage quiting");
    }
}

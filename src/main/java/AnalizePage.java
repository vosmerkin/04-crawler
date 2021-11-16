import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class AnalizePage {

    public static void Analize(Document doc) throws IOException {

        Elements hrefs = doc.select("a[href]");
        for (Element href : hrefs) {
            if ((href!=null)&&(href.toString().contains(doc.baseUri()))) {
                UrlDb.addUrl(href.toString());
                System.out.println(href);
            }
        }
    }
}

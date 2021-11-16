import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class GetPage {


    static {
        UrlCollection = new HashSet<>();
    }

    public void main() throws IOException {

        UrlDb UrlDb = new UrlDb();
        UrlDb.addUrl("https://www.google.com.ua/");


        getByUrl("https://www.google.com.ua/");

    }

    private String URL;

    private static HashSet<String> UrlCollection;

    public static void getByUrl(String Url) throws IOException {
            Connection conn = Jsoup.connect(Url).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:59.0) Gecko/20100101");
            Connection.Response response = conn.execute();
            Document doc = conn.get();
            System.out.println(doc.baseUri());

            Elements hrefs = doc.select("a[href]");
            for (Element href : hrefs) {
                if ((href!=null)&&(href.toString().contains(doc.baseUri()))) {
                    UrlCollection.add(href.toString());
                    System.out.println(href);
                }
            }
    }


}

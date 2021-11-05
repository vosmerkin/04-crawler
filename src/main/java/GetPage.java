import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class GetPage {


    public static void main(String[] args) throws IOException {

        getByUrl("https://www.google.com.ua/");
    }
    static{
        UrlCollection=new HashSet<>();
    }

    private String URL;

    private static HashSet<String> UrlCollection;

    public static void getByUrl(String Url) throws IOException {
//        try {
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
//        } catch (IOException e) {
//            System.out.println("io - " + e);
//        }


    }


}

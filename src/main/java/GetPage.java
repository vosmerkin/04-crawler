import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.*;

public class GetPage {


    public static void main(String[] args) throws IOException {

        UrlDb UrlDb = new UrlDb();
        UrlDb.addUrl("https://sitejs.org/");
        UrlDb.hasURLsToDownload = true;



        Thread t1 = new Thread(new DownloadThread("1"));
        Thread t2 = new Thread(new DownloadThread("2"));
        Thread t3 = new Thread(new DownloadThread("3"));
        Thread t4 = new Thread(new DownloadThread("4"));
        Thread t5 = new Thread(new DownloadThread("5"));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }


}

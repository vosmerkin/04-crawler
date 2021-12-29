package crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class DownloadUrl {


    public Document getByUrl(String url) throws IOException {

        System.out.println("Downloading " +url);
        Connection conn = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:59.0) Gecko/20100101").ignoreContentType(true);
        Connection.Response response = conn.execute();
        return conn.get();
    }
}

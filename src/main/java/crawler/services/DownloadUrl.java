package crawler.services;

import crawler.Params;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class DownloadUrl {

    public Document getByUrl(String url) throws IOException {
        System.out.println("Downloading " + url);
        Connection conn = Jsoup.connect(url).userAgent(Params.CONNECTION_USERAGENT_STRING).ignoreContentType(true);
        conn.execute();
        return conn.get();
    }
}

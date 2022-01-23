package crawler.services;

import crawler.Params;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.logging.Logger;

public class DownloadUrl {
    private static Logger log = Logger.getLogger(DownloadUrl.class.getName());

    public Document getByUrl(String url) throws IOException {
        log.config(Thread.currentThread().getName() + "_DownloadUrl_downloading URL " + url);
        Connection conn = Jsoup.connect(url).userAgent(Params.CONNECTION_USERAGENT_STRING).ignoreContentType(true);
        conn.execute();
        return conn.get();
    }
}

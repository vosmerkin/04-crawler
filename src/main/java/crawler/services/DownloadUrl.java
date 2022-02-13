package crawler.services;

import crawler.Params;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DownloadUrl {
    private static final Logger log = LoggerFactory.getLogger(AnalyzePage.class);


    public Document getByUrl(String url) throws IOException {
        log.debug("DownloadUrl getByUrl {} {}", url, Thread.currentThread().getName());
        Connection conn = Jsoup.connect(url).userAgent(Params.CONNECTION_USERAGENT_STRING).ignoreContentType(true);
        conn.execute();
        return conn.get();
    }
}

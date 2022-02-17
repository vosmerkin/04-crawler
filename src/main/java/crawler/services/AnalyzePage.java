package crawler.services;

import crawler.Params;
import crawler.queue.DownloadQueue;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class AnalyzePage {
    private static final Logger log = LoggerFactory.getLogger(AnalyzePage.class);

    public Set<String> analyze(Document doc) {
        Set<String> set = new HashSet<>();
        String newUrl;
        Elements hrefs = doc.select(Params.URL_SEARCH_STRING1);
        for (Element href : hrefs) {
            newUrl = href.attr(Params.URL_SEARCH_STRING2);
            if (href != null && newUrl.contains(doc.baseUri())) {
                set.add(newUrl);
                log.debug("AnalyzePage analyze adding URL {} {}", newUrl, Thread.currentThread().getName());
            }
        }
        return set;
    }
}
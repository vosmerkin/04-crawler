package crawler.services;

import crawler.Params;
import crawler.queue.DownloadQueue;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class AnalyzePage {
    private static Logger log = Logger.getLogger(AnalyzePage.class.getName());

    public Set<String> analyze(Document doc) throws IOException, InterruptedException {
        Set<String> set = new HashSet<>();
        String newUrl = new String();
        Elements hrefs = doc.select(Params.URL_SEARCH_STRING1);
        for (Element href : hrefs) {
            newUrl = href.attr(Params.URL_SEARCH_STRING2);
            if (href != null && newUrl.contains(doc.baseUri())) {
                set.add(newUrl);
                log.config(Thread.currentThread().getName() + "_AnalyzePage_adding URL " + href.attr(Params.URL_SEARCH_STRING2));
            }
        }
    }
}
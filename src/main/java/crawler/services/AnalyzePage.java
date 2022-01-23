package crawler.services;

import crawler.Params;
import crawler.queue.DownloadQueue;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.logging.Logger;

public class AnalyzePage {
    private static Logger log = Logger.getLogger(AnalyzePage.class.getName());
    public void analyze(Document doc, DownloadQueue downloadDb) throws IOException, InterruptedException {
        Elements hrefs = doc.select(Params.URL_SEARCH_STRING1);
        for (Element href : hrefs) {
            if (href != null && href.attr(Params.URL_SEARCH_STRING2).contains(doc.baseUri())) {
                downloadDb.addElement(href.attr(Params.URL_SEARCH_STRING2));
                log.config(Thread.currentThread().getName() + "_AnalyzePage_adding URL " + href.attr(Params.URL_SEARCH_STRING2));
            }
        }
    }
}
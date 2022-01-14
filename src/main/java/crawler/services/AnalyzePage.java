package crawler.services;

import crawler.Params;
import crawler.queue.DownloadQueue;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class AnalyzePage {
    public void analyze(Document doc, DownloadQueue downloadDb) throws IOException, InterruptedException {
        Elements hrefs = doc.select(Params.URL_SEARCH_STRING1);
        for (Element href : hrefs) {
            if (href != null && href.attr(Params.URL_SEARCH_STRING2).contains(doc.baseUri()))
                downloadDb.addElement(href.attr(Params.URL_SEARCH_STRING2));
        }
    }
}
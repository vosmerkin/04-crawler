package crawler;

import org.jsoup.nodes.Document;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UrlDb {

    //    public static Map.Entry<String, Boolean> UrlListEntry;
    //String for
    //
    public static final Set<String> UrlList = new CopyOnWriteArraySet<>();
    public static final Set<Document> AnalyzeList = new CopyOnWriteArraySet<>();
    private Lock lock = new ReentrantLock();

    public boolean hasURLsToDownload() {
        System.out.println("UrlList isEmpty = " + UrlList.isEmpty());
        return !UrlList.isEmpty();
    }

    public boolean hasPagesToAnalyze() {
        System.out.print("AnalyzeList isEmpty = " + UrlList.isEmpty());
        System.out.println(" AnalyzeList size " + AnalyzeList.size());
        return !AnalyzeList.isEmpty();
    }

    public synchronized void addUrl(String url) {
        System.out.print("UrlList adding url " + url);
        System.out.println("  size before " + UrlList.size());
        UrlList.add(url);
        System.out.println("UrlList size after " + UrlList.size());
        notifyAll();
    }

    public synchronized void addPage(Document doc) {
        System.out.print("AnalyzeList adding page " + doc.baseUri());
        System.out.println("  size before " + AnalyzeList.size());
        AnalyzeList.add(doc);
        System.out.println("AnalyzeList size after " + AnalyzeList.size());
        notifyAll();

    }

    public synchronized String getNextUrl() {

        String returnValue = "";


        while (UrlList.isEmpty()) {
            try {
                System.out.println("UrlList size" + UrlList.size() + ".Waiting");
                wait();
            } catch (InterruptedException e) {
            }
        }

        if (!UrlList.isEmpty()) {
            for (String url : UrlList) {
                returnValue = url;
                if (!("".equals(returnValue))) {
                    UrlList.remove(url);
                    break;
                }

            }
        }
        System.out.println("UrlList size" + UrlList.size());
//        lock.unlock();

        return returnValue;
    }


    public synchronized Document getNextPage() {
        Document returnValue = null;

        while (AnalyzeList.isEmpty()) {
            try {
                System.out.println("AnalyzeList size " + AnalyzeList.size() + ".Waiting");
                wait();
            } catch (InterruptedException e) {
            }
        }

        if (!AnalyzeList.isEmpty()) {
            for (Document doc : AnalyzeList) {
                returnValue = doc.clone();
                if (!(null == returnValue)) {
                    AnalyzeList.remove(doc);
                    break;
                }
            }
        }
        System.out.println("AnalyzeList size" + AnalyzeList.size());

        return returnValue;
    }


}

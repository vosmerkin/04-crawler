package crawler;

import org.jsoup.nodes.Document;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UrlDb {

//    public static Map.Entry<String, Boolean> UrlListEntry;
    //String for
    //
    public static final Set<String> UrlList = new CopyOnWriteArraySet<>();
    public static final Set<Document> AnalyzeList = new CopyOnWriteArraySet<>();
    private Lock lock= new ReentrantLock();

    public  boolean hasURLsToDownload (){
        return !UrlList.isEmpty();
    };
    public boolean hasPagesToAnalyze (){
        return !AnalyzeList.isEmpty();
    }

    public  String getNextUrl() {
//        for(Map.Entry<String, Boolean> entry: UrlList.entrySet())
//        {
//            if (!entry.getValue()) {
//                entry.setValue(true);
//                return entry.getKey();
//            }
//        }
//        hasURLsToDownload=false;
        String returnValue="";
//        lock.lock();

        if (!UrlList.isEmpty()) {
            for (String url:UrlList){
                returnValue = url.toString();
                if (!(""==returnValue)){
                    UrlList.remove(url);
                    break;
                }

            }
        }
//        lock.unlock();

        return returnValue;
    }


    public  void addUrl(String url) {
            UrlList.add(url);
    }

    public void addPage(Document doc) {
        AnalyzeList.add(doc);
    }

    public Document getNextPage() {
        Document returnValue=null;
//        lock.lock();
        if (!AnalyzeList.isEmpty()) {
            for (Document doc:AnalyzeList){
                returnValue = doc.clone();
                if (!(null==returnValue)){
                    AnalyzeList.remove(doc);
                    break;
                }
            }
        }
//        lock.unlock();
        return returnValue;
    }



}

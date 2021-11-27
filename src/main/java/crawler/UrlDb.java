package crawler;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class UrlDb {

//    public static Map.Entry<String, Boolean> UrlListEntry;
    //String for
    //
    private static final ConcurrentHashMap<String, Boolean> UrlList = new ConcurrentHashMap<String, Boolean>();  //boolean как показатель того, что урл уже передан какомуто потоку

    public  boolean hasURLsToDownload = true;


    public  String getNextUrl() {

        Iterator<Map.Entry<String, Boolean>> UrlListIterator1 = UrlList.entrySet().iterator();
        while (UrlListIterator1.hasNext()) {
            Map.Entry<String, Boolean> entry = UrlListIterator1.next();
            if (!entry.getValue()) {
                entry.setValue(true);
                return entry.getKey();
            }
        }
//        hasURLsToDownload=false;
        return "";
    }


    public  void addUrl(String url) {
        if (!UrlList.containsKey(url)) {
            UrlList.put(url, false);
        }

    }


}

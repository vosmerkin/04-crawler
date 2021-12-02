package crawler;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class UrlDb {

//    public static Map.Entry<String, Boolean> UrlListEntry;
    //String for
    //
    private static final Map<String, Boolean> UrlList = new ConcurrentHashMap<String, Boolean>();  //boolean как показатель того, что урл уже передан какомуто потоку

    public  boolean hasURLsToDownload=true;
//            () { return UrlList.containsValue(true); };


    public  String getNextUrl() {

        for(Map.Entry<String, Boolean> entry: UrlList.entrySet())
        {
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

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UrlDb {

    public static Map.Entry<String, Boolean> UrlListEntry;
    //String for
    //
    private static final ConcurrentHashMap<String, Boolean> UrlList = new ConcurrentHashMap<String, Boolean>();
    private static final Iterator UrlListIterator = UrlList.entrySet().iterator();

    public static boolean hasNext() {
        return UrlListIterator.hasNext();
    }

    public static boolean allDownloadedLinksAnalized() {
        return UrlList.contains(false);
    }

    public static String getNextUrl() {

        if (UrlListIterator.hasNext()) {

            UrlListEntry = (Map.Entry<String, Boolean>) UrlListIterator.next();

            return UrlListEntry.getKey();
        } else {
            return "";
        }
    }


    public static void addUrl(String Url) {
        if (!UrlList.containsKey(Url)) {
            UrlList.put(Url, false);
        }

    }


}

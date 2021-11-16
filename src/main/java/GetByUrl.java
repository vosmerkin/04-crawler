import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class GetByUrl {


    public static Document getByUrl(String Url) throws IOException {
        Connection conn = Jsoup.connect(Url).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:59.0) Gecko/20100101");
        Connection.Response response = conn.execute();
        return conn.get();
    }
}

package crawler.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;


class AnalyzePageTest {
    final String baseURL = "https://site.mockito.org/";
    final String baseURL1 = "https://site.mockito1.org/";
    final String html = "<html><head><title>First parse</title></head>"
            + "<body><p>Parsed HTML into a doc.</p>"
            + "<a href='https://site.mockito.org/'>Visit W3Schools</a>"
            + "<a href='https://www.w3schools.com'>Visit W3Schools</a>"
            + "<a href='https://site.mockito.org/123456'>Visit W3Schools</a>"
            + "</body></html>";
    AnalyzePage analyzePage;
    Document document;
    List<String> expectedList = new LinkedList<>();

    @BeforeEach
    void setUp() {
        analyzePage = new AnalyzePage();
        document = Jsoup.parse(html);
        document.setBaseUri(baseURL);


        expectedList.add("https://site.mockito.org/123456");
        expectedList.add("https://site.mockito.org/");
    }

    @Test
    void AnalyzePageTest1() {
//        Assertions.assertEquals(expectedList.toArray(), analyzePage.analyze(document).toArray());
        Assertions.assertIterableEquals(expectedList, analyzePage.analyze(document));
    }

    @Test
    void AnalyzePageTestNull() {
        Assertions.assertIterableEquals(null, analyzePage.analyze(null));
    }

    @Test
    void AnalyzePageTestNoUrl() {
        document.setBaseUri(baseURL1);
        expectedList.clear();
        Assertions.assertIterableEquals(expectedList, analyzePage.analyze(document));
    }
}
//нужно ли тестировать Runnable?

package crawler.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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


    @BeforeEach
    void setUp() {
        analyzePage = new AnalyzePage();
        document = Jsoup.parse(html);
        document.setBaseUri(baseURL);
    }

    @Test
    void analyzePageTest1() {
        List<String> expectedList = List.of("https://site.mockito.org/123456", "https://site.mockito.org/");
//        Assertions.assertEquals(expectedList.toArray(), analyzePage.analyze(document).toArray());
        assertIterableEquals(expectedList, analyzePage.analyze(document));
    }

    @Test
    void analyzePageTestNull() {
        assertIterableEquals(null, analyzePage.analyze(null));
    }

    @Test
    void analyzePageTestNoUrl() {
        document.setBaseUri(baseURL1);
        assertTrue(analyzePage.analyze(document).isEmpty());
    }
}

//нужно ли тестировать Runnable?

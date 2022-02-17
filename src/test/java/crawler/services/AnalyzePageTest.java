package crawler.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;


class AnalyzePageTest {
    final String baseURL = "https://site.mockito.org/";
    final String html = "<html><head><title>First parse</title></head>"
            + "<body><p>Parsed HTML into a doc.</p>"
            + "<a href='https://www.w3schools.com'>Visit W3Schools</a>"
            + "</body></html>";
    AnalyzePage analyzePage;
    Document document;

    @BeforeEach
    void setUp() {
        analyzePage = new AnalyzePage();
        document = Jsoup.parse(html);

    }

    @Test
    void AnalyzePageTest() throws IOException, InterruptedException {
        assertThat( analyzePage.analyze(document), Matchers.containsInAnyOrder("foo", "bar"));

    }


//    void analyze() throws IOException, InterruptedException {
//        analyzePage = new AnalyzePage();
//        analyzePage.analyze(document, downloadDbMock);
//        verify(downloadDbMock, times(4)).addElement(anyString());
//
//
//    }
}
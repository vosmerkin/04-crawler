package crawler.services;

import crawler.queue.DownloadQueue;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;


class AnalyzePageTest {
    final String urlToAdd = "https://site.mockito.org/";
    AnalyzePage analyzePage;

    @BeforeEach
    void setUp() {
        analyzePage=new AnalyzePage();
        Document document = (new DownloadUrl()).getByUrl(urlToAdd);
    }
    @Test
    void AnalyzePageTest() {
        assertThat(analyzePage(document), hasItems("foo", "bar"));

    }


    void analyze() throws IOException, InterruptedException {
        downloadDbMock= mock( DownloadQueue.class);
        analyzePage = new AnalyzePage();
        analyzePage.analyze(document, downloadDbMock);
        verify(downloadDbMock, times(4)).addElement(anyString());


    }
}
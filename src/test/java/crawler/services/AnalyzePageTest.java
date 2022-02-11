package crawler.services;

import crawler.queue.DownloadQueue;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class AnalyzePageTest {
    Document document = (new DownloadUrl()).getByUrl("https://site.mockito.org/");
    AnalyzePage analyzePage;
    final String urlToAdd="https://site.mockito.org/";

    @Mock
    DownloadQueue downloadDbMock;

    AnalyzePageTest() throws IOException {
    }
//    DownloadQueue downloadDbMock= mock(DownloadQueue.class);

    @Test
    void analyze() throws IOException, InterruptedException {
        downloadDbMock= mock( DownloadQueue.class);
        analyzePage = new AnalyzePage();
        analyzePage.analyze(document,downloadDbMock);
        verify(downloadDbMock, times(4)).addElement(anyString());


    }
}
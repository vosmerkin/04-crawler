package crawler.services;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DownloadUrlTest {

    @Test
    void getByUrl() throws IOException {
        DownloadUrl testDownloadUrl = new DownloadUrl();
        String testUrl = "https://junit.org/junit5/";
        String expectedTitle = "JUnit 5";
        Document testDoc = testDownloadUrl.getByUrl(testUrl);
        assertEquals(expectedTitle, testDoc.title());
    }
}
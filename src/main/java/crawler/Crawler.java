package crawler;

import crawler.queue.AnalyzeQueue;
import crawler.queue.DownloadQueue;
import crawler.thread.AnalyzeThread;
import crawler.thread.DownloadThread;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Crawler {

    public static void main(String[] args) throws InterruptedException {
        DownloadQueue downloadDb = new DownloadQueue();
        AnalyzeQueue analyzeDb = new AnalyzeQueue();
        downloadDb.addElement(Params.STARTING_URL);
        ExecutorService exec = Executors.newFixedThreadPool(Params.THREADS_COUNT);
        DownloadThread download;
        AnalyzeThread analyze;
        for (int i = 0; i < Params.THREADS_COUNT; i++) {
            download = new DownloadThread(downloadDb, analyzeDb);
            analyze = new AnalyzeThread(downloadDb, analyzeDb);
            exec.execute(download);
            exec.execute(analyze);
        }
        TimeUnit.SECONDS.sleep(Params.SHUTDOWN_TIMEOUT_IN_SECONDS);
        exec.shutdown();
        if (!exec.awaitTermination(Params.AWAIT_TERMINATION_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)) exec.shutdownNow();
    }
}

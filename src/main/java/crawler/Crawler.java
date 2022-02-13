package crawler;

import crawler.queue.AnalyzeQueue;
import crawler.queue.DownloadQueue;
import crawler.thread.AnalyzeThread;
import crawler.thread.DownloadThread;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class Crawler {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(AnalyzeThread.class);

    public static void main(String[] args) throws InterruptedException {
        Handler fh;
        String logfile = System.getProperty("user.dir") + "/" + Params.LOG_FILE_NAME;
        try {
            fh = new FileHandler(logfile);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            Logger.getLogger("").addHandler(fh);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
            log.warn("Cannot create new logger FileHandler {} {}", logfile, e);
        }
        log.info(logfile);
        DownloadQueue downloadDb = new DownloadQueue();
        AnalyzeQueue analyzeDb = new AnalyzeQueue();
        downloadDb.addElement(Params.STARTING_URL);
        ExecutorService exec = Executors.newFixedThreadPool(Params.THREADS_COUNT);
        DownloadThread download;
        AnalyzeThread analyze;
        log.info("Starting threads");
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

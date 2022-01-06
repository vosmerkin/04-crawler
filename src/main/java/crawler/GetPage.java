package crawler;


import crawler.queue.AnalyzeQueue;
import crawler.queue.DownloadQueue;
import crawler.thread.AnalyzeThread;
import crawler.thread.DownloadThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GetPage {


    public static void main(String[] args) throws InterruptedException {

        DownloadQueue downloadDb = new DownloadQueue();
        AnalyzeQueue analyzeDb = new AnalyzeQueue();
        downloadDb.addElement("https://sitejs.org/");



        ExecutorService exec = Executors.newFixedThreadPool(Params.THREADS_COUNT);
        DownloadThread download;
        AnalyzeThread analyze;
        for (int i = 0; i < Params.THREADS_COUNT; i++) {
            download = new DownloadThread(downloadDb, analyzeDb);
            analyze = new AnalyzeThread(downloadDb, analyzeDb);
            exec.execute(download);
            exec.execute(analyze);

            System.out.println(download.getId());
            System.out.println(analyze.getId());

        }

        System.out.println("Main thread finishing");


    }


}

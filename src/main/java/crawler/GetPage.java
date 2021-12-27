package crawler;


import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class  GetPage {


    public static void main(String[] args) throws IOException, InterruptedException {

        DownloadDb downloadDb = new DownloadDb();
        AnalyzeDb analyzeDb = new AnalyzeDb();
        downloadDb.add("https://sitejs.org/");
//        urlDb.addUrl("https://lider-group.com.ua/");


        ExecutorService exec = Executors.newFixedThreadPool(params.THREADS_COUNT);
        for (int i = 0; i < params.THREADS_COUNT; i++) {
            exec.execute(new DownloadThread(Integer.toString(i+1), downloadDb, analyzeDb));
            exec.execute(new AnalyzeThread(Integer.toString(i+1), downloadDb, analyzeDb));

        }



//        Thread t1 = new Thread(new DownloadThread("1"));
//        Thread t2 = new Thread(new DownloadThread("2"));
//        Thread t3 = new Thread(new DownloadThread("3"));
//        Thread t4 = new Thread(new DownloadThread("4"));
//        Thread t5 = new Thread(new DownloadThread("5"));
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();
//        t5.start();
    }


}

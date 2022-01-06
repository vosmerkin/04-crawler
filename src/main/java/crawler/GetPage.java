package crawler;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GetPage {


    public static void main(String[] args) throws InterruptedException {

        DownloadDb downloadDb = new DownloadDb();
        AnalyzeDb analyzeDb = new AnalyzeDb();
//        downloadDb.add("https://sitejs.org/");
        downloadDb.add("https://tid.ua/");


        ExecutorService exec = Executors.newFixedThreadPool(params.THREADS_COUNT);
        DownloadThread t1;
        AnalyzeThread t2;
        for (int i = 0; i < params.THREADS_COUNT; i++) {
            t1 = new DownloadThread(Integer.toString(i + 1), downloadDb, analyzeDb);
            t2 = new AnalyzeThread(Integer.toString(i + 1), downloadDb, analyzeDb);
            exec.execute(t1);
            exec.execute(t2);

            System.out.println(t1.getId());
            System.out.println(t2.getId());

        }

        System.out.println("Main thread finishing");


    }


}

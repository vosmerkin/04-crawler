package crawler;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class  GetPage {


    public static void main(String[] args) throws InterruptedException {

        DownloadDb downloadDb = new DownloadDb();
        AnalyzeDb analyzeDb = new AnalyzeDb();
        downloadDb.add("https://sitejs.org/");
//        urlDb.addUrl("https://lider-group.com.ua/");


        ExecutorService exec = Executors.newFixedThreadPool(params.THREADS_COUNT);
        for (int i = 0; i < params.THREADS_COUNT; i++) {
            exec.execute(new DownloadThread(Integer.toString(i+1), downloadDb, analyzeDb));
            exec.execute(new AnalyzeThread(Integer.toString(i+1), downloadDb, analyzeDb));

        }

        System.out.println("Main thread finishing");


    }


}

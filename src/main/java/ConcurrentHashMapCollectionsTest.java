import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentHashMapCollectionsTest {
    public static void main(String[] args) {

        List<Integer> list = new CopyOnWriteArrayList();

        Thread t1 = new Thread() {
            public void run() {
                Random rnd1 = new Random();
                while (true) {
                    list.add((int)(Math.random() * 50 + 1));
                    System.out.println("T1_"+rnd1);
                    Thread.yield();
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                }


            }

        };


        Thread t2 = new Thread() {
            public void run() {
                for (Integer i : list) {
                    System.out.println("T2_"+i);
                    Thread.yield();
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                }

            }

        };

        t1.start();
        t2.start();
    }
}

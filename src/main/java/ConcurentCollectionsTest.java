import java.util.ArrayList;
import java.util.Random;

public class ConcurentCollectionsTest {


    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList();

        Thread t1 = new Thread() {
            public void run() {
                while (true) {
                    Integer rnd1 = new Random(47).nextInt();
                    list.add(rnd1);
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

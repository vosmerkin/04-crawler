import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;




class ConcurrentHashMapCollectionsTest {
    public static void main(String[] args) {

        List<Integer> list = new CopyOnWriteArrayList();

        Thread t1 = new Thread() {
            public void run() {
                while (true) {
                    int i=(int)(Math.random() * 50 + 1);
                    list.add(i);
                    System.out.println("T1_"+i);

                    if (list.size()>20) {
                        try {
                            Thread.sleep(5000);
                        } catch (Exception e) {
                        }
                        Thread.yield();
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
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }
        t2.start();
    }
}

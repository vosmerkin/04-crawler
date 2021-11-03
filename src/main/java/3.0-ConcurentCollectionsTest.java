import java.util.ArrayList;
import java.util.Random;

class ConcurentCollectionsTest {


    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList();

        Thread t1 = new Thread() {
            public void run() {
                System.out.println("T1_started");
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
                System.out.println("T2_started");
                for (Integer i : list) {
                    System.out.println("T2_"+i);
                    Thread.yield();
                    try {
                        Thread.sleep(5000);
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

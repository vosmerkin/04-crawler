package test;

import java.util.ArrayList;
import java.util.List;

class ConcurentSyncCollectionTest {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList();

        Thread t1 = new Thread() {
            public void run() {
//                List<Integer> integers = Collections.synchronizedList(list);
                System.out.println("T1_started");
                synchronized (list) {
                    while (true) {
                        int i = (int) (Math.random() * 50 + 1);
                        list.add(i);
                        System.out.println("T1_" + i);

                        if (list.size() > 20) {
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                            }
                            Thread.yield();
                        }
                        if (list.size() > 40) {
                            System.out.println("Quiting T1");
                            return;
                        }
                    }
                }


            }

        };


        Thread t2 = new Thread() {
            public void run() {
                System.out.println("T2_started");
                synchronized (list) {
                    for (Integer i : list) {
                        System.out.println("T2_" + i);
                        Thread.yield();
                        try {
                            Thread.sleep(5000);
                        } catch (Exception e) {
                        }
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

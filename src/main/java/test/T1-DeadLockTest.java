package test;

import java.util.concurrent.TimeUnit;

class DeadLockTest {
    static Integer[] i = {0, 0, 0};

    public static void main(String[] args) {

        Thread t1 = new Thread(new CommonResource(1, 2));
        Thread t2 = new Thread(new CommonResource(2, 1));

        t1.start();
        t2.start();
    }
}

class CommonResource implements Runnable {
    public static Integer i = 0;
    Integer id;
    Integer id2;

    CommonResource(Integer id, Integer id2){
        this.id=id;
        this.id2=id2;
        DeadLockTest.i[id]=id;
    };

    @Override
    public void run() {

        synchronized (DeadLockTest.i[id]) {
            System.out.println("Res"+id+"_locked");
            while (i++ >= 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (DeadLockTest.i[id2]) {
                    System.out.println("Res"+id +"(locking_common_resource)_" + DeadLockTest.i[id]);
                }
            }
        }
    }
}


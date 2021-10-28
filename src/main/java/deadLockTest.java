import javax.print.attribute.IntegerSyntax;
import java.util.concurrent.TimeUnit;

public class deadLockTest {
    static Integer[] i={0,0,0};

    public static void main(String[] args) {

        Thread t1 = new Thread(new commonResource1(1,2));
        Thread t2 = new Thread(new commonResource1(2,1));

        t1.start();
        t2.start();
    }
}

class commonResource1 implements Runnable {
    public static Integer i = 0;
    Integer id;
    Integer id2;

    commonResource1(Integer id,Integer id2){
        this.id=id;
        this.id2=id2;
        deadLockTest.i[id]=id;
    };

    @Override
    public void run() {

        synchronized (deadLockTest.i[id]) {
            System.out.println("Res"+id+"_locked");
            while (i++ >= 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (deadLockTest.i[id2]) {
                    System.out.println("Res"+id +"(locking_common_resource)_" + deadLockTest.i[id]);
                }
            }
        }
    }
}


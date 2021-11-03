import java.util.concurrent.TimeUnit;

class RaceConditionTest {
    public static Integer i = 0;


    public static void main(String[] args) {
        Thread t1 = new Thread(new CommonResourceI(1));
        Thread t2 = new Thread(new CommonResourceI(2));

        t1.start();
        t2.start();

    }
}

class CommonResourceI implements Runnable {
    int id;

    CommonResourceI(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        for (int i3 = id * 100; i3 < id * 100 + 100; i3++) {
            RaceConditionTest.i = i3;
            Thread.yield();
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (RaceConditionTest.i != i3) System.out.println("ID" + id + "_RaceCondition_i3=" + i3 + "_i_"+RaceConditionTest.i);
        }
    }
}

/**
 * Created by Will on 2016-11-07.
 */
public class MyThread extends Thread {
    private String s;

    public MyThread(String s) {
        super();
        this.s = s;
    }

    public void run() {
        for (int i = 0; i < 5; ++i) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }

            System.out.println(s + " running");
        }
    }

    public void start() {
        System.out.println(this.getClass().getSimpleName() + " start");
        super.start();
    }
}

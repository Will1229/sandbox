/**
 * Created by Will on 2016-11-07.
 */
public class MyRunnable implements Runnable {

    private String name;
    private Memorize<String> m;
    private int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 5; ++i) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }

            System.out.println(name + " running " + count++);
            try {
                System.out.println(m.syncGet());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public MyRunnable(String s, Memorize<String> m) {
        this.name = s;
        this.m = m;

    }
}

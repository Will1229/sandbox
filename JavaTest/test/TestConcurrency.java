import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RejectedExecutionException;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Will on 2016-11-14.
 */
public class TestConcurrency {

    @Test
    public void testSingleThreadMemorize() {

        List<String> l = new ArrayList<>();
        l.add("s1");

        List<?> l2 = new ArrayList<String>();
        List l3 = new ArrayList<>();

        MyCallable<String> myCallable = new MyCallable<>();
        myCallable.add("data");
        Memorize<String> memoize = new Memorize<>(myCallable);
        try {
            System.out.println("1 time call");
            System.out.println(memoize.syncGet());
            System.out.println("2 time call");
            System.out.println(memoize.syncGet());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMultiThreadMemorize() throws InterruptedException {
        MyCallable<String> myCallable = new MyCallable<>();
        myCallable.add("data");
        Memorize<String> m = new Memorize<>(myCallable);
        Thread t1 = new Thread(new MyRunnable("t1", m));
        Thread t2 = new Thread(new MyRunnable("t2", m));
        Thread t3 = new Thread(new MyRunnable("t3", m));
        Thread t4 = new Thread(new MyRunnable("t4", m));

        Runnable cleanRun = () -> {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("clean cache");
            synchronized (m) {
                m.cleanCache();
            }
        };

        Thread cleaner = new Thread(cleanRun);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        cleaner.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        cleaner.join();


        assertEquals(2, myCallable.getCount());

    }

    @Test(expected = RejectedExecutionException.class)
    public void testExecutor() throws ExecutionException, InterruptedException {
        MyExecutor myExecutor = new MyExecutor();
        myExecutor.execute();
    }

    @Test
    public void testAsyncExecutor() throws InterruptedException {
        MyExecutor myExecutor = new MyExecutor();
        myExecutor.asyncExecute();
    }

    @Test
    public void testSyncExecutor() throws InterruptedException {
        MyExecutor myExecutor = new MyExecutor();
        myExecutor.syncExecute();
    }

    @Test
    public void testAtomicInt() {
        MyExecutor myExecutor = new MyExecutor();
        myExecutor.atomicAdd();
    }
}

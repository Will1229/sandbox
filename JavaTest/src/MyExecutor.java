import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Created by Will on 2016-11-14.
 */
public class MyExecutor {

    private ExecutorService executor;
    private int sharedCount = 0;
    private AtomicInteger atomicInt = new AtomicInteger(0);

    private void addCount() {
        ++sharedCount;
    }

    private synchronized void syncAddCount() {
        ++sharedCount;
    }

    public void asyncExecute() throws InterruptedException {
        executor = Executors.newFixedThreadPool(3);
        IntStream.range(0, 10000).forEach(value -> executor.submit(() -> {
            addCount();
            return "done";
        }));

        stop();
        System.out.println(sharedCount);
    }


    public void syncExecute() throws InterruptedException {
        executor = Executors.newFixedThreadPool(3);
        IntStream.range(0, 10000).forEach(value -> executor.submit(() -> {
            syncAddCount();
            return "done";
        }));

        stop();
        System.out.println(sharedCount);
    }

    public void atomicAdd() {
        executor = Executors.newFixedThreadPool(3);
        IntStream.range(0, 10000).forEach(value -> executor.submit(() -> atomicInt.incrementAndGet()));

        stop();
        System.out.println(atomicInt.get());
    }

    public void execute() throws InterruptedException, ExecutionException {

        executor = Executors.newSingleThreadExecutor();

        Future<Integer> future = executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Callable " + threadName);
            Thread.sleep(1000);
            return 1;
        });

        while (!future.isDone()) {
            Thread.sleep(300);
            System.out.println("future is not done");
        }
        Integer result = future.get();
        System.out.println("future done. result: " + result);


        List<Callable<String>> callables = Arrays.asList(
                () -> "task1",
                () -> "task2",
                () -> "task3");

        executor.invokeAll(callables)
                .stream()
                .map(f -> {
                    try {
                        return f.get();
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .forEach(System.out::println);

        String results = executor.invokeAny(callables);
        System.out.println(results);

        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            int count = 0;
            while (count < 3) {
                System.out.println("Runnable " + ++count + threadName);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        stop();

        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Callable " + threadName);
            return 0;
        });

    }

    private void stop() {
        executor.shutdown();
        try {
            executor.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdownNow();
            System.out.println("shutdown forcefully");
        }
    }

}

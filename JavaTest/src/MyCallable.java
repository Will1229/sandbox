import java.util.concurrent.Callable;

/**
 * Created by Will on 2016-11-07.
 */

public class MyCallable<T> implements Callable<T> {
    private T t;

    public int getCount() {
        return count;
    }

    private int count = 0;

    @Override
    public T call() throws Exception {
        System.out.println("call " + ++count);
        return t;
    }

    public void add(T t) {
        this.t = t;
    }
}

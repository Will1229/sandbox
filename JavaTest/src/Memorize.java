import java.util.concurrent.Callable;

/**
 * Created by Will on 2016-09-15.
 */
public class Memorize<T> {
    private Callable<T> callable;
    private T cache = null;
    private boolean isCalled = false;

    public Memorize(Callable<T> c) {
        callable = c;
    }

    public synchronized T syncGet() throws Exception {
        if (!isCalled) {
            cache = callable.call();
            isCalled = true;
        }

        return cache;
    }

    public T syncBlockGet() throws Exception {
        synchronized (this) {
            if (!isCalled) {
                cache = callable.call();
                isCalled = true;
            }
        }

        return cache;
    }

    public T unsyncGet() throws Exception {
        if (!isCalled) {
            cache = callable.call();
            isCalled = true;
        }

        return cache;
    }

    public void cleanCache() {
        cache = null;
        isCalled = false;
    }


}
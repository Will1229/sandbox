import java.util.concurrent.Callable;

/**
 * Created by Will on 2016-09-15.
 */
public class Memoize<T> {
    private Callable<T> callable;
    private T result_call = null;
    private boolean isCalled = false;

    public Memoize(Callable<T> c) {
        callable = c;
    }

    public T get() throws Exception {
        synchronized (this) {
            if (!isCalled) {
                result_call = callable.call();
                isCalled = true;
            }
        }

        return result_call;
    }
}

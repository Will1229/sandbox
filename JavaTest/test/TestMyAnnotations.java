import annotations.PrintMethod;
import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by Will on 2016-11-11.
 */
public class TestMyAnnotations {

    @Test
    public void testPrintAnnotations() {
        Class<AnnotatedClass> object = AnnotatedClass.class;
        Annotation[] annotations = object.getAnnotations();

        for (Annotation a : annotations) {
            System.out.println("class annotation: " + a);
        }

        for (Method method : object.getDeclaredMethods()) {
            if (method.isAnnotationPresent(PrintMethod.class)) {
                Annotation annotation = method.getAnnotation(PrintMethod.class);
                System.out.println(method.getName() + " has annotation: " + annotation);
            }
        }
    }

    @Before
    public void before() {
        System.out.println("before");
    }

    @Before
    public void before2() {
        System.out.println("before2");
    }
}

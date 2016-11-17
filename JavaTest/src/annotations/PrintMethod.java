package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Created by Will on 2016-11-11.
 */

@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PrintMethod {
    String info() default "default method info";

    boolean print() default true;
}


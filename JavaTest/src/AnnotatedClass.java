import annotations.PrintClass;
import annotations.PrintMethod;

/**
 * Created by Will on 2016-11-11.
 */

@PrintClass(info = "AnnotatedClass")
public class AnnotatedClass {

    private String name = "default name";

    public AnnotatedClass() {
    }

    public AnnotatedClass(String name) {
        this.name = name;
    }

    @PrintMethod(print = false)
    public void method1() {
        System.out.println(name + " run method 1");

    }

    @PrintMethod(info = "method2 special info")
    public void method2() {
        System.out.println(name + " run method 2");
    }
}

import annotations.PrintClass;
import annotations.PrintMethod;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by Will on 2016-11-11.
 */
public class MyAnnotationHandler {

    public static void main(String[] args) {

        Class<?> c = null;
        try {
            c = Class.forName("AnnotatedClass");
            handle(c);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        System.out.println("Done");
    }

    private static void handle(Class<?> c) {
        if (c.isAnnotationPresent(PrintClass.class)) {
            System.out.println("Class name: " + c.getSimpleName() + " info: " + c.getAnnotation(PrintClass.class).info());
        }

        AnnotatedClass object0 = null;
        AnnotatedClass object1 = null;
        if (Objects.equals(c.getSimpleName(), AnnotatedClass.class.getSimpleName())) {
            try {
                System.out.println("All ctors: " + Arrays.toString(c.getConstructors()));
                Constructor<?> ctor0 = c.getConstructor();
                System.out.println("Ctor 0 args: " + ctor0.getName());
                Constructor<?> ctor1 = c.getConstructor(String.class);
                System.out.println("Ctor 1 args: " + ctor1.getName());
                object0 = (AnnotatedClass) ctor0.newInstance();
                object1 = (AnnotatedClass) ctor1.newInstance("object 1 name");
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }

            handleObject(c, object0);
            handleObject(c, object1);
        }


    }

    private static void handleObject(Class<?> c, AnnotatedClass object) {
        if (object != null) {
            for (Method m : c.getDeclaredMethods()) {
                if (m.isAnnotationPresent(PrintMethod.class)) {
                    System.out.println(m.getAnnotation(PrintMethod.class).info());
                    try {
                        m.invoke(object);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

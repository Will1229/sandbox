import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;


/**
 * Created by Will on 2016-11-07.
 */
public class TestJava8 {



    @Test
    public void testForEach() {
        List<Integer> l = Arrays.asList(0, 1, 2, 3);
        l.forEach(System.out::println);

        System.out.println("new instance");
        //noinspection Convert2Lambda
        l.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                if (integer > 1) {
                    System.out.println(integer);
                }
            }
        });

        System.out.println("lambda expression");
        Consumer<Integer> printBiggerThanOne = integer -> {
            if (integer > 1) {
                System.out.println(integer);
            }
        };
        l.forEach(printBiggerThanOne);

        System.out.println("method reference");
        l.forEach(TestJava8::printBiggerThanOne);

    }

    @Test
    public void testStream() {
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        //get list of unique squares
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        squaresList.stream().forEach(System.out::println);

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        //get count of empty string
        long count = strings.stream().filter(String::isEmpty).count();
        System.out.println("Count of empty string is " + count);

        strings = Arrays.asList("1", "2", "3", "4", "5");

        System.out.println("\nUsing sequential stream");
        strings.stream().map(i -> i + " ").forEach(System.out::print);
        System.out.println("\nUsing parallel stream");
        strings.parallelStream().map(i -> i + " ").forEach(System.out::print);

        strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("\nFiltered List: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("Merged String: " + mergedString);

        List<Integer> integers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics stats = integers.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("Highest number in List : " + stats.getMax());
        System.out.println("Lowest number in List : " + stats.getMin());
        System.out.println("Sum of all numbers : " + stats.getSum());
        System.out.println("Average of all numbers : " + stats.getAverage());
    }

    private static void printBiggerThanOne(Integer i) {
        if (i > 1) {
            System.out.println(i);
        }
    }

}

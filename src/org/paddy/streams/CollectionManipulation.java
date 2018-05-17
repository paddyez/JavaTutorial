package org.paddy.streams;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Map.entry;

public class CollectionManipulation {
    /** Java 9 */
    private static final Map<String, String> CUSTOMER_BY_ID = Map.of(
            "AsdfG123", "Max Mustermann",
            "QwerT456", "Stefanie Musterfrau",
            "yxcvB789", "Stefan Muster"
    );
    private static final Map<String, String> test2 = Map.ofEntries(
            entry("AsdfG123", "Max Mustermann"),
            entry("QwerT456", "Stefanie Musterfrau"),
            entry("yxcvB789", "Stefan Muster")
    );
    /** Java 8 */
    private static final Map<String, String> EXTENSION_TO_MIMETYPE =
            Arrays.stream(new String[][] {
                    { "txt", "text/plain" },
                    { "html", "text/html" },
                    { "js", "application/javascript" },
                    { "css", "text/css" },
                    { "xml", "application/xml" },
                    { "png", "image/png" },
                    { "gif", "image/gif" },
                    { "jpg", "image/jpeg" },
                    { "jpeg", "image/jpeg" },
                    { "svg", "image/svg+xml" },
            }).collect(Collectors.toMap(kv -> kv[0], kv -> kv[1]));
    private List<String> names = Arrays.asList("Patrick", "Christoph", "Andreas", exclude);
    private List<Integer> numbers;
    {
        numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }
    private static final String exclude = "Walter";
    public CollectionManipulation() {
        filterListByExclude();
        addEvenNumbers();
        reduceList();
    }
    /**
     * Different ways to filter the names in the List
     */
    private void filterListByExclude() {
        System.out.println("== Imperative style ==\n");
        for(String name : names) {
            if(!name.equals(exclude)) {
                System.out.println(name);
            }
        }
        System.out.println("== Annonymous inner classes ==\n");
        names.stream()
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String name) {
                        return !name.equals(exclude);
                    }
                })
                .forEach(new Consumer<String>() {
                    @Override
                    public void accept(String name) {
                        System.out.println(name);
                    }
                });
        System.out.println("== Functional style lambda ==\n");
        names.stream()
                .filter(name -> !name.equals(exclude))
                .forEach(name -> System.out.println(name));
        System.out.println("== Functional style lamda method reference ==\n");
        names.stream()
                .filter(CollectionManipulation::isNotWalter)
                .forEach(System.out::println);
    }
    /**
     * Add all even numbers from numbers
     */
    private void addEvenNumbers() {
        int sum = numbers.stream()
                .filter(CollectionManipulation::isEven)
                .mapToInt(i -> i)
                .sum();
        System.out.println("== Sum even numbers in list ==\n" + sum);
    }

    /**
     * Reduce numbers
     */
    private void reduceList() {
        Integer sum = numbers.stream()
                .reduce(0, (number1, number2) -> number1 + number2);
        System.out.println("== Redced list ==\n" + sum);
    }
    /**
     * Check the name is not Walter
     * @param name
     * @return true if it is not Walter
     */
    private static boolean isNotWalter(String name) {
        return !name.equals(exclude);
    }
    private static boolean isEven(Integer integer) {
        if ((integer % 2) == 0) return true;
        else return false;
    }
}
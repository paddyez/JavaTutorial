package org.paddy.streams;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
public class CollectionManipulation {
    private static final String exclude = "Walter";
    private List<String> names = Arrays.asList("Patrick", "Christoph", "Andreas", exclude);
    private List<Integer> numbers;
    {
        numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }
    public CollectionManipulation() {
        filterListByExclude();
        addEvenNumbers();
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
        System.out.println("== Functional style lambda 1 ==\n");
        names.stream()
                .filter(name -> !name.equals(exclude))
                .forEach(name -> System.out.println(name));
        System.out.println("== Functional style lamda 2 ==\n");
        names.stream()
                .filter(CollectionManipulation::isNotWalter)
                .forEach(System.out::println);
    }

    /**
     * Add all even numbers from numbers
     */
    private void addEvenNumbers() {
        int sum =numbers.stream()
                .filter(CollectionManipulation::isEven)
                .mapToInt(i -> i)
                .sum();
        System.out.println("== Sum even numbers in list ==\n" + sum);
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
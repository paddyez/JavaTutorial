package org.paddy.streams;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
public class CollectionManipulation {
    private static final String exclude = "Walter";
    private List<String> names = Arrays.asList("Patrick", "Christoph", "Andreas", exclude);
    public CollectionManipulation() {
        filterListByExclude();
    }
    private void filterListByExclude() {
        System.out.println("Paradigm style:");
        for(String name : names) {
            if(!name.equals(exclude)) {
                System.out.println(name);
            }
        }
        System.out.println("Annonymous inner classes:");
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
        System.out.println("Functional style lambda 1:");
        names.stream()
                .filter(name -> !name.equals(exclude))
                .forEach(name -> System.out.println(name));
        System.out.println("Functional style lamda 2:");
        names.stream()
                .filter(CollectionManipulation::isNotWalter)
                .forEach(System.out::println);
    }
    private static boolean isNotWalter(String name) {
        return !name.equals(exclude);
    }
}
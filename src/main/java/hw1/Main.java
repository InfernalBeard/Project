package hw1;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        CustomArrayList<String> customList = new CustomArrayList<>();

        // Тестирование метода add(int index, E element)
        customList.add(0, "Hello");
        customList.add(1, "World");
        customList.add(2, "CustomArrayList");
        System.out.println("After add(index, element): " + customList);

        // Тестирование метода addAll(Collection<? extends E> c)
        ArrayList<String> additionalItems = new ArrayList<>();
        additionalItems.add("Additional");
        additionalItems.add("Elements");
        customList.addAll(additionalItems);
        System.out.println("After addAll(collection): " + customList);

        // Тестирование метода get(int index)
        System.out.println("Element at index 1: " + customList.get(1));

        // Тестирование метода isEmpty()
        System.out.println("Is list empty? " + customList.isEmpty());

        // Тестирование метода remove(int index)
        customList.remove(2);
        System.out.println("After remove(index): " + customList);

        // Тестирование метода remove(Object o)
        customList.remove("Additional");
        System.out.println("After remove(object): " + customList);

        // Тестирование метода clear()
        customList.clear();
        System.out.println("After clear(): " + customList);
        System.out.println("Is list empty after clear? " + customList.isEmpty());

        // Повторное добавление элементов для сортировки
        customList.add(0, "Banana");
        customList.add(1, "Apple");
        customList.add(2, "Cherry");
        customList.add(3, "Date");
        customList.add(4, "Elderberry");

        // Тестирование метода sort(Comparator<? super E> c)
        customList.sort(Comparator.naturalOrder());
        System.out.println("After sort(): " + customList);
    }
}
package dev.said.streamapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;

public class TaskService {

    public void task1() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> squaredEvenNumbers1 = new ArrayList<>();
        List<Integer> squaredEvenNumbers2 = new ArrayList<>();

        System.out.println("\n === Task 1 ===");
        System.out.print("Given numbers list: " + numbers);

        //stream api
        squaredEvenNumbers1 = numbers.stream().filter(number -> number % 2 == 0).map(number -> number * number).toList();

        System.out.println("\nstream api result: Squared even numbers: " + squaredEvenNumbers1);

        //for loop
        for (Integer number : numbers) {
            if (number % 2 == 0) {
                squaredEvenNumbers2.add(number * number);
            }
        }

        System.out.println("for loop result: Squared even numbers: " + squaredEvenNumbers1);
        System.out.println(" ===============\n");
    }


    public void task2() {
//        Подсчитай, сколько строк в списке длиннее 5 символов.
        List<String> words = List.of("apple", "banana", "pear", "pineapple");
        long count = 0;

        count = words.stream().filter(word -> word.length() > 5).count();

        System.out.println("\n === Task 2 ===");
        System.out.println("stream api: " + count);

        count = 0;
        for (String word : words) {
            if (word.length() > 5) {
                count++;
            }
        }
        System.out.println("for loop: " + count);
        System.out.println(" ===============\n");
    }


    public void task3() {
//        3. Найди максимальное и минимальное число в списке с помощью Stream API.
        List<Integer> nums = List.of(10, 2, 33, 4, 25);
        long max = nums.getFirst();
        long min = nums.getFirst();

        max = nums.stream().max((o1, o2) -> o1 - o2).get();
        min = nums.stream().max((o1, o2) -> o2 - o1).get();

        System.out.println(" \n === Task 3 ===");
        System.out.println("Stream API");
        System.out.println("max: " + max);
        System.out.println("min: " + min);

        max = nums.getFirst();
        min = nums.getFirst();
        for (Integer num : nums) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        System.out.println("For loop");
        System.out.println("max: " + max);
        System.out.println("min: " + min);
        System.out.println(" ===============\n");
    }

    public void task4() {
//       4. Посчитай среднюю длину строк в списке.
        List<String> names = List.of("Alice", "Bob", "Charlie", "David");
        int average = 0;
        int count = names.size();

        System.out.println(" \n === Task 4 ===");
        System.out.println("Stream API");

        System.out.printf("average: %s\n", names.stream().mapToInt(String::length).sum() / names.stream().count());

        System.out.println("For loop");
        for (String name : names) {
            average += name.length();
        }
        System.out.println("Average: " + average / count);
        System.out.println(" ===============\n");
    }


    public void task5() {
//        5. Удали дубликаты и отсортируй строки по длине.
        List<String> input = List.of("apple", "pear", "apple", "banana", "pear");
        List<String> sortedListWithNoDuplicates = new ArrayList<>();
        System.out.println(" \n === Task 5 ===");
        sortedListWithNoDuplicates = input.stream().distinct().sorted(Comparator.comparingInt(String::length)).toList();
        System.out.println("Stream API: " + sortedListWithNoDuplicates);

        sortedListWithNoDuplicates = new ArrayList<>();
        for (String word : input) {
            if (!sortedListWithNoDuplicates.contains(word)) {
                sortedListWithNoDuplicates.add(word);
            }
        }
        sortedListWithNoDuplicates.sort(Comparator.comparingInt(String::length));
        System.out.println("For loop: " + sortedListWithNoDuplicates);
        System.out.println(" ===============\n");
    }


    public void task6() {
//        6. Преобразуй список строк в Map: ключ — строка, значение — длина.
        List<String> fruits = List.of("apple", "banana", "kiwi");
        Map<String, Integer> map = new HashMap<>();

        System.out.println(" \n === Task 6 ===");

        map = fruits.stream().collect(
            toMap(s -> s, String::length)
        );
        System.out.println("Stream API: ");
        map.forEach((key, value) -> System.out.println(key + " : " + value));

        map.clear();

        System.out.println("For loop: ");
        for (String fruit : fruits) {
            map.put(fruit, fruit.length());

            System.out.println(fruit + " : " + map.get(fruit));
        }
        System.out.println(" ===============\n");
    }

    public void task7() {
//        7. Сгруппируй имена по первой букве.
        List<String> names = List.of("Alice", "Andrew", "Bob", "Charlie", "Catherine");

        System.out.println(" \n === Task 7 ===");
        System.out.println("Stream API: ");
        Map<Character, List<String>> groupedName = names.stream().collect(groupingBy(s -> s.charAt(0)));
        groupedName.forEach((key, value) -> System.out.println(key + " : " + value));

        System.out.println("For loop: ");
        groupedName.clear();

        for (String name : names) {
            char key = name.charAt(0);
            groupedName.putIfAbsent(key, new ArrayList<>());
            groupedName.get(key).add(name);
        }
        groupedName.forEach((key, value) -> System.out.println(key + " : " + value));
        System.out.println(" ===============\n");
    }


    public void task8() {
//        8. Собери список имён в одну строку через запятую.
        List<String> names = List.of("Tom", "Jerry", "Spike");

        System.out.println(" \n === Task 8 ===");

        String namesList = names.stream().collect(joining(","));
        System.out.println(" Stream API :     " + namesList);

        namesList = names.getFirst();
        for (int i = 0; i < names.size() - 1; i++) {
            namesList = namesList.concat(", " + names.get(i + 1));
        }
        System.out.println("For loop: " + namesList);

        System.out.println(" ===============\n");
    }

    public void task9() {
//        9. Из списка предложений получить список всех слов.
        List<String> sentences = List.of("Java is cool", "Streams are powerful");
        List<String> list = new ArrayList<>();

        System.out.println(" \n === Task 9 ===");

        list = Arrays.stream(sentences.stream().collect(joining(" ")).split(" ")).toList();
        System.out.println(" Stream API :     " + list);

        ArrayList<String> list2 = new ArrayList<>();

        for (String sentence : sentences) {
            String[] s = sentence.split(" ");
            list2.addAll(Arrays.asList(s));
        }

        System.out.println("For loop: " + list2);
        System.out.println(" ===============\n");
    }


    public void task10() {
//        10. Найди самый дорогой продукт в каждой категории.
        record Product(String name, String category, double price) {}
        List<Product> products = List.of(
            new Product("Phone", "Electronics", 1200),
            new Product("TV", "Electronics", 1800),
            new Product("Apple", "Fruits", 2.5),
            new Product("Mango", "Fruits", 4.0));

        System.out.println(" \n === Task 10 ===");

        System.out.println(" ===============\n");
    }

}
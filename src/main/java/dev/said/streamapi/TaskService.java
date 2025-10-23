package dev.said.streamapi;

import java.util.ArrayList;
import java.util.List;

public class TaskService {

    public void task1() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> squaredEvenNumbers1 = new ArrayList<>();
        List<Integer> squaredEvenNumbers2 = new ArrayList<>();


        System.out.println("\n === Task 1 ===");
        System.out.print("Given numbers list: " + numbers);

        //stream api
        squaredEvenNumbers1 = numbers.stream()
                .filter(number -> number % 2 == 0)
                .map(number -> number * number)
                .toList();

        System.out.println("\nstream api result: Squared even numbers: " + squaredEvenNumbers1);

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

        words.clear();
    }

    public static void main(String[] args) {
        TaskService s = new TaskService();
        s.task1();
    }
}

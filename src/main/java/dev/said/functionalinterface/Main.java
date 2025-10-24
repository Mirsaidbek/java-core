package dev.said.functionalinterface;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Functional Interface");

        TaskService service = new TaskService();
        Scanner scanner = new Scanner(System.in);
        String choice;
        System.out.println("Stream Api");

        do {
            System.out.println("\n=== Меню задач ===");
            System.out.println("1. task1");
            System.out.println("2. task2");
            System.out.println("3. task3");
            System.out.println("4. task4");
            System.out.println("5. task5");
            System.out.println("6. task6");
            System.out.println("7. task7");
            System.out.println("8. task8");
            System.out.println("9. task9");
            System.out.println("10. task10");
            System.out.println("11 filter()");
            System.out.println("12 map()");
            System.out.println("13 forEach()");
            System.out.println("14 generate()");
            System.out.println("0. exit");
            System.out.print(">> ");

            choice = scanner.nextLine();

            switch (choice) {
                case "1" -> service.task1();
                case "2" -> service.task2();
                case "3" -> service.task3();
                case "4" -> service.task4();
                case "5" -> service.task5();
                case "6" -> service.task6();
                case "7" -> service.task7();
                case "8" -> service.task8();
                case "9" -> service.task9();
                case "10" -> service.task10();
                case "11" -> filterTest(service);
                case "12" -> mapTest(service);
                case "13" -> forEachTest(service);
                case "14" -> generateTest(service);
                case "0" -> System.exit(0);
                default -> System.out.print("What?! )");

            }

        } while (choice.equals("0"));
        scanner.close();
    }

    public static void filterTest(TaskService service) {
        List<String> words = Arrays.asList("Hello", "myWorld", "car", "GTA VI", "something");

        service.filter(words, (word) -> word.length() == 3);

    }

    public static void mapTest(TaskService service) {
        List<String> words = Arrays.asList("Java", "Scala", "GO", "GTA", "C", "C#");
        System.out.println(service.map(words, String::length));

    }

    public static void forEachTest(TaskService service) {
        List<String> words = Arrays.asList("Apple", "Samsung", "Tesla", "Rockstar", "Artel", "GM");
        service.forEach(words, System.out::println);

    }

    public static void generateTest(TaskService service) {
        List<String> generate = service.generate(() -> "Test word", 5);
        System.out.println(generate);
    }


}

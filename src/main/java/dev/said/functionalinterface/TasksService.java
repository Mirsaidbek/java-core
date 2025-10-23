package dev.said.functionalinterface;

import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TasksService {
    public void task1() {
//        1. Создай Predicate<String>, который проверяет, что строка не пуста и длиннее 3 символов.
        Predicate<String> predicate = s -> !s.isBlank() && s.length() > 3;

        System.out.println(predicate.test("Hello"));
        System.out.println(predicate.test("world"));
        System.out.println(predicate.test("car"));
    }

    public void task2() {
//        2. Создай Function<String, Integer>, возвращающую длину строки.
        Function<String, Integer> function = String::length;

        System.out.println(function.apply("Hello"));
        System.out.println(function.apply("world"));
        System.out.println(function.apply("car"));
    }

    public void task3() {
//        3. Создай Supplier<UUID>, который возвращает новый UUID при каждом вызове.
        Supplier<UUID> uuidSupplier = UUID::randomUUID;

        System.out.println(uuidSupplier.get());
        System.out.println(uuidSupplier.get());
        System.out.println(uuidSupplier.get());
    }

    public void task4() {
//        4. Создай Consumer<String>, который выводит строку в upper case.
        Consumer<String> consumer = String::toUpperCase;

        consumer.accept("Hello");
        consumer.accept("world");
        consumer.accept("car");
    }

    public void task5() {
//        5. Создай BiFunction<Integer, Integer, Integer>, которая возвращает сумму двух чисел.
        BiFunction<Integer, Integer, Integer> biFunction = (a, b) -> a + b;

        System.out.println(biFunction.apply(1, 2));
        System.out.println(biFunction.apply(-5, 2));
        System.out.println(biFunction.apply(1, 0));
    }

    public void task6() {
//        6. Function<String, String> trim и Function<String, String> toUpperCase. Объедини их
//        в одну, которая сначала обрезает пробелы, потом делает верхний регистр.
        Function<String, String> trimAndToUpperCase = s -> s.trim().toUpperCase();

        System.out.println(trimAndToUpperCase.apply("  Hell   o   "));
        System.out.println(trimAndToUpperCase.apply("   wor  ld   "));
        System.out.println(trimAndToUpperCase.apply("   -car  -   "));
    }

    public void task7() {
//        7. Один Consumer печатает строку в консоль, второй — печатает длину строки.
//            Объедини их через andThen().

        Consumer<String> textPrinter = s -> System.out.println("\ntext: " + s);
        Consumer<String> lengthCounter = s -> System.out.println("text lenght: " + s.length());

        textPrinter.andThen(lengthCounter).accept("Hello world! HAHA ");
        textPrinter.andThen(lengthCounter).accept("world is being ... What");
        textPrinter.andThen(lengthCounter).accept("flying cars");

    }

    public void task8() {
//    8. Создай Predicate<Integer> isEven и isPositive. Получи Predicate, который
//    проверяет "нечётное или отрицательное".
        Predicate<Integer> isEven = integer -> integer % 2 == 0;
        Predicate<Integer> isPositive = integer -> integer >= 0;

        System.out.println(isEven.or(isPositive).test(120));
        System.out.println(isEven.or(isPositive).test(0));
        System.out.println(isEven.or(isPositive).test(-1));
        System.out.println(isEven.or(isPositive).test(6));

    }

    public static void main(String[] args) {
        TasksService tasksService = new TasksService();
        tasksService.task8();
    }
}



/*

Functional Interface





8. Создай Predicate<Integer> isEven и isPositive. Получи Predicate, который
проверяет "нечётное или отрицательное".
9. BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b; Function<Integer,
String> toStr = x -> "Result: " + x; Используй andThen(), чтобы объединить в одну
цепочку.
10. Создай UnaryOperator<String>, который добавляет "!!!" к строке.
11. Создай метод filter(List<T> list, Predicate<T> predicate), который вручную
фильтрует коллекцию аналогично Stream API.
12. Создай метод map(List<T> list, Function<T, R> mapper) и преобразуй List<String>
в List<Integer> (длины строк).
13. Создай метод forEach(List<T> list, Consumer<T> consumer) и напечатай каждый
элемент списка.
14. Напиши метод generate(Supplier<T> supplier, int n), который создаёт список из n
элементов, полученных от supplier.

 */

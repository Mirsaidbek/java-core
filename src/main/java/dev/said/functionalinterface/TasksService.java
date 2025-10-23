package dev.said.functionalinterface;

public class TasksService {

}



/*

Functional Interface
1. Создай Predicate<String>, который проверяет, что строка не пуста и длиннее 3
символов.
2. Создай Function<String, Integer>, возвращающую длину строки.
3. Создай Supplier<UUID>, который возвращает новый UUID при каждом вызове.
4. Создай Consumer<String>, который выводит строку в upper case.
5. Создай BiFunction<Integer, Integer, Integer>, которая возвращает сумму двух
чисел.
6. Function<String, String> trim и Function<String, String> toUpperCase. Объедини их
в одну, которая сначала обрезает пробелы, потом делает верхний регистр.
7. Один Consumer печатает строку в консоль, второй — печатает длину строки.
Объедини их через andThen().
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

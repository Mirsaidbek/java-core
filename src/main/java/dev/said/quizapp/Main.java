package dev.said.quizapp;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        QuizService service = new QuizService();

        Quiz quiz = service.createQuiz();

        service.addQuestions(quiz, List.of(
            new Question("What is the keyword to inherit a class in Java?", "extends", 2),
            new Question("What collection doesn’t allow duplicates?", "set", 8),
            new Question("What is the entry point in Java?", "main", 5),
            new Question("What is 5 + 5 = ", "10", 1)
        ));

        Team team1 = new Team(1, "Code Masters");
        team1.addMember("Alice");
        team1.addMember("Bob");

        Team team2 = new Team(2, "Bug Hunters");
        team2.addMember("Charlie");
        team2.addMember("David");

        service.addTeam(quiz, team1);
        service.addTeam(quiz, team2);

        service.startQuiz(quiz);

        service.showResults(quiz);
    }
}


/*

2. Разработайте систему онлайн квиза
- создание квиза
- добавление вопросов
- добавление команд
- запуск квиза
- взаимодействие через консоль
- результаты
в текущей задаче использовать абстракцию, инкапсуляцию, наследование и
полиморфизм

*/
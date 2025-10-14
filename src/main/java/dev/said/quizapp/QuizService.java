package dev.said.quizapp;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class QuizService {

    public QuizService() {
    }

    public Quiz createQuiz() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("== Creating Quiz ==");
        Quiz quiz = new Quiz();

        System.out.print("Quiz title> ");
        quiz.setTitle(scanner.nextLine());

        System.out.print("Quiz description (you can skip it) > ");
        quiz.setDescription(scanner.nextLine());
        return quiz;
    }

    public boolean addQuestion(Quiz quiz, Question question) {
        if (Objects.isNull(quiz)) {
            System.out.println("Invalid Quiz Input");
            return false;
        }
        if (Objects.isNull(question)) {
            System.out.println("Invalid Question Input");
            return false;
        }

        return quiz.addQuestion(question);
    }

    public boolean addQuestions(Quiz quiz, List<Question> questions) {
        if (Objects.isNull(quiz)) {
            System.out.println("Invalid Quiz Input");
            return false;
        }
        if (Objects.isNull(questions)) {
            System.out.println("Invalid Questions Input");
            return false;
        }

        for (Question question : questions) {
            if (Objects.isNull(question)) {
                System.out.println("Invalid Question Input");
            }
            quiz.addQuestion(question);
        }
        return true;
    }

    public boolean addTeam(Quiz quiz, Team team) {
        if (Objects.isNull(quiz)) {
            System.out.println("Invalid Quiz Input");
            return false;
        }
        if (Objects.isNull(team)) {
            System.out.println("Invalid Team Input");
            return false;
        }
        return quiz.addTeam(team);
    }

    public void startQuiz(Quiz quiz) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("== Starting Quiz " + quiz.getTitle() + " ==");

        List<Question> questions = quiz.getQuestions();
        Collections.shuffle(questions);

        for (Question question : questions) {
            System.out.println("-------------------------------------------------");
            System.out.println("- Question [" + question.getPoints() + "pts]: " + question.getQuestion());
            System.out.println("-------------------------------------------------");

            for (Team team : quiz.getTeams()) {
                System.out.print(team.getName() + ", your answer: ");
                String teamResponse = scanner.nextLine();
                if (checkAnswer(question, teamResponse)) {
                    System.out.println("✅ Correct! You answered first and got the points!");
                    addPointsToTeam(team, question.getPoints());
                    break;
                } else {
                    System.out.println("❌ Wrong!");
                }
            }


        }
    }

    private boolean checkAnswer(Question question, String teamResponse) {
        return question.getAnswer().equalsIgnoreCase(teamResponse);
    }

    private void addPointsToTeam(Team team, int points) {
        team.increaseScore(points);
    }

    public void showResults(Quiz quiz) {
        System.out.println("\n-------------- Quiz Results --------------");
        for (Team team : quiz.getTeams()) {
            System.out.println(team.getName() + ": " + team.getScore() + " points");
        }
    }
}

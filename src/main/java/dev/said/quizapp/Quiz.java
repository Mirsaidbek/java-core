package dev.said.quizapp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Quiz {
    String title;
    String description;
    List<Question> questions = new ArrayList<>();
    List<Team> teams = new ArrayList<>();

    public boolean addQuestion(Question question) {
        return questions.add(question);
    }

    public boolean addTeam(Team team) {
        return teams.add(team);
    }


}

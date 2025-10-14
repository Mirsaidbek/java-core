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

@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Team extends Participant {
    List<String> members = new ArrayList<>();
    int score;

    public Team(int id, String name) {
        super(id, name);
    }

    public void addMember(String name) {
        members.add(name);
    }

    public void removeMember(String name) {
        members.remove(name);
    }

    public void increaseScore(int points) {
        score = score + points;
    }

    public void decreaseScore(int points) {
        if (score < points) {
            System.out.println("Something went wrong");
            return;
        }
        score = score - points;
    }

}

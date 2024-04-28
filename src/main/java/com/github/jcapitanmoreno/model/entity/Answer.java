package com.github.jcapitanmoreno.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Answer {
    private Questions questionsID;
    private Player playerID;
    private LocalTime time;
    private int playerAnswer;

    public Answer(Questions questionsID, Player playerID, LocalTime time, int playerAnswer) {
        this.questionsID = questionsID;
        this.playerID = playerID;
        this.time = time;
        this.playerAnswer = playerAnswer;
    }

    public Questions getQuestionsID() {
        return questionsID;
    }

    public void setQuestionsID(Questions questionsID) {
        this.questionsID = questionsID;
    }

    public Player getPlayerID() {
        return playerID;
    }

    public void setPlayerID(Player playerID) {
        this.playerID = playerID;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getPlayerAnswer() {
        return playerAnswer;
    }

    public void setPlayerAnswer(int playerAnswer) {
        this.playerAnswer = playerAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(questionsID, answer.questionsID) && Objects.equals(playerID, answer.playerID) && Objects.equals(time, answer.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionsID, playerID, time);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "questionsID=" + questionsID +
                ", playerID=" + playerID +
                ", time=" + time +
                ", playerAnswer=" + playerAnswer +
                '}';
    }
}

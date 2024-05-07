package com.github.jcapitanmoreno.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Answer {
    private Questions questions;
    private Player player;
    private LocalTime time;
    private int playerAnswer;

    public Answer(Questions questions, Player player, LocalTime time, int playerAnswer) {
        this.questions = questions;
        this.player = player;
        this.time = time;
        this.playerAnswer = playerAnswer;
    }

    public Questions getQuestionsID() {
        return questions;
    }

    public void setQuestionsID(Questions questionsID) {
        this.questions = questionsID;
    }

    public Player getPlayerID() {
        return player;
    }

    public void setPlayerID(Player playerID) {
        this.player = playerID;
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
        return Objects.equals(questions, answer.questions) && Objects.equals(player, answer.player) && Objects.equals(time, answer.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questions, player, time);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "questionsID=" + questions.getQuestionID() +
                ", playerID=" + player.getPlayerID() +
                ", time=" + time +
                ", playerAnswer=" + playerAnswer +
                '}';
    }
}

package com.github.jcapitanmoreno.model.entity;

import java.time.LocalTime;
import java.util.Objects;

public class Answer {
    private Question question;
    private Player player;
    private LocalTime time;
    private int playerAnswer;

    private String answerText;
    private boolean validateAnswer;

    public Answer(){

    }
    public Answer(Question question, Player player, LocalTime time, int playerAnswer, String answerText, boolean validateAnswer) {
        this.question = question;
        this.player = player;
        this.time = time;
        this.playerAnswer = playerAnswer;
        this.answerText = answerText;
        this.validateAnswer = validateAnswer;
    }

    public boolean isValidateAnswer() {
        return validateAnswer;
    }

    public void setValidateAnswer(boolean validateAnswer) {
        this.validateAnswer = validateAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Question getQuestionsID() {
        return question;
    }

    public void setQuestionsID(Question questionID) {
        this.question = questionID;
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
        return Objects.equals(question, answer.question) && Objects.equals(player, answer.player) && Objects.equals(time, answer.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, player, time);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "question=" + question +
                ", player=" + player +
                ", time=" + time +
                ", playerAnswer=" + playerAnswer +
                ", answerText='" + answerText + '\'' +
                ", validateAnswer=" + validateAnswer +
                '}';
    }
}

package com.github.jcapitanmoreno.model.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Question {
    private int questionID;
    private Game game;
    private String questionText;
    private List<Answer> possibleAnswers;
    private List<Answer> answers;

    public Question(){
        this.questionID = -1;
    }

    public Question(int questionID, Game gameID, String questionText, String[] answers) {
        this.questionID = questionID;
        this.game = game;
        this.questionText = questionText;
        this.possibleAnswers = possibleAnswers;
    }

    public List<Answer> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<Answer> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game gameID) {
        this.game = gameID;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return questionID == question.questionID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionID);
    }

    @Override
    public String toString() {
        return "Questions{" +
                "questionID=" + questionID +
                ", gameID=" + game.getGameID()+
                ", questionText='" + questionText + '\'' +
                ", answers=" + Arrays.toString(new List[]{possibleAnswers}) +
                '}';
    }


}

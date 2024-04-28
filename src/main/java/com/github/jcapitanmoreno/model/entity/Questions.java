package com.github.jcapitanmoreno.model.entity;

import java.util.ArrayList;
import java.util.Objects;

public class Questions {
    private int questionID;
    private Game gameID;
    private String questionText;
    private ArrayList<String> answers;

    public Questions(int questionID, Game gameID, String questionText, ArrayList<String> answers) {
        this.questionID = questionID;
        this.gameID = gameID;
        this.questionText = questionText;
        this.answers = answers;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public Game getGameID() {
        return gameID;
    }

    public void setGameID(Game gameID) {
        this.gameID = gameID;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Questions questions = (Questions) o;
        return questionID == questions.questionID && Objects.equals(gameID, questions.gameID) && Objects.equals(questionText, questions.questionText) && Objects.equals(answers, questions.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionID, gameID, questionText, answers);
    }

    @Override
    public String toString() {
        return "Questions{" +
                "questionID=" + questionID +
                ", gameID=" + gameID +
                ", questionText='" + questionText + '\'' +
                ", answers=" + answers +
                '}';
    }
}
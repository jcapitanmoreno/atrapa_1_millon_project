package com.github.jcapitanmoreno.model.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Game {
    private int gameID;
    private LocalDate gameDate;

    private List<Question> questions;

    public Game(){

    }

    public Game(int gameID, LocalDate gameDate) {
        this.gameID = gameID;
        this.gameDate = gameDate;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public LocalDate getGameDate() {
        return gameDate;
    }

    public void setGameDate(LocalDate gameDate) {
        this.gameDate = gameDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(gameID, game.gameID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameID);
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameID='" + gameID + '\'' +
                ", gameDate=" + gameDate +
                '}';
    }
}

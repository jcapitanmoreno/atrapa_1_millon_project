package com.github.jcapitanmoreno.model.entity;

import java.util.ArrayList;
import java.util.Objects;

public class Player {

    private int playerID;
    private String name;
    private int earnedPoints;

    public Player() {
        this.playerID = -1;

    }

    public Player(int playerID, String name, int earnedPoint) {
        this.playerID = playerID;
        this.name = name;
        this.earnedPoints = earnedPoints;

    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEarnedPoints() {
        return earnedPoints;
    }

    public void setEarnedPoints(int earnedPoints) {
        this.earnedPoints = earnedPoints;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerID, player.playerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerID);
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerID='" + playerID + '\'' +
                ", name='" + name + '\'' +
                ", earnedPoints=" + earnedPoints +
                '}';
    }
}

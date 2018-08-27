package com.example.filippocenonfolo.myapplication;

public class Rank {

    private String team;
    private int points;

    public Rank( String team, int points) {
        this.team = team;
        this.points = points;
    }

    public String getTeam() {
        return team;
    }

    public int getPoints() {
        return points;
    }
}

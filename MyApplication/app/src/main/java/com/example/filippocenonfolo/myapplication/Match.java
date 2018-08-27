package com.example.filippocenonfolo.myapplication;

public class Match {

    private int idTorneo;
    private int idGirone;
    private int idMatch;
    private String team1;
    private String team2;
    private int golTeam1;
    private int golTeam2;

    public Match(int idTorneo, int idGirone, int idMatch, String team1, String team2, int golTeam1, int golTeam2) {
        this.idTorneo = idTorneo;
        this.idGirone = idGirone;
        this.idMatch = idMatch;
        this.team1 = team1;
        this.team2 = team2;
        this.golTeam1 = golTeam1;
        this.golTeam2 = golTeam2;
    }

    public int getIdTorneo() {
        return idTorneo;
    }

    public int getIdGirone() {
        return idGirone;
    }

    public int getIdMatch() {
        return idMatch;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public int getGolTeam1() {
        return golTeam1;
    }

    public int getGolTeam2() {
        return golTeam2;
    }


}

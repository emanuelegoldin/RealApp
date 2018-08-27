package com.example.filippocenonfolo.myapplication;

public class ListItem {

    private int id;
    private int idCreator;
    private int complete;
    private int format;
    private int phase;
    private String name;
    private String place;

    public ListItem(int id, int idCreator, int complete, int format, int phase, String name, String place) {
        this.id = id;
        this.idCreator = idCreator;
        this.complete = complete;
        this.format = format;
        this.phase = phase;
        this.name = name;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public int getIdCreator() {
        return idCreator;
    }

    public int getComplete() {
        return complete;
    }

    public int getFormat() {
        return format;
    }

    public int getPhase() {
        return phase;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

}

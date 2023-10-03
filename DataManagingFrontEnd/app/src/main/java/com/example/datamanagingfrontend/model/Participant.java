package com.example.datamanagingfrontend.model;

public class Participant {

    private int id;
    private String team;
    private int solved;
    private int time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getSolved() {
        return solved;
    }

    public void setSolved(int solved) {
        this.solved = solved;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", team='" + team + '\'' +
                ", solved=" + solved +
                ", time=" + time +
                '}';
    }
}

package com.spo.workplace.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class RoomAssignment {

    private int senior;
    private int junior;

    protected RoomAssignment() {}

    public RoomAssignment(int senior, int junior) {
        this.senior = senior;
        this.junior = junior;
    }

    public int getSenior() {
        return senior;
    }

    public void setSenior(int senior) {
        this.senior = senior;
    }

    public int getJunior() {
        return junior;
    }

    public void setJunior(int junior) {
        this.junior = junior;
    }
}

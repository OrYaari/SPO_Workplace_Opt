package com.spo.workplace.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect
public class OptimizationRequest {

    private List<Integer> rooms;
    private int senior;
    private int junior;

    protected OptimizationRequest() {
    }

    public OptimizationRequest(List<Integer> rooms, int senior, int junior) {
        this.rooms = rooms;
        this.senior = senior;
        this.junior = junior;
    }

    public List<Integer> getRooms() {
        return rooms;
    }

    public int getSenior() {
        return senior;
    }

    public int getJunior() {
        return junior;
    }

    public void setRooms(List<Integer> rooms) {
        this.rooms = rooms;
    }

    public void setSenior(int senior) {
        this.senior = senior;
    }

    public void setJunior(int junior) {
        this.junior = junior;
    }
}
package com.spo.workplace.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@JsonAutoDetect
public class OptimizationRequest {

    @NotNull
    @Size(min = 1, max = 100)
    private List<Integer> rooms;

    @NotNull
    @Min(2)
    private int senior;

    @NotNull
    @Min(1)
    private int junior;

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
}
package com.spo.workplace.calculator;

public class CapacityCalculator {

    private int seniorCapacity;
    private int juniorCapacity;

    public CapacityCalculator(int seniorCapacity, int juniorCapacity) {
        this.seniorCapacity = seniorCapacity;
        this.juniorCapacity = juniorCapacity;
    }

    public int calculateMaxSeniors(int roomCapacity) {
        int maxSeniorAmount = roomCapacity / seniorCapacity;
        if (roomCapacity % seniorCapacity > 0) {
            maxSeniorAmount++;
        }

        return maxSeniorAmount;
    }

    public int calculateMaxJuniorsNeeded(int roomCapacity, int seniorAmount) {
        int remainingCapacity = roomCapacity - seniorCapacity * seniorAmount;
        int maxJuniorAmount = remainingCapacity / juniorCapacity;
        if (remainingCapacity % juniorCapacity > 0) {
            maxJuniorAmount++;
        }

        return maxJuniorAmount;
    }

    public int calculateWorkCapacity(int seniorAmount, int juniorAmount) {
        return seniorCapacity * seniorAmount + juniorCapacity * juniorAmount;
    }
}

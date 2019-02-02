package com.spo.workplace.calculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CapacityCalculatorTest {

    @Test
    public void calculateMaxSeniors_givenRoom40Senior10_expects4() {
        CapacityCalculator capacityCalculator = new CapacityCalculator(10, 1);
        assertEquals(4, capacityCalculator.calculateMaxSeniors(40));
    }

    @Test
    public void calculateMaxSeniors_givenRoom41Senior10_expects5() {
        CapacityCalculator capacityCalculator = new CapacityCalculator(10, 1);
        assertEquals(5, capacityCalculator.calculateMaxSeniors(41));
    }

    @Test
    public void calculateMaxSeniors_givenRoom9Senior10_expects1() {
        CapacityCalculator capacityCalculator = new CapacityCalculator(10, 1);
        assertEquals(1, capacityCalculator.calculateMaxSeniors(9));
    }

    @Test
    public void calculateMaxSeniors_givenRoom49Senior6_expects9() {
        CapacityCalculator capacityCalculator = new CapacityCalculator(6, 1);
        assertEquals(9, capacityCalculator.calculateMaxSeniors(49));
    }

    @Test
    public void calculateMaxJuniors_givenRoom49Junior6_expects9() {
        CapacityCalculator capacityCalculator = new CapacityCalculator(15, 6);
        assertEquals(6, capacityCalculator.calculateMaxJuniorsNeeded(49, 1));
    }

    @Test
    public void calculateWorkCapacity_seniorCap5Amount3JuniorCap3Amount2_expects21() {
        CapacityCalculator capacityCalculator = new CapacityCalculator(5, 3);
        assertEquals(21, capacityCalculator.calculateWorkCapacity(3,2));
    }
}
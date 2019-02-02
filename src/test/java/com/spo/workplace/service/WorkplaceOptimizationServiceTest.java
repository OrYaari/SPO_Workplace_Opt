package com.spo.workplace.service;

import com.spo.workplace.model.OptimizationRequest;
import com.spo.workplace.model.RoomAssignment;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WorkplaceOptimizationServiceTest {

    private WorkplaceOptimizationService workplaceOptimizationService;

    @Before
    public void setUp() {
        workplaceOptimizationService = new WorkplaceOptimizationService();
    }

    @Test
    public void optimizeRooms_example1() {
        OptimizationRequest optimizationRequest = new OptimizationRequest(Arrays.asList(35, 21, 17), 10 , 6);
        List<RoomAssignment> roomAssignments = workplaceOptimizationService.optimizeRooms(optimizationRequest);
        assertEquals(3, roomAssignments.size());
        assertEquals(3, roomAssignments.get(0).getSenior());
        assertEquals(1, roomAssignments.get(0).getJunior());
        assertEquals(1, roomAssignments.get(1).getSenior());
        assertEquals(2, roomAssignments.get(1).getJunior());
        assertEquals(2, roomAssignments.get(2).getSenior());
        assertEquals(0, roomAssignments.get(2).getJunior());

    }

    @Test
    public void optimizeRooms_example2() {
        OptimizationRequest optimizationRequest = new OptimizationRequest(Arrays.asList(24, 28), 11 , 6);
        List<RoomAssignment> roomAssignments = workplaceOptimizationService.optimizeRooms(optimizationRequest);
        assertEquals(2, roomAssignments.size());
        assertEquals(2, roomAssignments.get(0).getSenior());
        assertEquals(1, roomAssignments.get(0).getJunior());
        assertEquals(2, roomAssignments.get(1).getSenior());
        assertEquals(1, roomAssignments.get(1).getJunior());

    }

    @Test
    public void optimizeRooms_givenSeniorOverRoomCap_getOneSeniorAndNoJuniors() {
        OptimizationRequest optimizationRequest = new OptimizationRequest(Arrays.asList(6, 4), 8 , 2);
        List<RoomAssignment> roomAssignments = workplaceOptimizationService.optimizeRooms(optimizationRequest);
        assertEquals(2, roomAssignments.size());
        assertEquals(1, roomAssignments.get(0).getSenior());
        assertEquals(0, roomAssignments.get(0).getJunior());
        assertEquals(1, roomAssignments.get(1).getSenior());
        assertEquals(0, roomAssignments.get(1).getJunior());
    }

    // This test is used to make sure we have the least amount of workers at all time (after considering the over capacity)
    @Test
    public void optimizeRooms_givenJuniorCapOne_juniorsWillOnlyFillSeniorsRemainingCap() {
        OptimizationRequest optimizationRequest = new OptimizationRequest(Arrays.asList(16, 14, 9, 23), 8 , 1);
        List<RoomAssignment> roomAssignments = workplaceOptimizationService.optimizeRooms(optimizationRequest);
        assertEquals(4, roomAssignments.size());
        assertEquals(2, roomAssignments.get(0).getSenior());
        assertEquals(0, roomAssignments.get(0).getJunior());
        assertEquals(1, roomAssignments.get(1).getSenior());
        assertEquals(6, roomAssignments.get(1).getJunior());
        assertEquals(1, roomAssignments.get(2).getSenior());
        assertEquals(1, roomAssignments.get(2).getJunior());
        assertEquals(2, roomAssignments.get(3).getSenior());
        assertEquals(7, roomAssignments.get(3).getJunior());
    }
}
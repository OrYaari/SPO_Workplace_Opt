package com.spo.workplace.service;

import com.spo.workplace.calculator.CapacityCalculator;
import com.spo.workplace.model.OptimizationRequest;
import com.spo.workplace.model.RoomAssignment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkplaceOptimizationService {

    public List<RoomAssignment> optimizeRooms(OptimizationRequest optimizationRequest) {
        List<RoomAssignment> roomAssignments = new ArrayList<>();
        CapacityCalculator capacityCalculator = new CapacityCalculator(optimizationRequest.getSenior(), optimizationRequest.getJunior());
        for (Integer room : optimizationRequest.getRooms()) {
            roomAssignments.add(getRoomAssignment(room, capacityCalculator));
        }

        return roomAssignments;
    }

    private RoomAssignment getRoomAssignment(int room, CapacityCalculator capacityCalculator) {
        int maxSeniorAmount = capacityCalculator.calculateMaxSeniors(room);
        RoomAssignment roomAssignment = new RoomAssignment(maxSeniorAmount, 0);
        int bestOverCapacity = capacityCalculator.calculateWorkCapacity(maxSeniorAmount, 0) - room;
        if (bestOverCapacity != 0) {
            // We go from the end to ensure we have the least amount of workers needed
            // For example - if junior capacity is 1 to not just take one senior and fill the rest with juniors
            for (int seniorAmount = maxSeniorAmount - 1; seniorAmount > 0; seniorAmount--) {
                int juniorAmount = capacityCalculator.calculateMaxJuniorsNeeded(room, seniorAmount);
                int roomCapacity = capacityCalculator.calculateWorkCapacity(seniorAmount, juniorAmount);
                int currentOverCapacity = roomCapacity - room;
                if (currentOverCapacity < bestOverCapacity) {
                    bestOverCapacity = currentOverCapacity;
                    roomAssignment.setSenior(seniorAmount);
                    roomAssignment.setJunior(juniorAmount);
                    // If over capacity is 0 we reached our goal
                    if (currentOverCapacity ==  0) {
                        break;
                    }
                }
            }
        }

        return roomAssignment;
    }
}

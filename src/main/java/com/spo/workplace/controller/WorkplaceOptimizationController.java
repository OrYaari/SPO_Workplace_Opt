package com.spo.workplace.controller;

import com.spo.workplace.model.OptimizationRequest;
import com.spo.workplace.model.RoomAssignment;
import com.spo.workplace.service.WorkplaceOptimizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/spo/workplace/optimization")
public class WorkplaceOptimizationController {

    @Autowired
    private WorkplaceOptimizationService workplaceOptimizationService;

    @PostMapping(value = "/optimize", produces = "application/json")
    @ResponseBody
    public List<RoomAssignment> optimizeRoomAssignment(@RequestBody OptimizationRequest optimizationRequest) {
        return workplaceOptimizationService.optimizeRooms(optimizationRequest);
    }
}

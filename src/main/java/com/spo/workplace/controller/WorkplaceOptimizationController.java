package com.spo.workplace.controller;

import com.spo.workplace.model.OptimizationRequest;
import com.spo.workplace.model.RoomAssignment;
import com.spo.workplace.service.WorkplaceOptimizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/spo/workplace")
public class WorkplaceOptimizationController {

    @Autowired
    private WorkplaceOptimizationService workplaceOptimizationService;

    @Autowired
    @Qualifier("requestValidator")
    private Validator validator;

    @InitBinder
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @PostMapping(value = "/optimize", produces = "application/json")
    @ResponseBody
    public List<RoomAssignment> optimizeRoomAssignment(@Valid @RequestBody OptimizationRequest optimizationRequest) {
        return workplaceOptimizationService.optimizeRooms(optimizationRequest);
    }
}

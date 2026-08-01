package com.rr.trainseatbooking.controller.admin;

import com.rr.trainseatbooking.dto.request.TrainRequest;
import com.rr.trainseatbooking.entity.Train;
import com.rr.trainseatbooking.service.TrainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/trains")
@RequiredArgsConstructor
@Tag(name = "Train Management", description = "Admin APIs for managing trains")
public class TrainController {

    private final TrainService trainService;

    @PostMapping
    @Operation(summary = "Create a new train")
    public ResponseEntity<Train> createTrain(@RequestBody TrainRequest request) {
        return ResponseEntity.ok(trainService.createTrain(request));
    }

    @GetMapping
    @Operation(summary = "Get all trains")
    public ResponseEntity<List<Train>> getAllTrains() {
        return ResponseEntity.ok(trainService.getAllTrains());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get train by ID")
    public ResponseEntity<Train> getTrainById(@PathVariable Long id) {
        return ResponseEntity.ok(trainService.getTrainById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a train")
    public ResponseEntity<Train> updateTrain(
            @PathVariable Long id,
            @RequestBody TrainRequest request) {

        return ResponseEntity.ok(trainService.updateTrain(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a train")
    public ResponseEntity<Void> deleteTrain(@PathVariable Long id) {

        trainService.deleteTrain(id);

        return ResponseEntity.noContent().build();
    }
}
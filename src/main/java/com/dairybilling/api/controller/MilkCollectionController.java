package com.dairybilling.api.controller;

import com.dairybilling.api.entity.MilkCollection;
import com.dairybilling.api.service.MilkCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/collection")
@RequiredArgsConstructor
public class MilkCollectionController {

    private final MilkCollectionService collectionService;

    // Record a new milk drop-off for a specific customer
    @PostMapping("/customer/{customerId}")
    public ResponseEntity<MilkCollection> recordCollection(
            @PathVariable Long customerId,
            @RequestBody MilkCollection request) {
        
        MilkCollection savedCollection = collectionService.recordCollection(request, customerId);
        return ResponseEntity.ok(savedCollection);
    }
}
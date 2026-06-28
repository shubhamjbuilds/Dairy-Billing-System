package com.dairybilling.api.controller;

import com.dairybilling.api.entity.DairyGroup;
import com.dairybilling.api.service.DairyGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dairy-group")
@RequiredArgsConstructor
public class DairyGroupController {

    private final DairyGroupService dairyGroupService;

    @PostMapping
    public ResponseEntity<DairyGroup> createDairyGroup(@RequestBody DairyGroup request) {
        return ResponseEntity.ok(dairyGroupService.createDairyGroup(request));
    }

    @GetMapping
    public ResponseEntity<List<DairyGroup>> getAllDairyGroups() {
        return ResponseEntity.ok(dairyGroupService.getAllDairyGroups());
    }
}
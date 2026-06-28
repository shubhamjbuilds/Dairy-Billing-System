package com.dairybilling.api.controller;

import com.dairybilling.api.entity.Branch;
import com.dairybilling.api.entity.BranchStaging;
import com.dairybilling.api.entity.Customer;
import com.dairybilling.api.entity.CustomerStaging;
import com.dairybilling.api.service.ApprovalService;
import com.dairybilling.api.service.OnboardingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/onboarding")
@RequiredArgsConstructor
public class OnboardingController {

    private final OnboardingService onboardingService;
    private final ApprovalService approvalService;

    // --- 1. SUBMIT TO STAGING ---

    @PostMapping("/branch")
    public ResponseEntity<BranchStaging> onboardBranch(@RequestBody BranchStaging request) {
        return ResponseEntity.ok(onboardingService.onboardBranch(request));
    }

    @PostMapping("/customer")
    public ResponseEntity<CustomerStaging> onboardCustomer(@RequestBody CustomerStaging request) {
        return ResponseEntity.ok(onboardingService.onboardCustomer(request));
    }

    // --- 2. APPROVE FROM STAGING ---

    @PostMapping("/branch/{stagingId}/approve")
    public ResponseEntity<Branch> approveBranch(@PathVariable Long stagingId) {
        return ResponseEntity.ok(approvalService.approveBranch(stagingId));
    }

    @PostMapping("/customer/{stagingId}/approve")
    public ResponseEntity<Customer> approveCustomer(@PathVariable Long stagingId) {
        return ResponseEntity.ok(approvalService.approveCustomer(stagingId));
    }
}
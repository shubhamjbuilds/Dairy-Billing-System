package com.dairybilling.api.service;

import com.dairybilling.api.entity.BranchStaging;
import com.dairybilling.api.entity.CustomerStaging;
import com.dairybilling.api.enums.ApprovalStatus;
import com.dairybilling.api.repository.BranchStagingRepository;
import com.dairybilling.api.repository.CustomerStagingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OnboardingService {

    private final BranchStagingRepository branchStagingRepo;
    private final CustomerStagingRepository customerStagingRepo;

    // 1. Onboard a new Branch (Sub-parent)
    public BranchStaging onboardBranch(BranchStaging request) {
        // Force the status to PENDING, regardless of what the user sent
        request.setStatus(ApprovalStatus.PENDING);
        return branchStagingRepo.save(request);
    }

    // 2. Onboard a new Customer
    public CustomerStaging onboardCustomer(CustomerStaging request) {
        request.setStatus(ApprovalStatus.PENDING);
        return customerStagingRepo.save(request);
    }
}
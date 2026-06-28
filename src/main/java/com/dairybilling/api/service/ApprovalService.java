package com.dairybilling.api.service;

import com.dairybilling.api.entity.*;
import com.dairybilling.api.enums.ApprovalStatus;
import com.dairybilling.api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApprovalService {

    private final BranchStagingRepository branchStagingRepo;
    private final BranchRepository branchRepo;
    private final DairyGroupRepository dairyGroupRepo;

    private final CustomerStagingRepository customerStagingRepo;
    private final CustomerRepository customerRepo;

    // 1. APPROVE A BRANCH
    @Transactional
    public Branch approveBranch(Long stagingId) {
        // Find the pending branch
        BranchStaging staging = branchStagingRepo.findById(stagingId)
                .orElseThrow(() -> new RuntimeException("Staging record not found"));

        if (staging.getStatus() != ApprovalStatus.PENDING) {
            throw new RuntimeException("This record is already processed.");
        }

        // Verify the Parent (Dairy Group) actually exists
        DairyGroup parentGroup = dairyGroupRepo.findById(staging.getDairyGroupId())
                .orElseThrow(() -> new RuntimeException("Invalid Dairy Group ID. Parent does not exist."));

        // Map data to the Main Branch table
        Branch officialBranch = new Branch();
        officialBranch.setBranchName(staging.getBranchName());
        officialBranch.setDairyGroup(parentGroup);

        // Save to Main Table
        Branch savedBranch = branchRepo.save(officialBranch);

        // Update Staging Status
        staging.setStatus(ApprovalStatus.APPROVED);
        branchStagingRepo.save(staging);

        return savedBranch;
    }

    // 2. APPROVE A CUSTOMER
    @Transactional
    public Customer approveCustomer(Long stagingId) {
        // Find the pending customer
        CustomerStaging staging = customerStagingRepo.findById(stagingId)
                .orElseThrow(() -> new RuntimeException("Staging record not found"));

        if (staging.getStatus() != ApprovalStatus.PENDING) {
            throw new RuntimeException("This record is already processed.");
        }

        // Verify the Sub-Parent (Branch) actually exists
        Branch parentBranch = branchRepo.findById(staging.getBranchId())
                .orElseThrow(() -> new RuntimeException("Invalid Branch ID. Parent does not exist."));

        // Map data to the Main Customer table
        Customer officialCustomer = new Customer();
        officialCustomer.setName(staging.getName());
        officialCustomer.setContactNumber(staging.getContactNumber());
        officialCustomer.setBranch(parentBranch);
        officialCustomer.setActive(true);

        // Save to Main Table
        Customer savedCustomer = customerRepo.save(officialCustomer);

        // Update Staging Status
        staging.setStatus(ApprovalStatus.APPROVED);
        customerStagingRepo.save(staging);

        return savedCustomer;
    }
}
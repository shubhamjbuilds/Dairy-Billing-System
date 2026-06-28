package com.dairybilling.api.repository;

import com.dairybilling.api.entity.CustomerStaging;
import com.dairybilling.api.enums.ApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerStagingRepository extends JpaRepository<CustomerStaging, Long> {
    List<CustomerStaging> findByBranchIdAndStatus(Long branchId, ApprovalStatus status);
}
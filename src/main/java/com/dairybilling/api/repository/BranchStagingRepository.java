package com.dairybilling.api.repository;

import com.dairybilling.api.entity.BranchStaging;
import com.dairybilling.api.enums.ApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BranchStagingRepository extends JpaRepository<BranchStaging, Long> {
    List<BranchStaging> findByStatus(ApprovalStatus status);
}
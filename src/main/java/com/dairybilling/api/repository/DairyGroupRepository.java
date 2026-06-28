package com.dairybilling.api.repository;

import com.dairybilling.api.entity.DairyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DairyGroupRepository extends JpaRepository<DairyGroup, Long> {
}
package com.dairybilling.api.repository;

import com.dairybilling.api.entity.MilkCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MilkCollectionRepository extends JpaRepository<MilkCollection, Long> {
    
    // Spring magically writes the SQL to find records between two dates!
    List<MilkCollection> findByCustomerIdAndCollectionDateBetween(Long customerId, LocalDate startDate, LocalDate endDate);
}
package com.dairybilling.api.service;

import com.dairybilling.api.entity.DairyGroup;
import com.dairybilling.api.repository.DairyGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DairyGroupService {

    private final DairyGroupRepository dairyGroupRepo;

    // Create a new Dairy Group
    public DairyGroup createDairyGroup(DairyGroup request) {
        return dairyGroupRepo.save(request);
    }

    // Get a list of all Dairy Groups
    public List<DairyGroup> getAllDairyGroups() {
        return dairyGroupRepo.findAll();
    }
}
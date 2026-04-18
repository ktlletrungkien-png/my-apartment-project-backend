package org.example.service;

import org.example.entity.AssignmentBuildingEntity;
import org.example.entity.BuildingEntity;
import org.example.entity.UserEntity;
import org.example.model.AssignmentRequest;
import org.example.model.StaffDTO;
import org.example.repository.AssignmentBuildingRepository;
import org.example.repository.BuildingRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    public List<StaffDTO> getAllStaff(){
        return userRepository.findAllStaff().stream().map(item -> {
            StaffDTO dto = new StaffDTO();
            dto.setId(item.getId());
            dto.setFullName(item.getFullname());
            return dto;
        }).collect(Collectors.toList());
    }
    @Transactional
    public void assignStaffToBuilding(AssignmentRequest request) {
        buildingRepository.deleteAssignmentByBuildingId(request.getBuildingId());

        BuildingEntity building = buildingRepository.findById(request.getBuildingId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tòa nhà"));

        if (request.getStaffIds() == null || request.getStaffIds().isEmpty()) {
            return;
        }

        for (Long staffId : request.getStaffIds()) {
            UserEntity user = userRepository.findById(staffId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));

            AssignmentBuildingEntity assignment = new AssignmentBuildingEntity();
            assignment.setBuilding(building);
            assignment.setUser(user);

            assignmentBuildingRepository.save(assignment);
        }
    }
    public List<Long> getAssignedStaffIds(Long buildingId) {
        return buildingRepository.findAssignedStaffIds(buildingId);
    }
}

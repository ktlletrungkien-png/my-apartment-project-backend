package org.example.service;

import org.example.entity.AssignmentBuildingEntity;
import org.example.entity.BuildingEntity;
import org.example.entity.RoleEntity;
import org.example.entity.UserEntity;
import org.example.model.AssignmentRequest;
import org.example.model.StaffDTO;
import org.example.model.UserDTO;
import org.example.repository.AssignmentBuildingRepository;
import org.example.repository.BuildingRepository;
import org.example.repository.RoleRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    public List<UserDTO> getAllUser(){
        List<UserDTO> dto = new ArrayList<>();
        List<UserEntity> entity = userRepository.findAll();
        for (UserEntity user : entity){
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setFullname(user.getFullname());
            Set<RoleEntity> roles = user.getRoles();

            String userrole = "";
            for (RoleEntity x : roles){
                userrole = x.getCode();
            }

            userDTO.setRole(userrole);
            userDTO.setPhone(user.getPhone());
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword(user.getPassword());
            userDTO.setStatus(user.getStatus());
            userDTO.setUsername(user.getUsername());
            dto.add(userDTO);
        }
        return dto;
    }
    @Autowired
    private RoleRepository roleRepository;
    public void saveUser(UserDTO dto){
        UserEntity user = new UserEntity();
        user.setPassword(dto.getPassword());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setFullname(dto.getFullname());
        user.setPhone(dto.getPhone());
        user.setStatus(1);

        RoleEntity roleEnt = roleRepository.findByCode(dto.getRole()).orElseThrow(()->new RuntimeException("Khong tim thay role"));
        user.setRoles(Set.of(roleEnt));

        userRepository.save(user);
    }
}

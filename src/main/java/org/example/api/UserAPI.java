package org.example.api;

import org.example.model.AssignmentRequest;
import org.example.model.StaffDTO;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")

public class UserAPI {
    @Autowired
    private UserService userService;

    @GetMapping("/staff")
    public List<StaffDTO> getAllStaff(){
        return userService.getAllStaff();
    }
    @PostMapping("/assign-building")
    public String assignBuilding(@RequestBody AssignmentRequest request) {
        try {
            userService.assignStaffToBuilding(request);
            return "Phân công nhân viên thành công";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    @GetMapping("/assigned-staff/{buildingId}")
    public List<Long> getAssignedStaff(@PathVariable Long buildingId) {
        return userService.getAssignedStaffIds(buildingId);
    }
}

package org.example.api;

import org.example.model.AssignmentRequest;
import org.example.model.CustomerAssignmentRequest;
import org.example.model.CustomerDTO;
import org.example.model.StaffDTO;
import org.example.model.UserDTO;
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

    @GetMapping("/customers")
    public List<CustomerDTO> getAllCustomers(){
        return userService.getAllCustomers();
    }

    @PostMapping("/assign-customer")
    public String assignCustomer(@RequestBody CustomerAssignmentRequest request) {
        try {
            userService.assignStaffToCustomer(request);
            return "Phan cong khach hang thanh cong";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/assigned-staff-by-customer/{customerId}")
    public List<Long> getAssignedStaffByCustomer(@PathVariable Long customerId) {
        return userService.getAssignedStaffIdsByCustomer(customerId);
    }

    @GetMapping()
    public List<UserDTO> getAllUser(){
        return userService.getAllUser();
    }
    @PostMapping()
    public String saveUser(@RequestBody UserDTO user){
        try{
            userService.saveUser(user);
            return "Tao tai khoan moi thanh cong";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
}

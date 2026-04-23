package org.example.api;

import org.example.entity.RentTypeEntity;
import org.example.model.RentTypeDTO;
import org.example.repository.RentTypeRepository;
import org.example.service.RentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/renttype")
public class RentTypeAPI {
    @Autowired
    private RentTypeService rentTypeService;
    @Autowired
    private RentTypeRepository rentTypeRepository;
    @GetMapping
    public List<RentTypeDTO> listEntity(){
        return rentTypeService.listAll();
    }
    @PostMapping
    public String addRentType(@RequestBody RentTypeEntity ren){
        try{
            rentTypeRepository.save(ren);
            return "Them rent type thanh cong";
        }
        catch(Exception e){
            return e.getMessage();
        }
    }
    @PutMapping("/{id}")
    public String updateRentType(@PathVariable Long id, @RequestBody RentTypeEntity ren){
        try{
            RentTypeEntity old = rentTypeRepository.findById(id).orElseThrow(()->new RuntimeException("Khong tim thay"));
            old.setName(ren.getName());
            old.setCode(ren.getCode());
            rentTypeRepository.save(old);
            return "Cap nhat thanh cong";
        }
        catch(Exception e){
            return e.getMessage();
        }
    }
    @GetMapping("/{id}")
    public RentTypeDTO loadRentType(@PathVariable Long id){
        return rentTypeService.findById(id);
    }
}

package org.example.api;

import org.example.model.RentTypeDTO;
import org.example.repository.RentTypeRepository;
import org.example.service.RentTypeService;
import org.hibernate.annotations.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/renttype")
public class RentTypeAPI {
    @Autowired
    private RentTypeService rentTypeService;

    @GetMapping
    public List<RentTypeDTO> listEntity(){
        return rentTypeService.listAll();
    }
}

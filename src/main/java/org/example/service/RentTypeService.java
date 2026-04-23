package org.example.service;

import org.example.entity.RentTypeEntity;
import org.example.model.RentTypeDTO;
import org.example.repository.RentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentTypeService {
    @Autowired
    private RentTypeRepository rentTypeRepository;
    public List<RentTypeDTO> listAll(){
        List<RentTypeDTO> listRentType = new ArrayList<>();
        List<RentTypeEntity> listEntity = rentTypeRepository.findAll();
        for (RentTypeEntity rte : listEntity){
            RentTypeDTO dto = new RentTypeDTO();
            dto.setId(rte.getId());
            dto.setCode(rte.getCode());
            dto.setName(rte.getName());
            listRentType.add(dto);
        }
        return listRentType;
    }
}

package org.example.service;

import org.example.entity.BuildingEntity;
import org.example.entity.BuildingRentTypeEntity;
import org.example.entity.RentAreaEntity;
import org.example.entity.RentTypeEntity;
import org.example.model.BuildingDTO;
import org.example.model.BuildingRentTypeRequest;
import org.example.repository.BuildingRentTypeRepository;
import org.example.repository.BuildingRepository;
import org.example.repository.RentAreaRepository;
import org.example.repository.RentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingRentTypeRepository buildingRentTypeRepository;

    @Autowired
    private RentTypeRepository rentTypeRepository;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Transactional(readOnly = true)
    public List<BuildingDTO> findAll(Map<String, String> params) {
        String name = params.get("name");
        String address = params.get("address");
        String staffName = params.get("staffName");

        Integer rentArea = (params.get("rentArea") != null && !params.get("rentArea").isEmpty())
                ? Integer.parseInt(params.get("rentArea")) : null;

        Integer rentPriceFrom = (params.get("rentPriceFrom") != null && !params.get("rentPriceFrom").isEmpty())
                ? Integer.parseInt(params.get("rentPriceFrom")) : null;

        Integer rentPriceTo = (params.get("rentPriceTo") != null && !params.get("rentPriceTo").isEmpty())
                ? Integer.parseInt(params.get("rentPriceTo")) : null;

        List<BuildingEntity> entities = buildingRepository.searchBuilding(
                name, rentArea, address, staffName, rentPriceFrom, rentPriceTo
        );

        return entities.stream().map(item -> {
            BuildingDTO dto = new BuildingDTO();
            dto.setId(item.getId());
            dto.setName(item.getName());

            String street = (item.getStreet() != null) ? item.getStreet() : "";
            String ward = (item.getWard() != null) ? item.getWard() : "";
            dto.setStreet(item.getStreet());
            dto.setWard(item.getWard());
            dto.setDistrictId(item.getDistrictId());
            String fullAddress = street + (!street.isEmpty() && !ward.isEmpty() ? ", " : "") + ward;
            dto.setAddress(fullAddress);
            dto.setManagerPhoneNumber(item.getManagerPhoneNumber());
            dto.setFloorArea(item.getFloorArea());
            dto.setNumberOfBasement(item.getNumberOfBasement());
            dto.setRentPrice(item.getRentPrice());
            dto.setManagerName(item.getManagerName());
            dto.setRentPriceDescription(item.getRentPriceDescription());

            if (item.getRentAreas() != null && !item.getRentAreas().isEmpty()) {
                String areaStrings = item.getRentAreas().stream()
                        .map(area -> area.getValue().toString())
                        .collect(Collectors.joining(", "));
                dto.setRentAreas(areaStrings);
            }

            if (item.getAssignmentBuildings() != null) {
                String staffName2 = item.getAssignmentBuildings().stream()
                        .map(assignment -> assignment.getUser().getFullname())
                        .collect(Collectors.joining(", "));
                dto.setStaffName(staffName2);
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BuildingDTO findById(Long id) {
        BuildingEntity item = buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay toa nha!"));

        BuildingDTO dto = new BuildingDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setStreet(item.getStreet());
        dto.setWard(item.getWard());
        dto.setDistrictId(item.getDistrictId());

        String street = (item.getStreet() != null) ? item.getStreet() : "";
        String ward = (item.getWard() != null) ? item.getWard() : "";
        dto.setAddress(street + (!street.isEmpty() && !ward.isEmpty() ? ", " : "") + ward);

        dto.setRentPrice(item.getRentPrice());
        dto.setRentPriceDescription(item.getRentPriceDescription());
        dto.setNumberOfBasement(item.getNumberOfBasement());
        dto.setFloorArea(item.getFloorArea());
        dto.setDeposit(item.getDeposit());
        dto.setPayment(item.getPayment());
        dto.setRentTime(item.getRentTime());
        dto.setBrokerageFee(item.getBrokerageFee());
        dto.setNote(item.getNote());
        dto.setManagerName(item.getManagerName());
        dto.setManagerPhoneNumber(item.getManagerPhoneNumber());
        dto.setImage(item.getImage());

        if (item.getRentAreas() != null) {
            String areas = item.getRentAreas().stream()
                    .map(ra -> ra.getValue().toString())
                    .collect(Collectors.joining(", "));
            dto.setRentAreas(areas);
        }

        return dto;
    }
    @Transactional(readOnly = true)
    public List<Long> getAssigmentRentTypeIds(Long buildingId){
        List<BuildingRentTypeEntity> assigments = buildingRentTypeRepository.findByBuildingId(buildingId);
        List<Long> ids = new ArrayList<>();
        for (BuildingRentTypeEntity bdrt : assigments){
            ids.add(bdrt.getRentType().getId());
        }
        return ids;
    }

    @Transactional
    public void assignRentTypes(BuildingRentTypeRequest request){
        Long buildingId = request.getBuildingId();
        List<Long> rentTypeIds = request.getRentTypeIds();
        buildingRentTypeRepository.deleteByBuildingId(buildingId);
        if (request.getRentTypeIds() == null || request.getRentTypeIds().isEmpty()) {
            return;
        }
        for (Long rentTypeId : rentTypeIds){
            BuildingEntity building = buildingRepository.findById(buildingId).orElseThrow(()->new RuntimeException("Khong tim thay toa nha"));
            RentTypeEntity rentType = rentTypeRepository.findById(rentTypeId).orElseThrow(()->new RuntimeException("Khong tim thay loai hinh thue"));
            BuildingRentTypeEntity result = new BuildingRentTypeEntity();
            result.setBuilding(building);
            result.setRentType(rentType);
            buildingRentTypeRepository.save(result);
        }

    }

    @Transactional(readOnly = true)
    public List<Integer> getRentAreas(Long buildingId) {
        buildingRepository.findById(buildingId)
                .orElseThrow(() -> new RuntimeException("Khong tim thay toa nha"));

        return rentAreaRepository.findByBuildingId(buildingId).stream()
                .map(RentAreaEntity::getValue)
                .collect(Collectors.toList());
    }

    @Transactional
    public void assignRentAreas(Long buildingId, List<Integer> rentAreas) {
        BuildingEntity building = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new RuntimeException("Khong tim thay toa nha"));

        rentAreaRepository.deleteByBuildingId(buildingId);

        if (rentAreas == null || rentAreas.isEmpty()) {
            return;
        }

        Set<Integer> uniqueAreas = new LinkedHashSet<>();
        for (Integer area : rentAreas) {
            if (area == null) {
                continue;
            }
            if (area <= 0) {
                throw new RuntimeException("Dien tich thue phai lon hon 0");
            }
            uniqueAreas.add(area);
        }

        for (Integer area : uniqueAreas) {
            RentAreaEntity rentArea = new RentAreaEntity();
            rentArea.setBuilding(building);
            rentArea.setValue(area);
            rentAreaRepository.save(rentArea);
        }
    }
}

package org.example.service;

import org.example.entity.BuildingEntity;
import org.example.model.BuildingDTO;
import org.example.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

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
}

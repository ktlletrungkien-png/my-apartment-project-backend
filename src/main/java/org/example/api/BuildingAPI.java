package org.example.api;

import org.example.entity.BuildingEntity;
import org.example.model.BuildingDTO;
import org.example.repository.BuildingRepository;
import org.example.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/building")
public class BuildingAPI {

    @Autowired
    private BuildingService buildingService; // Sử dụng Service thay vì Repository
    @Autowired
    private BuildingRepository buildingRepository;

    @GetMapping
    // hàm này khi js gọi ....name=Nam?id=1 thì nó sẽ nấy param ra đi tìm
    public List<BuildingDTO> findBuilding(@RequestParam Map<String, String> params) {

        return buildingService.findAll(params);
    }
    @GetMapping("/{id}")

    public BuildingDTO getDetail(@PathVariable Long id) {

        return buildingService.findById(id);
    }
    @PostMapping
    // tự định nghĩa cho cái post này: nhung la post nen trở thanh thêm dữ liệu
    public String addBuilding(@RequestBody BuildingEntity building){
        try{
            buildingRepository.save(building);
            return "Them toa nha thanh cong";
        }
        catch(Exception e){
            return e.getMessage();
        }
    }
    @PutMapping("/{id}")
    public String updateBuilding(@PathVariable Long id, @RequestBody BuildingEntity building){
        try{
            BuildingEntity oldBuilding = buildingRepository.findById(id).orElseThrow(()-> new RuntimeException("Không tìm thấy toà nhà"));
            oldBuilding.setName(building.getName());
            oldBuilding.setStreet(building.getStreet());
            oldBuilding.setWard(building.getWard());
            oldBuilding.setDistrictId(building.getDistrictId());
            oldBuilding.setNumberOfBasement(building.getNumberOfBasement());
            oldBuilding.setFloorArea(building.getFloorArea());
            oldBuilding.setRentPrice(building.getRentPrice());
            oldBuilding.setRentPriceDescription(building.getRentPriceDescription());
            oldBuilding.setDeposit(building.getDeposit());
            oldBuilding.setPayment(building.getPayment());
            oldBuilding.setRentTime(building.getRentTime());
            oldBuilding.setBrokerageFee(building.getBrokerageFee());
            oldBuilding.setNote(building.getNote());
            oldBuilding.setImage(building.getImage());
            oldBuilding.setManagerName(building.getManagerName());
            oldBuilding.setManagerPhoneNumber(building.getManagerPhoneNumber());

            buildingRepository.save(oldBuilding);
            return "Cập nhật toà nhà thành công";
        } catch (Exception e){
            return e.getMessage();
        }
    }
    @DeleteMapping("/{id}")
    public String deleteBuilding(@PathVariable Long id){
        try{
            buildingRepository.deleteAssignmentByBuildingId(id);
            buildingRepository.deleteRentAreaByBuildingId(id);
            buildingRepository.deleteBuildingRentTypeByBuildingId(id);
            buildingRepository.deleteById(id);
            return "Xoá toà nhà thành công";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
}

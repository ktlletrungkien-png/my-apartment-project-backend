package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BuildingRentTypeRequest {
    private Long buildingId;
    private List<Long> rentTypeIds;
}
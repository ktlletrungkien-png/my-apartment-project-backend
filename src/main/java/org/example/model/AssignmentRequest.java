package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AssignmentRequest {
    private Long buildingId;
    private List<Long> staffIds;
}

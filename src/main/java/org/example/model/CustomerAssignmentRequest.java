package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerAssignmentRequest {
    private Long customerId;
    private List<Long> staffIds;
}

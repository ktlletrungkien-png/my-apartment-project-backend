package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BuildingDTO {
    private Long id;
    private String name;
    private String street;
    private String ward;
    private Long districtId;
    private String address;
    private String rentAreas;
    private Integer rentPrice;
    private String managerName;
    private String staffName;
    private Integer numberOfBasement;
    private Integer floorArea;
    private String rentPriceDescription;
    private String deposit;
    private String payment;
    private String rentTime;
    private BigDecimal brokerageFee;
    private String note;
    private String image;
    private String managerPhoneNumber;
}

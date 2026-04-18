package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
}
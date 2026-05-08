package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDTO {
    private String username, password, fullname, phone, email;
    private Long id;
    private int status;
    private String role;
}

package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CurrentUserDTO {
    private Long id;
    private String username;
    private String fullName;
    private Set<String> roles;
}
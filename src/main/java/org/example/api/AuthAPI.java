package org.example.api;

import org.example.model.CurrentUserDTO;
import org.example.security.CustomUserDetails;
import org.example.utils.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthAPI {

    @GetMapping("/me")
    public CurrentUserDTO getCurrentUser() {
        CustomUserDetails currentUser = SecurityUtils.getCurrentUser();

        if (currentUser == null) {
            throw new RuntimeException("Chua dang nhap");
        }

        CurrentUserDTO dto = new CurrentUserDTO();
        dto.setId(currentUser.getUserId());
        dto.setUsername(currentUser.getUsername());
        dto.setFullName(currentUser.getUser().getFullname());
        dto.setRoles(
                currentUser.getUser().getRoles().stream()
                        .map(role -> role.getCode())
                        .collect(Collectors.toSet())
        );

        return dto;
    }
}
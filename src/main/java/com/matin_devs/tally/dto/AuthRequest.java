package com.matin_devs.tally.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequest {
    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Username is mandatory")
    private String password;
}

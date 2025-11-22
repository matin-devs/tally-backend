package com.matin_devs.tally.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UserRequest {
    @NotBlank(message = "Username is mandatory")
    private String username;
}

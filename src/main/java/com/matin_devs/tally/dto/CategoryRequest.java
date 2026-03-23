package com.matin_devs.tally.dto;

import com.matin_devs.tally.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CategoryRequest {
    private String name;
    private User user;
}

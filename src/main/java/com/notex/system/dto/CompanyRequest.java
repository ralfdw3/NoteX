package com.notex.system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CompanyRequest {
    @NotBlank
    String name;
    String code;
}

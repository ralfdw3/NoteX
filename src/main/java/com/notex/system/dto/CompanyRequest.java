package com.notex.system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CompanyRequest {
    @NotBlank
    String name;

    @NotBlank
    String code;
}

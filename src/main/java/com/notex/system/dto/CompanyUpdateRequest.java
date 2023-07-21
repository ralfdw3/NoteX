package com.notex.system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CompanyUpdateRequest {

    @NotBlank
    String id;
    @NotBlank
    String name;
    String code;
}

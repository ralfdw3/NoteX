package com.notex.system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompanyUpdateRequest {

    @NotBlank
    String id;

    @NotBlank
    String name;

    @NotBlank
    String code;

}

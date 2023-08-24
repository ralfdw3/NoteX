package com.notex.system.dto;

import com.notex.system.enums.CompanyStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    CompanyStatus status;

    String phone;

    String email;

}

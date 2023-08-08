package com.notex.system.dto;

import com.notex.system.models.Company;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompanyUpdateRequest {

    @NotBlank
    String name;

    @NotBlank
    String code;

    public CompanyUpdateRequest(Company company) {
        this.name = company.getName();
        this.code = company.getCode();
    }
}

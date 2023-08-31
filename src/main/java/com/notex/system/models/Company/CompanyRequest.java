package com.notex.system.models.Company;

import com.notex.system.enums.CompanyStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CompanyRequest {
    String id;
    @NotBlank
    String name;
    @NotNull
    Long code;
    String phone;
    String email;
    CompanyStatus status;
}

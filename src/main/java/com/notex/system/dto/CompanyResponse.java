package com.notex.system.dto;

import com.notex.system.enums.CompanyStatus;
import com.notex.system.models.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CompanyResponse {
    String id;
    String name;
    String code;
    CompanyStatus status;

    public CompanyResponse(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.code = company.getCode();
        this.status = company.getStatus();
    }
}

package com.notex.system.dto;

import com.notex.system.models.Company;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CompanyResponse {
    String id;
    String name;
    String code;

    public CompanyResponse(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.code = company.getCode();
    }
}

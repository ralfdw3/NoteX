package com.notex.system.models.Company;

import com.notex.system.enums.CompanyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class CompanyResponse {
    String id;
    String name;
    Long code;
    String phone;
    String email;
    CompanyStatus status;

    public CompanyResponse(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.code = company.getCode();
        this.status = company.getStatus();
        this.phone = company.getPhone();
        this.email = company.getEmail();
    }
}

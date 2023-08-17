package com.notex.system.models;

import com.notex.system.dto.CompanyRequest;
import com.notex.system.dto.CompanyUpdateRequest;
import com.notex.system.enums.CompanyStatus;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "company")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Company {
    @Id
    String id;
    String name;
    String code;
    LocalDateTime creation = LocalDateTime.now();
    CompanyStatus status = CompanyStatus.ATIVO;
    String phone;
    String email;

    public Company(CompanyRequest request) {
        this.name = request.getName();
        this.code = request.getCode();
        this.phone = request.getCompanyPhone();
        this.email = request.getCompanyEmail();
    }

    public Company(String name, String code, String phone, String email) {
        this.name = name;
        this.code = code;
        this.phone = phone;
        this.email = email;
    }

    public void updateCompanyStatus(CompanyStatus status) {
        this.status = status;
    }

    public void updateCompany(CompanyUpdateRequest request) {
        this.name = request.getName();
        this.code = request.getCode();
        this.status = request.getStatus();
        this.email = request.getCompanyEmail();
        this.phone = request.getCompanyPhone();
    }
}

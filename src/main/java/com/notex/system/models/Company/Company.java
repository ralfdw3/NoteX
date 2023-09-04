package com.notex.system.models.Company;

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
    Long code;
    LocalDateTime creation = LocalDateTime.now();
    CompanyStatus status = CompanyStatus.ATIVO;
    String phone;
    String email;

    public void updateCompanyStatus(CompanyStatus status) {
        this.status = status;
    }

    public void updateCompany(CompanyRequest request) {
        this.name = request.getName();
        this.code = request.getCode();
        this.email = request.getEmail();
        this.phone = request.getPhone();
    }

    public Company(CompanyRequest request) {
        this.name = request.getName();
        this.code = request.getCode();
        this.email = request.getEmail();
        this.phone = request.getPhone();
    }
}

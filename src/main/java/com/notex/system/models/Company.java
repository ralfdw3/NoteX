package com.notex.system.models;

import com.notex.system.dto.CompanyRequest;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

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
    Boolean status = true;

    public Company(CompanyRequest request) {
        this.name = request.getName();
        this.code = request.getCode();
    }

    public void updateCompanyStatus(Boolean status) {
        this.status = status;
    }

}

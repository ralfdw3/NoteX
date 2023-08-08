package com.notex.system.stubs;

import com.notex.system.dto.CompanyRequest;
import com.notex.system.dto.CompanyUpdateRequest;
import com.notex.system.models.Company;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public interface CompanyStub {
    static Company companyDefault() {
        return new Company("id", "name", "code", LocalDateTime.now(), true);
    }

    static CompanyRequest companyRequest() {
        return new CompanyRequest("name", "code");
    }

    static CompanyUpdateRequest companyUpdateRequest() {
        return new CompanyUpdateRequest("id","name", "code");
    }

}
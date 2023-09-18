package com.notex.system.service.validations;

import com.notex.system.models.Company.CompanyRequest;

public interface CompanyValidation {
    void validate(CompanyRequest companyRequest);
}

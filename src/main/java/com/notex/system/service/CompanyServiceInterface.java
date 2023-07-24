package com.notex.system.service;

import com.notex.system.dto.CompanyRequest;
import com.notex.system.dto.CompanyResponse;
import com.notex.system.dto.CompanyUpdateRequest;
import com.notex.system.models.Company;

public interface CompanyServiceInterface {
    CompanyResponse createCompany(CompanyRequest request);
    CompanyResponse updateCompany(Company company);

    CompanyResponse updateCompany(CompanyUpdateRequest request);

    CompanyResponse getCompanyById(String id);

    void updateCompanyStatus(String id, Boolean status);
}

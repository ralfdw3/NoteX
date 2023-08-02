package com.notex.system.service;

import com.notex.system.dto.CompanyRequest;
import com.notex.system.dto.CompanyResponse;
import com.notex.system.dto.CompanyUpdateRequest;
import com.notex.system.models.Company;

import java.util.List;

public interface CompanyServiceInterface {
    Company createCompany(CompanyRequest request);
    CompanyResponse updateCompany(Company company);
    CompanyResponse updateCompany(CompanyUpdateRequest request);
    CompanyResponse getCompanyById(String code);
    List<Company> getCompaniesBySearchTerm(String searchTerm);
    void updateCompanyStatus(String code, Boolean status);

}

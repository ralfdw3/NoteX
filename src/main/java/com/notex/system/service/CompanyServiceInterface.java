package com.notex.system.service;

import com.notex.system.dto.CompanyRequest;
import com.notex.system.dto.CompanyResponse;
import com.notex.system.dto.CompanyUpdateRequest;
import com.notex.system.models.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyServiceInterface {
    Company createCompany(CompanyRequest request);
    Company findOrCreateCompany(String companyCode, String companyName);
    CompanyResponse updateCompany(CompanyUpdateRequest request);
    CompanyResponse getCompanyById(String code);
    Page<Company> getAllActiveCompanies(Pageable pageable);
    List<Company> getCompaniesBySearchTerm(String searchTerm);
    CompanyResponse updateCompanyStatus(String code);
}

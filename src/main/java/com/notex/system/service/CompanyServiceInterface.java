package com.notex.system.service;

import com.notex.system.dto.CompanyRequest;
import com.notex.system.dto.CompanyResponse;
import com.notex.system.dto.CompanyUpdateRequest;
import com.notex.system.models.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CompanyServiceInterface {
    Company createCompany(CompanyRequest request);
    Company findOrCreateCompany(String companyCode, String companyName, String companyPhone, String companyEmail);
    CompanyResponse updateCompany(CompanyUpdateRequest request);
    CompanyResponse getCompanyById(String code);
    Page<Company> getAllActiveCompanies(Pageable pageable);
    Page<Company> getCompaniesBySearchTerm(Pageable pageable, String searchTerm);
    CompanyResponse disableCompany(String code);
    Page<Company> getOverdueCompaniesBySearchTermAndStatus(Pageable pageable, String searchTerm);
    Page<Company> getAllOverdueCompanies(Pageable pageable);
}

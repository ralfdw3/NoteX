package com.notex.system.service.impl;

import com.notex.system.dto.CompanyRequest;
import com.notex.system.dto.CompanyResponse;
import com.notex.system.dto.CompanyUpdateRequest;
import com.notex.system.exceptions.NotFoundException;
import com.notex.system.models.Company;
import com.notex.system.repository.CompanyRepository;
import com.notex.system.service.CompanyServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyService implements CompanyServiceInterface {

    private final CompanyRepository companyRepository;

    @Override
    @Transactional
    public CompanyResponse createCompany(CompanyRequest request) {
        Company company = new Company(request);

        companyRepository.save(company);

        return new CompanyResponse(company);
    }

    @Override
    @Transactional
    public CompanyResponse updateCompany(CompanyUpdateRequest request) {
        Company company = findCompanyById(request.getId());

        companyRepository.save(company);

        return new CompanyResponse(company);
    }

    @Override
    @Transactional
    public CompanyResponse updateCompany(Company company) {
        companyRepository.save(company);

        return new CompanyResponse(company);
    }

    @Override
    public CompanyResponse getCompanyById(String id) {
        Company company = findCompanyById(id);

        return new CompanyResponse(company);
    }

    @Override
    @Transactional
    public void updateCompanyStatus(String id, Boolean status) {
        Company company = findCompanyById(id);
        updateCompany(company);
    }

    private Company findCompanyById(String id){
        return companyRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new NotFoundException("Empresa não encontrada."));
    }
}

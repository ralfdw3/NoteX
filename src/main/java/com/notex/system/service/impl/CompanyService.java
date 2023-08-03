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

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService implements CompanyServiceInterface {

    private final CompanyRepository companyRepository;

    @Override
    @Transactional
    public Company createCompany(CompanyRequest request) {
        Company company = new Company(request);

        companyRepository.save(company);

        return company;
    }

    @Override
    public Company findOrCreateCompany(String companyCode, String companyName) {
        return companyRepository
                .findByCodeAndStatusTrue(companyCode)
                .orElseGet(() -> createCompany(new CompanyRequest(companyName,companyCode)));
    }

    @Override
    @Transactional
    public CompanyResponse updateCompany(CompanyUpdateRequest request) {
        Company company = findCompanyById(request.getCode());

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
    public CompanyResponse getCompanyById(String code) {
        Company company = findCompanyById(code);

        return new CompanyResponse(company);
    }

    @Override
    public List<Company> getCompaniesBySearchTerm(String searchTerm) {
        List<Company> companies = companyRepository.findByNameContaining(searchTerm);
        if (companies.isEmpty()){
            throw new NotFoundException("Nenhuma empresa foi encontrada.");
        }

        return companies;
    }

    @Override
    @Transactional
    public void updateCompanyStatus(String code, Boolean status) {
        Company company = findCompanyById(code);
        updateCompany(company);
    }

    protected Company findCompanyById(String code){
        return companyRepository.findByCodeAndStatusTrue(code).orElseThrow(() -> new NotFoundException("Empresa não encontrada."));
    }
}

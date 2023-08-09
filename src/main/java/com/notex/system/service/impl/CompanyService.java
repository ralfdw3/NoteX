package com.notex.system.service.impl;

import com.notex.system.dto.CompanyRequest;
import com.notex.system.dto.CompanyResponse;
import com.notex.system.dto.CompanyUpdateRequest;
import com.notex.system.exceptions.BadRequestException;
import com.notex.system.exceptions.NotFoundException;
import com.notex.system.models.Company;
import com.notex.system.repository.CompanyRepository;
import com.notex.system.service.CompanyServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                .orElseGet(() -> {
                    if (companyName == null || companyName.isEmpty()) {
                        throw new BadRequestException("Coloque o nome da empresa para cadastrá-la, pois não existe nenhuma empresa com este código.");
                    }
                    return createCompany(new CompanyRequest(companyName, companyCode));
                });
    }

    @Override
    @Transactional
    public CompanyResponse updateCompany(CompanyUpdateRequest request) {
        Company company = findCompanyById(request.getId());
        company.updateCompany(request);
        companyRepository.save(company);

        return new CompanyResponse(company);
    }

    @Override
    public CompanyResponse getCompanyById(String code) {
        Company company = findCompanyByCode(code);

        return new CompanyResponse(company);
    }

    @Override
    public Page<Company> getAllActiveCompanies(Pageable pageable) {
        return companyRepository.findAllByStatusTrue(pageable);
    }

    @Override
    public Page<Company> getCompaniesBySearchTerm(Pageable pageable, String searchTerm) {
        return companyRepository.findByNameContainingIgnoreCase(pageable, searchTerm);
    }

    @Override
    @Transactional
    public CompanyResponse updateCompanyStatus(String code) {
        Company company = findCompanyByCode(code);
        company.updateCompanyStatus(false);
        companyRepository.save(company);
        return new CompanyResponse(company);
    }

    protected Company findCompanyByCode(String code){
        return companyRepository.findByCodeAndStatusTrue(code).orElseThrow(() -> new NotFoundException("Empresa não encontrada."));
    }

    private Company findCompanyById(String id){
        return companyRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new NotFoundException("Empresa não encontrada."));
    }
}

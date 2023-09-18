package com.notex.system.service;

import com.notex.system.enums.CompanyStatus;
import com.notex.system.exceptions.NotFoundException;
import com.notex.system.models.Company.Company;
import com.notex.system.models.Company.CompanyRequest;
import com.notex.system.models.Company.CompanyResponse;
import com.notex.system.repository.CompanyRepository;
import com.notex.system.service.validations.CompanyValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final List<CompanyValidation> validationsMap;

    @Transactional
    public Company createCompany(CompanyRequest companyRequest) {
        validationsMap.forEach(companyValidation -> companyValidation.validate(companyRequest));
        Company company = new Company(companyRequest);

        return companyRepository.save(company);
    }

    @Transactional
    public CompanyResponse updateCompany(CompanyRequest companyRequest) {
        validationsMap.forEach(companyValidation -> companyValidation.validate(companyRequest));
        Company company = findCompanyById(companyRequest.getId());
        updateAndSaveCompany(company, companyRequest);

        return new CompanyResponse(company);
    }

    private Company updateAndSaveCompany(Company company, CompanyRequest companyRequest) {
        company.updateCompany(companyRequest);

        return companyRepository.save(company);
    }

    @Transactional
    public CompanyResponse disableCompany(String id) {
        Company company = findCompanyById(id);
        company.updateCompanyStatus(CompanyStatus.INATIVO);
        companyRepository.save(company);

        return new CompanyResponse(company);
    }

    public Company findAndUpdateOrCreateNewCompany(CompanyRequest request) {
        return companyRepository.findByCode(request.getCode())
                .map(value -> updateAndSaveCompany(value, request))
                .orElseGet(() -> createCompany(request));
    }

    public CompanyResponse getCompanyByCode(Long code) {
        Company company = companyRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Empresa não encontrada."));

        return new CompanyResponse(company);
    }

    public Page<Company> getAllActiveCompanies(Pageable pageable) {
        return companyRepository.findAllByStatusOrderByCode(pageable, CompanyStatus.ATIVO);
    }

    public Page<Company> getCompaniesBySearchTerm(Pageable pageable, String searchTerm) {
        return companyRepository.findByNameContainingIgnoreCaseOrderByCode(pageable, searchTerm);
    }

    public Page<Company> getOverdueCompaniesBySearchTermAndStatus(Pageable pageable, String searchTerm) {
        return companyRepository.findByNameContainingIgnoreCaseAndStatusOrderByCode(pageable, searchTerm, CompanyStatus.INADIMPLENTE);
    }

    public Page<Company> getAllOverdueCompanies(Pageable pageable) {
        return companyRepository.findAllByStatusOrderByCode(pageable, CompanyStatus.INADIMPLENTE);
    }

    private Company findCompanyById(String id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Empresa não encontrada."));
    }

}

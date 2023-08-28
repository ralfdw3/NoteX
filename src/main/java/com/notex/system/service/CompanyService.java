package com.notex.system.service;

import com.notex.system.models.Company.CompanyRequest;
import com.notex.system.models.Company.CompanyResponse;
import com.notex.system.enums.CompanyStatus;
import com.notex.system.exceptions.BadRequestException;
import com.notex.system.exceptions.NotFoundException;
import com.notex.system.models.Company.Company;
import com.notex.system.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public Company createCompany(CompanyRequest request) {
        checkIfCompanyNameIsValid(request);
        checkIfEmailOrPhoneIsValid(request);

        Company company = new Company(request);

        return companyRepository.save(company);
    }

    private static void checkIfEmailOrPhoneIsValid(CompanyRequest request) {
        if (phoneAndEmailEmpty(request)){
            throw new BadRequestException("Pelo menos um e-mail ou telefone precisa ser adicionado");
        }
    }

    private static void checkIfCompanyNameIsValid(CompanyRequest request) {
        if (companyNameNullOrEmpty(request)) {
            throw new BadRequestException("Coloque o nome da empresa para cadastrá-la, pois não existe nenhuma empresa com este código.");
        }
    }

    @Transactional
    public CompanyResponse updateCompany(CompanyRequest companyRequest) {
        Company company = findCompanyByCode(companyRequest.getCode());
        updateAndSaveCompany(company, companyRequest);

        return new CompanyResponse(company);
    }

    private Company updateAndSaveCompany(Company company, CompanyRequest companyRequest) {
        checkIfEmailOrPhoneIsValid(companyRequest);
        checkIfCompanyNameIsValid(companyRequest);

        company.updateCompany(companyRequest);
        return companyRepository.save(company);
    }

    @Transactional
    public CompanyResponse disableCompany(Long code) {
        Company company = findCompanyByCode(code);
        company.updateCompanyStatus(CompanyStatus.INATIVO);
        companyRepository.save(company);
        return new CompanyResponse(company);
    }

    public Company findAndUpdateOrCreateNewCompany(CompanyRequest request) {
        Optional<Company> company = companyRepository.findByCode(request.getCode());

        return company.map(value -> updateAndSaveCompany(value, request)).orElseGet(() -> createCompany(request));
    }

    private static boolean phoneAndEmailEmpty(CompanyRequest companyRequest) {
        return companyRequest.getEmail().isEmpty() && companyRequest.getPhone().isEmpty();
    }

    private static boolean companyNameNullOrEmpty(CompanyRequest companyRequest) {
        return companyRequest.getName() == null || companyRequest.getName().isEmpty();
    }

    public CompanyResponse getCompanyByCode(Long code) {
        Company company = findCompanyByCode(code);

        return new CompanyResponse(company);
    }

    public Page<Company> getAllActiveCompanies(Pageable pageable) {
        return companyRepository.findAllByStatus(pageable, CompanyStatus.ATIVO);
    }

    public Page<Company> getCompaniesBySearchTerm(Pageable pageable, String searchTerm) {
        return companyRepository.findByNameContainingIgnoreCase(pageable, searchTerm);
    }

    public Page<Company> getOverdueCompaniesBySearchTermAndStatus(Pageable pageable, String searchTerm) {
        return companyRepository.findByNameContainingIgnoreCaseAndStatus(pageable, searchTerm, CompanyStatus.INADIMPLENTE);
    }

    public Page<Company> getAllOverdueCompanies(Pageable pageable) {
        return companyRepository.findAllByStatus(pageable, CompanyStatus.INADIMPLENTE);
    }

    private Company findCompanyByCode(Long code){
        return companyRepository.findByCode(code).orElseThrow(() -> new NotFoundException("Empresa não encontrada."));
    }
}

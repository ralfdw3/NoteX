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
        validations(request);

        Company company = new Company(request);

        return companyRepository.save(company);
    }

    @Transactional
    public CompanyResponse updateCompany(CompanyRequest companyRequest) {
        validations(companyRequest);
        Company company = findCompanyById(companyRequest.getId());

        updateAndSaveCompany(company, companyRequest);

        return new CompanyResponse(company);
    }

    private Company updateAndSaveCompany(Company company, CompanyRequest companyRequest) {
        company.updateCompany(companyRequest);
        return companyRepository.save(company);
    }

    private void validations(CompanyRequest companyRequest) {
        checkIfEmailOrPhoneIsValid(companyRequest);
        checkIfCompanyNameIsValid(companyRequest);
        checkIfCodeIsInUse(companyRequest);
    }

    @Transactional
    public CompanyResponse disableCompany(String id) {
        Company company = findCompanyById(id);
        company.updateCompanyStatus(CompanyStatus.INATIVO);
        companyRepository.save(company);
        return new CompanyResponse(company);
    }

    public Company findAndUpdateOrCreateNewCompany(CompanyRequest request) {
        Optional<Company> company = companyRepository.findByCode(request.getCode());

        return company.map(value -> updateAndSaveCompany(value, request)).orElseGet(() -> createCompany(request));
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

    private void checkIfCodeIsInUse(CompanyRequest companyRequest) {
        Long requestCode = companyRequest.getCode();

        if (existsCompanyByCode(requestCode)){
            throw new BadRequestException("Já existe uma empresa cadastrada com este código.");
        }
    }

    private boolean existsCompanyByCode(Long code) {
        return companyRepository.findByCode(code).isPresent();
    }

    private static boolean phoneAndEmailEmpty(CompanyRequest companyRequest) {
        return companyRequest.getEmail().isEmpty() && companyRequest.getPhone().isEmpty();
    }

    private static boolean companyNameNullOrEmpty(CompanyRequest companyRequest) {
        return companyRequest.getName() == null || companyRequest.getName().isEmpty();
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

    private Company findCompanyById(String id){
        return companyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Empresa não encontrada."));
    }

}

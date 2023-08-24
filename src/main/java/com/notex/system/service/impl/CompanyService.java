package com.notex.system.service.impl;

import com.notex.system.dto.CompanyRequest;
import com.notex.system.dto.CompanyResponse;
import com.notex.system.dto.CompanyUpdateRequest;
import com.notex.system.enums.CompanyStatus;
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
    public Company findOrCreateCompany(String companyCode, String companyName, String companyPhone, String companyEmail) {
        return companyRepository
                .findByCode(companyCode)
                .orElseGet(() -> {
                    if (companyName == null || companyName.isEmpty()) {
                        throw new BadRequestException("Coloque o nome da empresa para cadastrá-la, pois não existe nenhuma empresa com este código.");
                    }
                    if (companyEmail.isEmpty() && companyPhone.isEmpty()){
                        throw new BadRequestException("Pelo menos um e-mail ou telefone precisa ser adicionado");
                    }
                    return createCompany(new CompanyRequest(companyName, companyCode, companyPhone, companyEmail));
                });
    }

    @Override
    @Transactional
    public CompanyResponse updateCompany(CompanyUpdateRequest request) {
        Company company = companyRepository.findById(request.getId()).get();
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
        return companyRepository.findAllByStatus(pageable, CompanyStatus.ATIVO);
    }

    @Override
    public Page<Company> getCompaniesBySearchTerm(Pageable pageable, String searchTerm) {
        return companyRepository.findByNameContainingIgnoreCase(pageable, searchTerm);
    }

    @Override
    public Page<Company> getOverdueCompaniesBySearchTermAndStatus(Pageable pageable, String searchTerm) {
        return companyRepository.findByNameContainingIgnoreCaseAndStatus(pageable, searchTerm, CompanyStatus.INADIMPLENTE);
    }

    @Override
    public Page<Company> getAllOverdueCompanies(Pageable pageable) {
        return companyRepository.findAllByStatus(pageable, CompanyStatus.INADIMPLENTE);
    }

    @Override
    @Transactional
    public CompanyResponse disableCompany(String code) {
        Company company = findCompanyByCode(code);
        company.updateCompanyStatus(CompanyStatus.INATIVO);
        companyRepository.save(company);
        return new CompanyResponse(company);
    }

    protected Company findCompanyByCode(String code){
        return companyRepository.findByCode(code).orElseThrow(() -> new NotFoundException("Empresa não encontrada."));
    }
}

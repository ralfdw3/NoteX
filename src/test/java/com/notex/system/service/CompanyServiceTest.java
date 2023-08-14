package com.notex.system.service;

import com.notex.system.dto.CompanyRequest;
import com.notex.system.dto.CompanyResponse;
import com.notex.system.dto.CompanyUpdateRequest;
import com.notex.system.exceptions.NotFoundException;
import com.notex.system.models.Company;
import com.notex.system.repository.CompanyRepository;
import com.notex.system.service.impl.CompanyService;
import com.notex.system.stubs.CompanyStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class CompanyServiceTest {
    @Mock
    private CompanyRepository companyRepository;
    @InjectMocks
    private CompanyService companyService;
    Company companyDefault = CompanyStub.companyDefault();
    CompanyRequest companyRequest = CompanyStub.companyRequest();
    CompanyUpdateRequest companyUpdateRequest = CompanyStub.companyUpdateRequest();


    @BeforeEach
    public void setup() {
        openMocks(this);

        when(companyRepository.save(companyDefault)).thenReturn(companyDefault);
        when(companyRepository.findByIdAndStatus(companyDefault.getId())).thenReturn(Optional.ofNullable(companyDefault));
        when(companyRepository.findByNameContainingIgnoreCase("validSearchTerm")).thenReturn(List.of(companyDefault));
    }

    @Test
    public void Should_ReturnCompanyResponse_When_CreatingANewCompany () {
        CompanyResponse response = companyService.createCompany(companyRequest);

        assertEquals(companyDefault.getName(), response.getName());
        assertEquals(companyDefault.getCode(), response.getCode());
    }

    @Test
    public void Should_ReturnCompanyResponse_When_UpdatingACompanyWithCompanyUpdateRequest () {
        CompanyResponse response = companyService.updateCompany(companyUpdateRequest);

        assertEquals(companyUpdateRequest.getId(), response.getId());
        assertEquals(companyUpdateRequest.getName(), response.getName());
        assertEquals(companyUpdateRequest.getCode(), response.getCode());
    }

    @Test
    public void Should_ThrowNotFoundException_When_UpdatingCompanyWithInvalidIdAndUpdateRequest () {
        CompanyUpdateRequest request = new CompanyUpdateRequest("notFound", "name", "code");
        NotFoundException exception = assertThrows(NotFoundException.class, () -> companyService.updateCompany(request));
        assertEquals("Empresa não encontrada.", exception.getMessage());
    }

    @Test
    public void Should_ReturnCompanyResponse_When_UpdatingACompanyWithCompanyObject () {
        CompanyResponse response = companyService.updateCompany(companyDefault);

        assertEquals(companyUpdateRequest.getName(), response.getName());
        assertEquals(companyUpdateRequest.getCode(), response.getCode());
    }

    @Test
    public void Should_ReturnCompanyResponse_When_FindingACompanyById () {
        CompanyResponse response = companyService.getCompanyById(companyDefault.getId());

        assertEquals(companyDefault.getId(), response.getId());
        assertEquals(companyDefault.getName(), response.getName());
        assertEquals(companyDefault.getCode(), response.getCode());
    }

    @Test
    public void Should_ThrowNotFoundException_When_FindingCompanyWithInvalidId () {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> companyService.getCompanyById("notFound"));
        assertEquals("Empresa não encontrada.", exception.getMessage());
    }

    @Test
    public void Should_ReturnListCompanies_When_FindingCompaniesBySearchTerm () {
        List<Company> companies = companyService.getCompaniesBySearchTerm("validSearchTerm");

        assertEquals(companies.get(0).getId(), "id");
        assertEquals(companies.get(0).getName(), "name");
        assertEquals(companies.get(0).getCode(), "code");
        assertEquals(companies.get(0).getStatus(), true);
        assertEquals(companies.get(0).getCreation().getDayOfMonth(), LocalDateTime.now().getDayOfMonth());
    }

    @Test
    public void Should_ThrowNotFoundException_When_FindingCompaniesBySearchTerm () {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> companyService.getCompaniesBySearchTerm("invalid"));
        assertEquals("Nenhuma empresa foi encontrada.", exception.getMessage());
    }

    @Test
    public void Should_ReturnNothing_When_UpdatingCompanyStatus() {
        companyService.updateCompanyStatus(companyDefault.getId(), false);

        verify(companyRepository).findByIdAndStatus(companyDefault.getId());
        verify(companyRepository).save(companyDefault);
    }

    @Test
    public void Should_ThrowNotFoundException_When_UpdatingCompanyWithInvalidId () {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> companyService.updateCompanyStatus("notFound", true));
        assertEquals("Empresa não encontrada.", exception.getMessage());
    }

}

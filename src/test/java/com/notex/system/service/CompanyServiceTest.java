package com.notex.system.service;

import com.notex.system.dto.CardResponse;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
        when(companyRepository.findByIdAndStatusTrue(companyDefault.getId())).thenReturn(Optional.ofNullable(companyDefault));

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

        assertEquals(companyUpdateRequest.getId(), response.getId());
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
    public void Should_ReturnNothing_When_UpdatingCompanyStatus() {
        companyService.updateCompanyStatus(companyDefault.getId(), false);

        verify(companyRepository).findByIdAndStatusTrue(companyDefault.getId());
        verify(companyRepository).save(companyDefault);
    }

    @Test
    public void Should_ThrowNotFoundException_When_UpdatingCompanyWithInvalidId () {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> companyService.updateCompanyStatus("notFound", true));
        assertEquals("Empresa não encontrada.", exception.getMessage());
    }

}

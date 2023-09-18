package com.notex.system.service;

import com.notex.system.enums.CompanyStatus;
import com.notex.system.models.Company.CompanyRequest;
import com.notex.system.models.Company.CompanyResponse;
import com.notex.system.exceptions.NotFoundException;
import com.notex.system.models.Company.Company;
import com.notex.system.repository.CompanyRepository;
import com.notex.system.stubs.CompanyStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.notex.system.stubs.CompanyStub.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class CompanyServiceTest {
    private static final Company COMPANY_DEFAULT_STUB = companyDefault().build();
    private static final CompanyRequest COMPANY_REQUEST_STUB = companyRequest().build();
    @Mock
    private CompanyRepository companyRepository;
    @InjectMocks
    private CompanyService companyService;

    @BeforeEach
    public void setup() {
        openMocks(this);

        when(companyRepository.save(COMPANY_DEFAULT_STUB)).thenReturn(COMPANY_DEFAULT_STUB);
        when(companyRepository.findByIdAndStatus(COMPANY_DEFAULT_STUB.getId(), CompanyStatus.ATIVO)).thenReturn(Optional.of(COMPANY_DEFAULT_STUB));
    }

    @Test
    public void Should_ReturnCompanyResponse_When_CreatingANewCompany () {
        Company response = companyService.createCompany(COMPANY_REQUEST_STUB);

        assertEquals(COMPANY_DEFAULT_STUB.getName(), response.getName());
        assertEquals(COMPANY_DEFAULT_STUB.getCode(), response.getCode());
    }

    @Test
    public void Should_ThrowNotFoundException_When_UpdatingCompanyWithInvalidIdAndUpdateRequest () {
        CompanyRequest request = companyRequest().id("notFound").build();
        NotFoundException exception = assertThrows(NotFoundException.class, () -> companyService.updateCompany(request));
        assertEquals("Empresa não encontrada.", exception.getMessage());
    }

    @Test
    public void Should_ReturnCompanyResponse_When_UpdatingACompanyWithCompanyObject () {
        CompanyResponse response = companyService.updateCompany(COMPANY_REQUEST_STUB);

        assertEquals(COMPANY_REQUEST_STUB.getName(), response.getName());
        assertEquals(COMPANY_REQUEST_STUB.getCode(), response.getCode());
    }

    @Test
    public void Should_ReturnCompanyResponse_When_FindingACompanyById () {
        CompanyResponse response = companyService.getCompanyByCode(COMPANY_DEFAULT_STUB.getCode());

        assertEquals(COMPANY_DEFAULT_STUB.getId(), response.getId());
        assertEquals(COMPANY_DEFAULT_STUB.getName(), response.getName());
        assertEquals(COMPANY_DEFAULT_STUB.getCode(), response.getCode());
    }

    @Test
    public void Should_ThrowNotFoundException_When_FindingCompanyWithInvalidId () {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> companyService.getCompanyByCode(COMPANY_DEFAULT_STUB.getCode()));
        assertEquals("Empresa não encontrada.", exception.getMessage());
    }
}

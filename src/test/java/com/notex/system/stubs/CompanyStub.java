package com.notex.system.stubs;

import com.notex.system.enums.CompanyStatus;
import com.notex.system.models.Company.Company;
import com.notex.system.models.Company.CompanyRequest;
import com.notex.system.models.Company.CompanyResponse;

import java.time.LocalDateTime;

public interface CompanyStub {

    String ID = "id";
    long CODE = 1L;
    String NAME = "name";
    LocalDateTime CREATION_NOW = LocalDateTime.now();
    CompanyStatus STATUS_ATIVO = CompanyStatus.ATIVO;
    String EMAIL = "email";
    String PHONE = "phone";

    static Company.CompanyBuilder companyDefault() {
        return Company.builder()
                .id(ID)
                .code(CODE)
                .name(NAME)
                .creation(CREATION_NOW)
                .status(STATUS_ATIVO)
                .email(EMAIL)
                .phone(PHONE);
    }

    static CompanyRequest.CompanyRequestBuilder companyRequest() {
        return CompanyRequest.builder()
                .id(ID)
                .name(NAME)
                .code(CODE)
                .phone(PHONE)
                .email(EMAIL)
                .status(STATUS_ATIVO);
    }

    static CompanyResponse.CompanyResponseBuilder companyResponse() {
        return CompanyResponse.builder()
                .id(ID)
                .name(NAME)
                .code(CODE)
                .phone(PHONE)
                .email(EMAIL)
                .status(STATUS_ATIVO);
    }
}
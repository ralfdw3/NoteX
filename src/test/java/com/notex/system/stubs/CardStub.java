package com.notex.system.stubs;

import com.notex.system.models.Card.CardRequest;
import com.notex.system.models.Card.CardResponse;
import com.notex.system.enums.CardStatus;
import com.notex.system.models.Card.Card;
import com.notex.system.models.Company.Company;
import com.notex.system.models.Company.CompanyRequest;
import com.notex.system.models.Company.CompanyResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface CardStub {

    LocalDate APPEARANCE_NOW_PLUS_ONE = LocalDate.now().plusDays(1L);
    CardStatus STATUS_EM_NEGOCIACAO = CardStatus.EM_NEGOCIACAO;
    String DESCRIPTION = "description";
    String ID = "id";
    Company COMPANY_STUB = CompanyStub.companyDefault().build();
    CompanyRequest COMPANY_REQUEST_STUB = CompanyStub.companyRequest().build();
    CompanyResponse COMPANY_RESPONSE_STUB = CompanyStub.companyResponse().build();

    static Card.CardBuilder cardDefault(){
        return Card.builder()
                .id(ID)
                .description(DESCRIPTION)
                .appearance(APPEARANCE_NOW_PLUS_ONE)
                .company(COMPANY_STUB)
                .status(STATUS_EM_NEGOCIACAO);
    }

    static CardRequest.CardRequestBuilder cardRequest(){
        return CardRequest.builder()
                .id(ID)
                .description(DESCRIPTION)
                .appearance(APPEARANCE_NOW_PLUS_ONE)
                .companyRequest(COMPANY_REQUEST_STUB)
                .status(STATUS_EM_NEGOCIACAO);
    }

    static CardResponse.CardResponseBuilder cardResponse() {
        return CardResponse.builder()
                .id(ID)
                .description(DESCRIPTION)
                .appearance(APPEARANCE_NOW_PLUS_ONE)
                .company(COMPANY_RESPONSE_STUB)
                .status(STATUS_EM_NEGOCIACAO);
    }
}

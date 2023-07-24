package com.notex.system.stubs;

import com.notex.system.dto.CardRequest;
import com.notex.system.dto.CardResponse;
import com.notex.system.dto.CardUpdateRequest;
import com.notex.system.enums.Status;
import com.notex.system.models.Card;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface CardStub {

    static Card cardDefault(){
        return new Card("id", "title", "description", LocalDateTime.now(), LocalDate.now().plusDays(1), CompanyStub.companyDefault(), Status.EM_NEGOCIACAO);
    }

    static CardRequest cardRequest(){
        return new CardRequest("title", "description", LocalDate.now().plusDays(1), CompanyStub.companyDefault());
    }

    static CardResponse cardResponse() {
        return new CardResponse("id", "title", "description", LocalDateTime.now(), LocalDate.now().plusDays(1), CompanyStub.companyDefault());
    }

    static CardUpdateRequest cardUpdateRequest() {
        return new CardUpdateRequest("id", "title", "description", LocalDate.now().plusDays(1), CompanyStub.companyDefault());
    }
}

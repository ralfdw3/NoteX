package com.notex.system.service;

import com.notex.system.dto.CardRequest;
import com.notex.system.dto.CardResponse;
import com.notex.system.dto.CardUpdateRequest;
import com.notex.system.models.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CardServiceInterface {
    CardResponse createCard(CardRequest request);
    CardResponse updateCard(CardUpdateRequest request);
    CardResponse getCardById(String id);
    CardResponse deleteCardById(String id);
    List<Card> getAllActiveCards();
    Page<Card> getAllCardsByCompany(Pageable pageable, String companyId);
}

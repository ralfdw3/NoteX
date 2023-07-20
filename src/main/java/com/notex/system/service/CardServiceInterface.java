package com.notex.system.service;

import com.notex.system.dto.CardRequest;
import com.notex.system.dto.CardResponse;
import com.notex.system.dto.CardUpdateRequest;

public interface CardServiceInterface {
    CardResponse createCard(CardRequest request);

    CardResponse updateCard(CardUpdateRequest request);

    CardResponse getCardById(String id);

    CardResponse deleteCardById(String id);
}

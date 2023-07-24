package com.notex.system.service.impl;

import com.notex.system.dto.CardRequest;
import com.notex.system.dto.CardResponse;
import com.notex.system.dto.CardUpdateRequest;
import com.notex.system.exceptions.NotFoundException;
import com.notex.system.models.Card;
import com.notex.system.repository.CardRepository;
import com.notex.system.service.CardServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CardService implements CardServiceInterface {

    private final CardRepository cardRepository;

    @Override
    @Transactional
    public CardResponse createCard(CardRequest request) {
        Card card = new Card(request);

        cardRepository.save(card);

        return new CardResponse(card);
    }

    @Override
    @Transactional
    public CardResponse updateCard(CardUpdateRequest request) {
        Card card = findCardById(request.getId());

        cardRepository.save(card);

        return new CardResponse(card);
    }

    @Override
    public CardResponse getCardById(String id) {
        Card card = findCardById(id);
        return new CardResponse(card);
    }

    @Override
    @Transactional
    public CardResponse deleteCardById(String id) {
        Card card = findCardById(id);
        cardRepository.deleteById(id);

        return new CardResponse(card);
    }

    private Card findCardById(String id){
        return cardRepository.findById(id).orElseThrow(() -> new NotFoundException("Card não encontrado."));
    }
}

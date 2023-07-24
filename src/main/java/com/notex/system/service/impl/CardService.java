package com.notex.system.service.impl;

import com.notex.system.dto.CardRequest;
import com.notex.system.dto.CardResponse;
import com.notex.system.dto.CardUpdateRequest;
import com.notex.system.exceptions.NotFoundException;
import com.notex.system.models.Card;
import com.notex.system.repository.CardRepository;
import com.notex.system.service.CardServiceInterface;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CardService implements CardServiceInterface {

    private final CardRepository cardRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public CardResponse createCard(CardRequest request) {
        Card card = modelMapper.map(request, Card.class);

        cardRepository.save(card);

        return modelMapper.map(card, CardResponse.class);
    }

    @Override
    @Transactional
    public CardResponse updateCard(CardUpdateRequest request) {
        Card card = findCardById(request.getId());

        cardRepository.save(card);

        return modelMapper.map(card, CardResponse.class);
    }

    @Override
    public CardResponse getCardById(String id) {
        Card card = findCardById(id);
        return modelMapper.map(card, CardResponse.class);
    }

    @Override
    @Transactional
    public CardResponse deleteCardById(String id) {
        Card card = findCardById(id);
        cardRepository.deleteById(id);

        return modelMapper.map(card, CardResponse.class);
    }

    private Card findCardById(String id){
        return cardRepository.findById(id).orElseThrow(() -> new NotFoundException("Card não encontrado."));
    }
}

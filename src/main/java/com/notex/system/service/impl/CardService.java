package com.notex.system.service.impl;

import com.notex.system.dto.*;
import com.notex.system.enums.Status;
import com.notex.system.exceptions.NotFoundException;
import com.notex.system.models.Card;
import com.notex.system.models.Company;
import com.notex.system.repository.CardRepository;
import com.notex.system.service.CardServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService implements CardServiceInterface {

    private final CardRepository cardRepository;
    private final CompanyService companyService;

    @Override
    @Transactional
    public CardResponse createCard(CardRequest request) {
        Company company = companyService.findOrCreateCompany(request.getCompanyCode(), request.getCompanyName());
        Card card = new Card(request, company);

        cardRepository.save(card);

        return new CardResponse(card);
    }

    @Override
    @Transactional
    public CardResponse updateCard(CardUpdateRequest request) {
        Card card = findCardById(request.getId());
        Company company = companyService.findOrCreateCompany(request.getCompanyCode(), request.getCompanyName());
        card.updateCard(request, company);
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

    @Override
    public List<Card> getAllActiveCards() {
        return cardRepository.findAllByStatusOrStatus(Status.ABERTO, Status.EM_NEGOCIACAO);
    }

    @Override
    public Page<Card> getAllCardsByCompany(Pageable pageable, String companyId) {
        return cardRepository.findAllByCompanyId(pageable, companyId);
    }

    private Card findCardById(String id){
        return cardRepository.findById(id).orElseThrow(() -> new NotFoundException("Card não encontrado."));
    }
}

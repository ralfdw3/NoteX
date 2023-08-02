package com.notex.system.service.impl;

import com.notex.system.dto.*;
import com.notex.system.enums.Status;
import com.notex.system.exceptions.NotFoundException;
import com.notex.system.models.Card;
import com.notex.system.models.Company;
import com.notex.system.repository.CardRepository;
import com.notex.system.service.CardServiceInterface;
import lombok.RequiredArgsConstructor;
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
    public CardResponse createCard(CardRequest cardRequest) {
        Company company;

        try {
            company = companyService.findCompanyById(cardRequest.getCompanyCode());
        } catch (NotFoundException e) {
            CompanyRequest companyRequest = new CompanyRequest(cardRequest.getCompanyName(), cardRequest.getCompanyCode());
            company = companyService.createCompany(companyRequest);
        }
        Card card = new Card(cardRequest, company);

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

    @Override
    public List<Card> getAllActiveCards() {
        return cardRepository.findAllByStatusOrStatus(Status.ABERTO, Status.EM_NEGOCIACAO);
    }

    private Card findCardById(String id){
        return cardRepository.findById(id).orElseThrow(() -> new NotFoundException("Card não encontrado."));
    }
}

package com.notex.system.service;

import com.notex.system.enums.CardStatus;
import com.notex.system.exceptions.NotFoundException;
import com.notex.system.models.Card.Card;
import com.notex.system.models.Card.CardRequest;
import com.notex.system.models.Card.CardResponse;
import com.notex.system.models.Company.Company;
import com.notex.system.models.Company.CompanyResponse;
import com.notex.system.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService{

    private final CardRepository cardRepository;
    private final CompanyService companyService;

    @Transactional
    public CardResponse persistNewCard(CardRequest request) {
        Company company = companyService.findAndUpdateOrCreateNewCompany(request.getCompanyRequest());
        Card card = new Card(request, company);

        cardRepository.save(card);

        return new CardResponse(card);
    }

    @Transactional
    public CardResponse updateCard(CardRequest request) {
        Card card = findCardById(request.getId());
        companyService.updateCompany(request.getCompanyRequest());
        card.updateCard(request);

        cardRepository.save(card);

        return new CardResponse(card);
    }

    public CardResponse getCardById(String id) {
        Card card = findCardById(id);

        return new CardResponse(card);
    }

    @Transactional
    public CardResponse deleteCardById(String id) {
        Card card = findCardById(id);
        cardRepository.deleteById(id);

        return new CardResponse(card);
    }

    public List<Card> getAllActiveCards() {
        return cardRepository.findAllByStatusOrStatus(CardStatus.ABERTO, CardStatus.EM_NEGOCIACAO, Sort.by("appearance").ascending());
    }

    public Page<Card> getAllCardsByCompany(Pageable pageable, String companyId) {
        return cardRepository.findAllByCompanyId(pageable, companyId);
    }

    private Card findCardById(String id){
        return cardRepository.findById(id).orElseThrow(() -> new NotFoundException("Card não encontrado."));
    }
}

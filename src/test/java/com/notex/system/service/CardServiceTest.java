package com.notex.system.service;

import com.notex.system.models.Card.CardRequest;
import com.notex.system.models.Card.CardResponse;
import com.notex.system.exceptions.NotFoundException;
import com.notex.system.models.Card.Card;
import com.notex.system.repository.CardRepository;
import com.notex.system.stubs.CardStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class CardServiceTest {
    @Mock
    private CardRepository cardRepository;
    @InjectMocks
    private CardService cardService;
    Card cardDefault = CardStub.cardDefault();
    CardRequest cardRequest = CardStub.cardRequest();
    CardUpdateRequest cardUpdateRequest = CardStub.cardUpdateRequest();

    @BeforeEach
    public void setup() {
        openMocks(this);

        when(cardRepository.save(cardDefault)).thenReturn(cardDefault);
        when(cardRepository.findById(cardDefault.getId())).thenReturn(Optional.ofNullable(cardDefault));
    }

    @Test
    public void Should_ReturnCardResponse_When_CreatingANewCard () {
        CardResponse response = cardService.persistNewCard(cardRequest);

        assertEquals(cardDefault.getTitle(), response.getTitle());
        assertEquals(cardDefault.getDescription(), response.getDescription());
        assertEquals(cardDefault.getCompany().getName(), response.getCompany().getName());
        assertEquals(cardDefault.getAppearance(), response.getAppearance());
    }

    @Test
    public void Should_ReturnCardResponse_When_UpdatingACard () {
        CardResponse response = cardService.updateCard(cardUpdateRequest);

        assertEquals(cardUpdateRequest.getId(), response.getId());
        assertEquals(cardUpdateRequest.getTitle(), response.getTitle());
        assertEquals(cardUpdateRequest.getDescription(), response.getDescription());
        assertEquals(cardUpdateRequest.getCompany().getName(), response.getCompany().getName());
        assertEquals(cardUpdateRequest.getAppearance(), response.getAppearance());
    }

    @Test
    public void Should_ReturnCardResponse_When_FindingACardById () {
        CardResponse response = cardService.getCardById(cardDefault.getId());

        assertEquals(cardDefault.getId(), response.getId());
        assertEquals(cardDefault.getTitle(), response.getTitle());
        assertEquals(cardDefault.getDescription(), response.getDescription());
        assertEquals(cardDefault.getCompany().getName(), response.getCompany().getName());
        assertEquals(cardDefault.getAppearance(), response.getAppearance());
    }

    @Test
    public void Should_ReturnCardResponse_When_DeletingACardById() {
        CardResponse response = cardService.deleteCardById(cardDefault.getId());

        assertNotNull(response);
        assertEquals(cardDefault.getId(), response.getId());
        assertEquals(cardDefault.getTitle(), response.getTitle());
        assertEquals(cardDefault.getDescription(), response.getDescription());
        assertEquals(cardDefault.getCompany().getName(), response.getCompany().getName());
        assertEquals(cardDefault.getAppearance(), response.getAppearance());
    }

    @Test
    public void Should_ThrowNotFoundException_When_FindingCardWithInvalidId () {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> cardService.getCardById("notFound"));
        assertEquals("Card não encontrado.", exception.getMessage());
    }

}

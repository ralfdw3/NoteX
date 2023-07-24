package com.notex.system.service;

import com.notex.system.dto.CardRequest;
import com.notex.system.dto.CardResponse;
import com.notex.system.dto.CardUpdateRequest;
import com.notex.system.exceptions.NotFoundException;
import com.notex.system.models.Card;
import com.notex.system.repository.CardRepository;
import com.notex.system.service.impl.CardService;
import com.notex.system.stubs.CardStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CardService cardService;

    Card cardDefault = CardStub.cardDefault();
    CardRequest cardRequest = CardStub.cardRequest();
    CardResponse cardResponse = CardStub.cardResponse();
    CardUpdateRequest cardUpdateRequest = CardStub.cardUpdateRequest();

    @BeforeEach
    public void setup() {
        openMocks(this);

        when(cardRepository.save(cardDefault)).thenReturn(cardDefault);
        when(cardRepository.findById(cardDefault.getId())).thenReturn(Optional.ofNullable(cardDefault));
        when(modelMapper.map(cardRequest, Card.class)).thenReturn(cardDefault);
        when(modelMapper.map(cardDefault, CardResponse.class)).thenReturn(cardResponse);
    }

    @Test
    public void Should_ReturnCardResponse_When_CreatingANewCard () {
        CardResponse response = cardService.createCard(cardRequest);

        assertEquals(cardUpdateRequest.getId(), response.getId());
        assertEquals(cardUpdateRequest.getTitle(), response.getTitle());
        assertEquals(cardUpdateRequest.getDescription(), response.getDescription());
        assertEquals(cardUpdateRequest.getCompany().getName(), response.getCompany().getName());
        assertEquals(cardUpdateRequest.getAppearance(), response.getAppearance());

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

        assertEquals(cardUpdateRequest.getId(), response.getId());
        assertEquals(cardUpdateRequest.getTitle(), response.getTitle());
        assertEquals(cardUpdateRequest.getDescription(), response.getDescription());
        assertEquals(cardUpdateRequest.getCompany().getName(), response.getCompany().getName());
        assertEquals(cardUpdateRequest.getAppearance(), response.getAppearance());
    }

    @Test
    public void Should_ReturnCardResponse_When_DeletingACardById() {
        CardResponse response = cardService.deleteCardById(cardDefault.getId());

        assertNotNull(response);
        assertEquals(cardUpdateRequest.getId(), response.getId());
        assertEquals(cardUpdateRequest.getTitle(), response.getTitle());
        assertEquals(cardUpdateRequest.getDescription(), response.getDescription());
        assertEquals(cardUpdateRequest.getCompany().getName(), response.getCompany().getName());
        assertEquals(cardUpdateRequest.getAppearance(), response.getAppearance());

        verify(cardRepository).deleteById(cardDefault.getId());
    }

    @Test
    public void Should_ThrowNotFoundException_When_FindingCardWithInvalidId () {
        when(cardRepository.findById("notFound")).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> cardService.getCardById("notFound"));

    }

}

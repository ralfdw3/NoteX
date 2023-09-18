package com.notex.system.service;

import com.notex.system.models.Card.CardRequest;
import com.notex.system.models.Card.CardResponse;
import com.notex.system.exceptions.NotFoundException;
import com.notex.system.models.Card.Card;
import com.notex.system.models.Company.Company;
import com.notex.system.models.Company.CompanyRequest;
import com.notex.system.repository.CardRepository;
import com.notex.system.repository.CompanyRepository;
import com.notex.system.stubs.CardStub;
import com.notex.system.stubs.CompanyStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static com.notex.system.stubs.CardStub.*;
import static com.notex.system.stubs.CompanyStub.companyDefault;
import static com.notex.system.stubs.CompanyStub.companyRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class CardServiceTest {
    public static final Card CARD_DEFAULT_STUB = cardDefault().build();
    public static final CardRequest CARD_REQUEST_STUB = cardRequest().build();
    public static final CardResponse CARD_RESPONSE_STUB = cardResponse().build();
    public static final CompanyRequest COMPANY_REQUEST_STUB = companyRequest().build();
    public static final Company COMPANY_DEFAULT_STUB = companyDefault().build();
    @Mock
    private CardRepository cardRepository;
    @Mock
    private CompanyService companyService;
    @InjectMocks
    private CardService cardService;


    @BeforeEach
    public void setup() {
        openMocks(this);

        when(cardRepository.save(CARD_DEFAULT_STUB)).thenReturn(CARD_DEFAULT_STUB);
        when(cardRepository.findById(CARD_DEFAULT_STUB.getId())).thenReturn(Optional.of(CARD_DEFAULT_STUB));
    }

    @Test
    public void Should_ReturnCardResponse_When_CreatingANewCard () {
        when(companyService.findAndUpdateOrCreateNewCompany(COMPANY_REQUEST_STUB)).thenReturn(COMPANY_DEFAULT_STUB);

        CardResponse response = cardService.persistNewCard(CARD_REQUEST_STUB);

        assertEquals(CARD_RESPONSE_STUB.getDescription(), response.getDescription());
        assertEquals(CARD_RESPONSE_STUB.getAppearance(), response.getAppearance());
        assertEquals(CARD_RESPONSE_STUB.getCompany(), response.getCompany());
    }

    @Test
    public void Should_ReturnCardResponse_When_FindingACardById () {
        CardResponse response = cardService.getCardById(CARD_DEFAULT_STUB.getId());

        assertEquals(CARD_DEFAULT_STUB.getId(), response.getId());
        assertEquals(CARD_DEFAULT_STUB.getDescription(), response.getDescription());
        assertEquals(CARD_DEFAULT_STUB.getCompany().getName(), response.getCompany().getName());
        assertEquals(CARD_DEFAULT_STUB.getAppearance(), response.getAppearance());
    }

    @Test
    public void Should_ReturnCardResponse_When_DeletingACardById() {
        CardResponse response = cardService.deleteCardById(CARD_DEFAULT_STUB.getId());

        assertNotNull(response);
        assertEquals(CARD_DEFAULT_STUB.getId(), response.getId());
        assertEquals(CARD_DEFAULT_STUB.getDescription(), response.getDescription());
        assertEquals(CARD_DEFAULT_STUB.getCompany().getName(), response.getCompany().getName());
        assertEquals(CARD_DEFAULT_STUB.getAppearance(), response.getAppearance());
    }

    @Test
    public void Should_ThrowNotFoundException_When_FindingCardWithInvalidId () {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> cardService.getCardById("notFound"));
        assertEquals("Card não encontrado.", exception.getMessage());
    }
}

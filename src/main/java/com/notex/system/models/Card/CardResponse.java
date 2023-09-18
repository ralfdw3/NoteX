package com.notex.system.models.Card;

import com.notex.system.models.Company.CompanyResponse;
import com.notex.system.enums.CardStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class CardResponse {
    String id;
    String description;
    LocalDateTime creation;
    LocalDate appearance;
    CompanyResponse company;
    CardStatus status;

    public CardResponse(Card card) {
        this.id = card.getId();
        this.description = card.getDescription();
        this.creation = card.getCreation();
        this.appearance = card.getAppearance();
        this.company = new CompanyResponse(card.getCompany());
        this.status = card.getStatus();
    }
}

package com.notex.system.dto;

import com.notex.system.enums.Status;
import com.notex.system.models.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CardResponse {
    String id;
    String description;
    LocalDateTime creation;
    LocalDate appearance;
    CompanyResponse company;
    Status status;

    public CardResponse(Card card) {
        this.id = card.getId();
        this.description = card.getDescription();
        this.creation = card.getCreation();
        this.appearance = card.getAppearance();
        this.company = new CompanyResponse(card.getCompany());
        this.status = card.getStatus();
    }
}

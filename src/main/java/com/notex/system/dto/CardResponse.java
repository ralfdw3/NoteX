package com.notex.system.dto;

import com.notex.system.models.Card;
import com.notex.system.models.Company;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardResponse {
    String id;
    String title;
    String description;
    LocalDateTime creation;
    LocalDate appearance;
    Company company;

    public CardResponse(Card card) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.description = card.getDescription();
        this.creation = card.getCreation();
        this.appearance = card.getAppearance();
        this.company = card.getCompany();
    }
}

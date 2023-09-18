package com.notex.system.models.Card;

import com.notex.system.enums.CardStatus;
import com.notex.system.models.Company.Company;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "card")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Card {
    @Id
    String id;
    String description;
    LocalDateTime creation = LocalDateTime.now();
    LocalDate appearance;
    @DBRef
    Company company;
    @Field("status")
    CardStatus status;

    public Card(CardRequest request, Company company) {
        this.description = request.getDescription();
        this.appearance = request.getAppearance();
        this.company = company;
        this.status = request.getStatus();
    }

    public void updateCard(CardRequest request) {
        this.description = request.getDescription();
        this.appearance = request.getAppearance();
        this.status = request.getStatus();
    }
}

package com.notex.system.models;

import com.notex.system.dto.CardRequest;
import com.notex.system.dto.CardUpdateRequest;
import com.notex.system.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
public class Card {
    @Id
    String id;

    String description;

    LocalDateTime creation = LocalDateTime.now();

    LocalDate appearance;

    @DBRef
    Company company;

    @Field("status")
    Status status;

    public Card(CardRequest request, Company company) {
        this.description = request.getDescription();
        this.appearance = request.getAppearance();
        this.company = company;
        this.status = request.getStatus();
    }

    public void updateCard(CardUpdateRequest request, Company company) {
        this.description = request.getDescription();
        this.appearance = request.getAppearance();
        this.company = company;
        this.status = request.getStatus();
    }
}

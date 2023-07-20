package com.notex.notes.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "card")
@Data
public class Card {
    @Id
    String id;
    String title;
    String description;
    LocalDateTime creation = LocalDateTime.now();
    LocalDate aperence;
}

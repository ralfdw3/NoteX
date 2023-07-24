package com.notex.system.models;

import com.notex.system.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "card")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    String id;
    String title;
    String description;
    LocalDateTime creation = LocalDateTime.now();
    LocalDate appearance;
    Company company;
    Status status;
}

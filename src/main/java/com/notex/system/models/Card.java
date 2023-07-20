package com.notex.system.models;

import com.notex.system.enums.Status;
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
    LocalDate appearance;
    Company company;
    Status status;
}

package com.notex.system.dto;

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
}

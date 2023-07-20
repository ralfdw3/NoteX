package com.notex.system.dto;

import com.notex.system.models.Company;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CardResponse {
    String id;
    String title;
    String description;
    LocalDateTime creation;
    LocalDate appearance;
    Company company;
}

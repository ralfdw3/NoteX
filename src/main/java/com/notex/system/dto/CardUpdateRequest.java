package com.notex.system.dto;

import com.notex.system.enums.CardStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
public class CardUpdateRequest {
    @NotBlank
    String id;

    String description;

    @NotNull
    LocalDate appearance;

    @NotNull
    String companyCode;

    @NotBlank
    String companyName;

    String companyPhone;

    String companyEmail;

    @NotNull
    CardStatus status;

}

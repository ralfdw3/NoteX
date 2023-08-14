package com.notex.system.dto;

import com.notex.system.enums.CardStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class CardRequest {
    String description;

    @NotNull
    LocalDate appearance;

    @NotNull
    String companyCode;

    String companyName;

    @NotNull
    CardStatus status;

}

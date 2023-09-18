package com.notex.system.models.Card;

import com.notex.system.enums.CardStatus;
import com.notex.system.models.Company.CompanyRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class CardRequest {
    String id;
    String description;
    @NotNull
    LocalDate appearance;
    @NotNull
    CompanyRequest companyRequest;
    @NotNull
    CardStatus status;

}

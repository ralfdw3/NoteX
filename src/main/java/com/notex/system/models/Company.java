package com.notex.system.models;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "company")
@Getter
public class Company {
    @Id
    String id;
    String name;
    String code;
    LocalDateTime creation = LocalDateTime.now();
    List<Card> cards;
    Boolean status = true;

    public void updateCompanyStatus(Boolean status) {
        this.status = status;
    }

}

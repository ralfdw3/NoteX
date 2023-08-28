package com.notex.system.enums;

import lombok.Getter;

@Getter
public enum CompanyStatus {
    ATIVO("ATIVO"), INATIVO("INATIVO"), INADIMPLENTE("INADIMPLENTE");

    private final String id;

    private CompanyStatus(String id) {
        this.id = id;
    }

}

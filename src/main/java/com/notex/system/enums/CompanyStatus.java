package com.notex.system.enums;

public enum CompanyStatus {
    ATIVO("ATIVO"), INATIVO("INATIVO"), INADIMPLENTE("INADIMPLENTE");

    private final String id;

    private CompanyStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

package com.notex.system.enums;

public enum CompanyStatus {
    ACTIVE("ACTIVE"), INACTIVE("INACTIVE"), OVERDUE("OVERDUE");

    private final String id;

    private CompanyStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

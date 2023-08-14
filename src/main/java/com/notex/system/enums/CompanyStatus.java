package com.notex.system.enums;

public enum CompanyStatus {
    ACTIVE("1"), INACTIVE("2"), OVERDUE("3");

    private final String id;

    private CompanyStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

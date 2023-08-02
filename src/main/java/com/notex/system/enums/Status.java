package com.notex.system.enums;

public enum Status {
    ABERTO("1"), EM_NEGOCIACAO("2"), CONCLUIDO("3");

    private final String id;

    private Status(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

package com.notex.system.enums;

public enum CardStatus {
    ABERTO("1"), EM_NEGOCIACAO("2"), CONCLUIDO("3");

    private final String id;

    private CardStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

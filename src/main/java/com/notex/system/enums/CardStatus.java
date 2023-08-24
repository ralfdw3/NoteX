package com.notex.system.enums;

public enum CardStatus {
    ABERTO("ABERTO"), EM_NEGOCIACAO("EM_NEGOCIACAO"), CONCLUIDO("CONCLUIDO");

    private final String id;

    private CardStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

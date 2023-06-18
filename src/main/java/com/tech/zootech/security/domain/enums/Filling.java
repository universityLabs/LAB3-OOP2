package com.tech.zootech.security.domain.enums;

public enum Filling {
    CHOCOLATE(0, "Chocolate"),
    CHERRY(1, "Cherry"),
    RASPBERRY(5, "Raspberry");

    Filling(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    private final Integer id;
    private final String type;

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}

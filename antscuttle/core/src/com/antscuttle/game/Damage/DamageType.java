package com.antscuttle.game.Damage;

public enum DamageType {
    PHYSICAL("physical"),
    FIRE("fire"),
    WATER("water"),
    POISON("poison"),
    ELECTRIC("electric"),
    EXPLOSIVE("explosive");

    private final String name;

    private DamageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static DamageType fromName(String name) {
        for (DamageType type : values()) {
            if (type.getName().equals(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown damage type: " + name);
    }
}
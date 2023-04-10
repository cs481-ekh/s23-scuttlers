package com.antscuttle.game.Damage;

public enum DamageType {
    Physical("Physical"),
    Fire("Fire"),
    Water("Water"),
    Poison("Poison"),
    Electric("Electric"),
    Explosive("Explosive");

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
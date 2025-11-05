package model;

public class Magic {
    String name;
    EffectType type;
    int value;
    int manaCost;
    public Magic(String name, EffectType type, int value, int manaCost) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.manaCost = manaCost;
    }
    public int getValue() {
        return value;
    }
}

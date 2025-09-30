public class Magic {
    String name;
    EffectType type;
    int value;
    int manaCost;
    String synergyTag;
    public Magic(String name, EffectType type, int value, int manaCost, String synergyTag) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.manaCost = manaCost;
        this.synergyTag = synergyTag;
    }
}

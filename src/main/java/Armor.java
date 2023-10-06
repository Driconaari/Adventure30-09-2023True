public class Armor {
    private String name;
    private int defense; // Represents the defense value of the armor

    public Armor(String name, int defense) {
        this.name = name;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public int getDefense() {
        return defense;
    }
}
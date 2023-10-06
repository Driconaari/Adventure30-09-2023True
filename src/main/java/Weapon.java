public class Weapon {
    private static final Object attackPower = 2;
    private String description;
    private String name;
    private int damage;


    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
        this(name, "A basic weapon.", attackPower);
    }

    // Getters and setters for name and damage

    Weapon fist = new Weapon("Fist", 3); // Providing name "Fist" and attack power 3

    public Weapon(String name, String description, int damage) {
        this.name = name;
        this.description = description;
        this.damage = damage;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


public class Weapon extends Item{
    private final Object attackPower = 2;
    private String description;
    private String name;
    private int damage;


    //Weapon fist = new Weapon("Fist", "just bare hands",3); // Providing name "Fist" and attack power 3

    public Weapon(String name, String description, int damage) {
        super(name, description); // Call the constructor of the superclass
        this.damage = damage;
    }


    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


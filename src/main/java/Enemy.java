public class Enemy {
    private String name;
    private String description;
    private int health;
    private int damage;

    public Enemy(String name, String description, int health, int damage) {
        this.name = name;
        this.description = description;
        this.health = health;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            // Enemy is defeated
            health = 0; // Ensure health doesn't go negative
        }
    }
    public int attack() {
        return damage;
    }

    public boolean isDefeated() {
        return health <= 0;
    }
    public boolean isAlive() {
        return health > 0;
    }

    private int experiencePoints;

    // Constructor to initialize the enemy's properties, including experiencePoints
    public Enemy(String name, int health, int damage, int experiencePoints) {
        // Initialize other properties...
        this.experiencePoints = experiencePoints;
    }

    // Getter method for experiencePoints
    public int getExperiencePoints() {
        return experiencePoints;
    }

    public int getDamage() {
        return damage;
    }

    // Other methods and properties of the Enemy class...
}

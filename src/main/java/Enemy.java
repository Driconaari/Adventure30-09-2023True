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

    public void takeDamage(int amount) {
        health -= amount;

        if (health <= 0) {
            health = 0;
            // Handle enemy defeat (e.g., remove from room)
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

}

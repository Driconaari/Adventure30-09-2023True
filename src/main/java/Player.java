import java.util.ArrayList;
import java.util.List;

public class Player {
    public static final int SUCCESS = 1;
    public static final int NOT_FOUND = 2;
    public static final int NOT_EDIBLE = 3;
    private static final int INITIAL_HEALTH = 100;
    private int health;
    private Room currentRoom;
    private List<Item> inventory;
    private List<Enemy> enemies;

    public Player(Room startingRoom) {
        this.currentRoom = startingRoom;
        this.inventory = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.health = INITIAL_HEALTH;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public boolean removeItem(Item item) {
        return inventory.remove(item);
    }

    public Item getItemByName(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int newHealth) {
        this.health = newHealth;
    }

    public int takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
        return health;
    }

    public boolean isDefeated() {
        return health <= 0;
    }

    public void reset() {
        health = INITIAL_HEALTH;
        inventory.clear();
        currentRoom = Adventure.getInstance().getStartingRoom(); // Replace with actual method
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int eatFood(String foodName) {
        // Your eatFood method implementation

        return EatCommandOutcome.NOT_FOUND; // Replace with the correct outcome
    }

    private class EatCommandOutcome {
        public static final int SUCCESS = 1;
        public static final int NOT_FOUND = 2;
        public static final int NOT_EDIBLE = 3;
    }

    public void handleEnemyAttacks() {
        if (!enemies.isEmpty()) {
            for (Enemy enemy : enemies) {
                int damage = enemy.attack();
                takeDamage(damage);
                System.out.println("The " + enemy.getName() + " attacks you for " + damage + " damage!");
                if (isDefeated()) {
                    System.out.println("You have been defeated by " + enemy.getName() + "!");
                    // Implement game over logic here
                }
            }
        }
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
}

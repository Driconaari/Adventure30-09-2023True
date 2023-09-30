import java.util.ArrayList;
import java.util.List;

public class Player {
    private static final int INITIAL_HEALTH = 100;
    private int health;
    private Room currentRoom;
    private final List<Item> inventory;
    private final List<Enemy> enemies;


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

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
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

    public void eatFood(String foodName) {
        // Check if the specified food item exists in the current room or inventory
        Item foodItem = currentRoom.getItemByName(foodName);
        //Item foodItem = inventory.getItemByName(foodName);


        if (!(foodItem instanceof Food food)) {
            return; // Use ordinal to get the integer value
        }

        int healthPoints = food.getHealthPoints();

        if (healthPoints <= 0) {
            return; // Use ordinal to get the integer value
        }

        // Increase the player's health by 10 points if it's an apple
        if (food.getName().equalsIgnoreCase("apple")) {
            int INITIAL_HEALTH = getHealth();
            setHealth(INITIAL_HEALTH + 10);
        }

        // Remove the food item from the room or inventory
        if (currentRoom.removeItem(food)) {
        } else if (removeItem(food)) {
        }

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

    public void useItem(Item item) {
        if (item != null) {
            item.use(this, currentRoom); // Delegate the item usage to the item
        } else {
            System.out.println("You can't use that item."); // Handle the case where the item doesn't exist
        }
    }
}
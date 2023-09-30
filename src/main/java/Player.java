import java.util.ArrayList;
import java.util.List;

public class Player {
    private static final int INITIAL_HEALTH = 100; // Set the initial health value as needed
    private Room currentRoom;
    private List<Item> inventory;
    private int health;

    public Player(Room startingRoom, int i) {
        this.currentRoom = startingRoom;
        this.inventory = new ArrayList<>();
        this.health = INITIAL_HEALTH; // Initialize the player's health
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void move(Room nextRoom) {
        currentRoom = nextRoom;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public boolean removeItem(Item item) {
        if (inventory.contains(item)) {
            inventory.remove(item);
            return true; // Item was found and removed
        }
        return false; // Item was not found
    }

    public Item getItemByName(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null; // Item not found in inventory
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int newHealth) {
        this.health = newHealth;
    }

    public int takeDamage(int damage) {
        // Reduce health by the amount of damage taken
        health -= damage;

        // Ensure health doesn't go below 0
        if (health < 0) {
            health = 0;
        }

        // Return the remaining health after taking damage
        return health;
    }

    public boolean isDefeated() {
        return health <= 0;
    }

    public void reset() {
        // Reset player's health to its initial value
        health = INITIAL_HEALTH;

        // Clear the player's inventory
        inventory.clear();

        // Place the player back in the starting room (you might need a reference to the starting room)
        currentRoom = Adventure.getInstance().getStartingRoom(); // Replace Adventure.getInstance() with the actual method to get the starting room
    }

    public boolean isAlive() {
        return health > 0;
    }

}

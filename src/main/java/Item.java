import java.util.ArrayList;
import java.util.List;

public class Item {
    private String name;
    private String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name;
    }


    public void use(Player player, Room currentRoom) {
        // Implement the behavior of using this item
        // You can check the item's name or type to determine its behavior
        // For example, if it's a health potion, you can increase the player's health

        if (getName().equalsIgnoreCase("Health Potion")) {
            int currentHealth = player.getHealth();
            int healingAmount = 20; // Adjust the healing amount as needed
            int newHealth = currentHealth + healingAmount;
            player.setHealth(newHealth);
            System.out.println("You used a Health Potion and gained " + healingAmount + " health.");
        } else if (getName().equalsIgnoreCase("Key")) {
            // Implement behavior for using a key
        } else {
            System.out.println("You can't use this item.");
        }

        // Remove the item from the current room or player's inventory
        if (currentRoom.removeItem(this)) {
            System.out.println("You used " + getName() + " and it disappeared from the room.");
        } else if (player.removeItem(this)) {
            System.out.println("You used " + getName() + " from your inventory.");
        }
    }
}

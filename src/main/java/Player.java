import java.util.ArrayList;
import java.util.List;

public class Player {
    // Other player properties and methods...
    int level;

    private Room currentRoom;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private int health = 1000;


    private int experiencePoints;
    private final List<Item> inventory;

    public Player(Room startingRoom, int i) {
        // Initialize the inventory as an ArrayList with a specific initial capacity
        inventory = new ArrayList<>(100); // Adjust the capacity as needed
        // Other initialization code...
    }

    public Player(Room startingRoom, int initialHealth, List<Item> inventory) {
        this.currentRoom = startingRoom;
        this.health = initialHealth; // Initialize the health attribute
        this.inventory = inventory;
    }

    // Getter method for health
    public int getHealth() {
        return health;
    }

    // Setter method for health
    public void setHealth(int health) {
        this.health = health;
    }

    // Other methods...

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    public void setEquippedArmor(Armor equippedArmor) {
        this.equippedArmor = equippedArmor;
    }

    // Equip a weapon
    public void equipWeapon(Weapon weapon) {
        equippedWeapon = weapon;
    }

    // Unequip the currently equipped weapon
    public void unequipWeapon() {
        equippedWeapon = null;
    }

    // Equip an armor
    public void equipArmor(Armor armor) {
        equippedArmor = armor;
    }

    // Unequip the currently equipped armor
    public void unequipArmor() {
        equippedArmor = null;
    }


    // Find a weapon by name in the player's inventory
    private Weapon findWeaponByName(String weaponName) {
        for (Item item : inventory) {
            if (item instanceof Weapon && item.getName().equalsIgnoreCase(weaponName)) {
                return (Weapon) item;
            }
        }
        return null; // Weapon not found
    }


    // Assuming the player executes an "equip" command to equip a weapon
    public void equipWeaponByName(String weaponName) {
        Weapon weaponToEquip = findWeaponByName(weaponName);

        if (weaponToEquip != null) {
            equipWeapon(weaponToEquip);
            System.out.println("You have equipped " + weaponToEquip.getName() + " with damage " + weaponToEquip.getDamage() + ".");
        } else {
            System.out.println("You don't have that weapon in your inventory.");
        }
    }

    public void unequipWeaponByName() {
        if (equippedWeapon != null) {
            unequipWeapon();
            System.out.println("You have unequipped your weapon.");
        } else {
            System.out.println("You don't have any weapon equipped.");
        }
    }

    public void takeDamage(int enemyDamage) {
        health -= enemyDamage;

        if (health <= 0) {
            // Handle the case where the player's health reaches 0 or below
            System.out.println("Health: " + health + " - You are unconscious. Game Over!");
            // Add game over logic here, such as ending the game
        } else {
            System.out.println("Health: " + health);
        }
    }

    public boolean isDefeated() {
        return health <= 0;
    }


    public boolean isAlive() {
        return health > 0;
    }

    public Item getItemByName(String name) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null; // Item not found
    }

    public void eatFood(Food foodToEat) {
        // Check if the specified food item exists in the player's inventory
        Item foodItem = getItemByName(foodToEat.getName());

        if (foodItem == null) {
            System.out.println("You don't have " + foodToEat.getName() + " in your inventory.");
        } else if (foodItem instanceof Food) {
            int healthPoints = foodToEat.getHealthPoints();

            if (healthPoints <= 0) {
                System.out.println("You can't eat " + foodToEat.getName() + ".");
            } else {
                increaseHealth(healthPoints); // Increase the player's health
                removeItem(foodItem); // Remove the food item from the player's inventory
                System.out.println("You ate " + foodToEat.getName() + " and gained " + healthPoints + " health.");
            }
        } else {
            System.out.println("You can't eat that.");
        }
    }

    private void increaseHealth(int healthPoints) {
        health += healthPoints;

        // Ensure health doesn't exceed a maximum value (e.g., 100)
        if (health > 1000) {
            health = 1000;
        }

        System.out.println("Your health has increased to " + health + ".");
    }

    public boolean removeItem(Item item) {
        boolean removed = inventory.remove(item);

        if (removed) {
            System.out.println("You have removed " + item.getName() + " from your inventory.");
        } else {
            System.out.println("You don't have " + item.getName() + " in your inventory.");
        }

        return removed;
    }

    public void setCurrentRoom(Room newRoom) {
        currentRoom = newRoom;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public List<Item> getInventory() {
        return inventory;
    }


    // Getter method for experiencePoints
    public int getExperiencePoints() {
        return experiencePoints;

    }

    // Method to gain experience points
    public void gainExperience(int experiencePointsGained) {
        experiencePoints += experiencePointsGained;
        System.out.println("You gained " + experiencePointsGained + " experience points.");
    }


    public void levelUp() {
        if (shouldLevelUp()) {
            // Increase the player's level
            level++;

            // Reset the player's experience points to 0
            experiencePoints = 0;

            // Apply level-up bonuses, e.g., increase health, damage, etc.
            // You can implement the specific logic for level-up bonuses here

            // Print a message to notify the player
            System.out.println("Congratulations! You have leveled up to level " + level);
        }
    }

    public boolean shouldLevelUp() {
        // Implement your logic to determine if the player should level up
        // For example, you might check if the player has reached a certain amount of experience points

        // For demonstration purposes, let's assume the player levels up every 100 experience points
        return experiencePoints >= 100;
    }

    // Demonstration of polymorphism
    public void displayInventory() {
        for (Item item : inventory) {
            System.out.println("Item: " + item.getName());
            System.out.println("Description: " + item.getDescription());

            if (item instanceof Weapon weapon) {
                System.out.println("Damage: " + weapon.getDamage());
            } else if (item instanceof Food food) {
                System.out.println("Health Points: " + food.getHealthPoints());
            }
        }
    }

    public int calculateDamage(int attackerStrength, int weaponDamage, int modifier) {
        // Calculate damage using the attacker's strength, weapon damage, and a modifier
        int baseDamage = attackerStrength + weaponDamage;
        int totalDamage = baseDamage + modifier;


        // Ensure that damage is not negative
        if (totalDamage < 0) {
            totalDamage = 0;
        }

        return totalDamage;
    }



   /* public Enemy getEnemyByName(String enemyName) {
        for (Enemy enemy : enemies) {
            if (enemy.getName().equalsIgnoreCase(enemyName)) {
                return enemy;
            }
        }
        return null; // Enemy not found in the room
    }
    
    */
}




/*
    // Assuming the player wants to equip a weapon by name, e.g., "equip Shiny Sword"
    String ItemName  = "Shiny Sword"; // Replace with the actual weapon name
player.equipWeaponByName(itemName);

// Assuming the player wants to unequip the currently equipped weapon
player.unequipWeaponByName();

 */


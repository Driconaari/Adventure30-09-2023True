public class Food extends Item {
    private final int healthPoints;

    public Food(String name, String description, int healthPoints) {
        super(name, description);
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }


    public void use(Player player, Room currentRoom) {
        // Get the player's current health
        int currentHealth = player.getHealth();

        // Calculate the new health by adding the item's healthPoints
        int newHealth = currentHealth + getHealthPoints(); // Add healthPoints directly

        // Set the player's new health
        player.setHealth(newHealth);

        // Check if the item can be removed from the current room
        if (currentRoom.removeItem(this)) {
            System.out.println("You used " + getName() + " and gained " + getHealthPoints() + " health.");
        } else if (player.removeItem(this)) {
            System.out.println("You used " + getName() + " from your inventory and gained " + getHealthPoints() + " health.");
        }
    }

}

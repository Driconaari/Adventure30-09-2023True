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
        int newHealth = currentHealth + healthPoints;

        // Set the player's new health
        player.setHealth(newHealth);

        // Check if the item can be removed from the current room
        if (currentRoom.removeItem(this)) {
            System.out.println("You used " + getName() + " and gained " + healthPoints + " health.");
        } else if (player.removeItem(this)) {
            System.out.println("You used " + getName() + " from your inventory and gained " + healthPoints + " health.");
        }
    }

    public void eatFood(Player player, Room currentRoom, String foodName) {
        Item foodItem = currentRoom.getItemByName(foodName);

        if (!(foodItem instanceof Food food)) {
            // Food item not found or not edible
            System.out.println("You can't eat that.");
            return;
        }

        int healthPoints = food.getHealthPoints();

        if (healthPoints <= 0) {
            // Food is not edible
            System.out.println("You can't eat " + foodName + ".");
            return;
        }

        int currentHealth = player.getHealth();
        int newHealth = currentHealth + healthPoints;
        player.setHealth(newHealth); // Set the player's new health

        if (currentRoom.removeItem(food)) {
            System.out.println("You ate " + food.getName() + " and gained " + healthPoints + " health.");
        } else if (player.removeItem(food)) {
            System.out.println("You ate " + food.getName() + " from your inventory and gained " + healthPoints + " health.");
        }
    }

}

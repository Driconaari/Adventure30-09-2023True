import java.util.List;
import java.util.Scanner;


public class UserInterface {
    private Adventure adventure;
    private Player player;
    private Scanner scanner;
    private Room currentRoom;
    private final boolean[] triedDirections = {false, false, false, false}; // North, East, South, West
    private int health;

    public void startProgram() {
        // Start the game
        startGame();
    }

    //setter method
    public void setHealth(int newHealth) {
        health = newHealth;
    }


    public UserInterface() {
        // Initialize the game components
        Map map = new Map(); // Create the map
        Room startingRoom = map.getStartingRoom(); // Get the starting room from the map
        player = new Player(startingRoom); // Create the player with the starting room
        adventure = new Adventure(map, player); // Create the Adventure instance

        scanner = new Scanner(System.in);
    }

    public void attackEnemy(Enemy enemy) {
        int playerDamage  // Calculate player damage based on player's weapon, etc.
                ;

        if (enemy.isDefeated()) {
            // Handle the defeated enemy (e.g., remove from room)
        } else {
            enemyAttacksPlayer(player, enemy);
        }
    }


    public void enemyAttacksPlayer(Player player, Enemy enemy) {
        int enemyDamage = enemy.attack();
        player.takeDamage(enemyDamage);

        if (player.isDefeated()) {
            handlePlayerDefeat(); // Call the method to handle game over or other actions
        }
    }

    public void handlePlayerDefeat() {
        // Add code here to handle game over or other actions when the player is defeated.
        System.out.println("Game Over"); // Example: Display a game over message.
        // You can include any logic or actions you want to perform when the player is defeated.
    }

    public void displayHealthStatus() {
        int playerHealth = player.getHealth(); // Assuming you have a method to get the player's health
        String healthMessage = getHealthMessage(playerHealth); // Create a helper method to generate a health message

        System.out.println("Health: " + playerHealth + " - " + healthMessage);
    }

    private String getHealthMessage(int health) {
        if (health >= 80) {
            return "You are in excellent health!";
        } else if (health >= 50) {
            return "You are in good health, but avoid fighting right now.";
        } else if (health >= 20) {
            return "Your health is getting low. Be careful!";
        } else if (health > 0) {
            return "Your health is critical. Find food or a safe place quickly!";
        } else {
            return "You are unconscious. Game Over!";
        }
    }


    public UserInterface(Adventure adventure, Player player) {
        this.adventure = adventure;
        this.player = player;
        scanner = new Scanner(System.in);
        currentRoom = adventure.getStartingRoom();
    }

    public void startGame() {
        System.out.println("Welcome to the Adventure Game!\"You feel hungry and you are injured and you see a door to the east.\"");

        // Ensure that currentRoom is initialized to the starting room
        if (currentRoom == null) {
            currentRoom = adventure.getStartingRoom();
        }

        System.out.println("You are in " + currentRoom.getName() + ".");
        System.out.println("Type 'help' for a list of commands.");

        while (true) {
            String input = scanner.nextLine().toLowerCase();
            interpretCommand(input);
        }
    }


    private void interpretCommand(String input) {
        if (input.startsWith("take ") || input.startsWith("pick ")) {
            handleTakeCommand(input);
        } else if (input.startsWith("drop ")) {
            handleDropCommand(input);
        } else if (input.equals("exit")) {
            handleExitCommand();
        } else if (input.equals("help")) {
            displayHelp();
        } else if (input.equals("look")) {
            displayRoomDescription();
        } else if (input.startsWith("go ")) {
            handleGoCommand(input);
        } else if (input.equals("turn on light")) {
            // Handle turning on the light (if applicable)
        } else if (input.equals("turn off light")) {
            // Handle turning off the light (if applicable)
        } else if (input.equals("see items")) {
            displayItemsInRoom();
        } else if (input.equals("inventory")) {
            handleInventoryCommand();
        } else if (input.startsWith("eat ")) {
            String foodName = input.substring(4).toLowerCase(); // Extract the food name from the input

            // Check if the player has the specified food item in their inventory
            Item foodItem = player.getItemByName(foodName);

            if (foodItem instanceof Food) {
                Food foodToEat = (Food) foodItem; // Cast the item to Food
                eatFood(foodToEat); // Call the eatFood method with the Food object
            } else {
                System.out.println("You can't eat that.");
            }
        } else if (input.equals("health") || input.equals("hp")) {
            displayHealthStatus();
        } else {
            System.out.println("Invalid command. Type 'help' for a list of commands.");
        }

        //check enemies for in room

        List<Enemy> enemiesInRoom = adventure.getCurrentRoom().getEnemies();
        if (!enemiesInRoom.isEmpty()) {
            // Notify the player about the presence of enemies
            System.out.println("You are being attacked by enemies!");
            // Handle enemy attacks (you can implement this logic)
            Combat.enemyAttacksPlayer(player, enemiesInRoom.get(0)); // Assuming there is only one enemy for simplicity
        }



    }


    private void displayItemsInRoom() {
        List<Item> items = currentRoom.getItems();
        if (items.isEmpty()) {
            System.out.println("There are no items in this room.");
        } else {
            System.out.println("Items in the room:");
            for (Item item : items) {
                System.out.println("- " + item.getName());
            }
        }
    }

    private void handleInventoryCommand() {
        List<Item> playerInventory = player.getInventory();
        if (playerInventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (Item item : playerInventory) {
                System.out.println("- " + item.getName() + ": " + item.getDescription());
            }
        }
    }

    private void displayRoomDescription() {
        String description = currentRoom.getDescription();
        String roomName = currentRoom.getName();
        StringBuilder directions = new StringBuilder();

        if (!triedDirections[0]) {
            directions.append("North, ");
        }
        if (!triedDirections[1]) {
            directions.append("East, ");
        }
        if (!triedDirections[2]) {
            directions.append("South, ");
        }
        if (!triedDirections[3]) {
            directions.append("West, ");
        }

        if (!directions.isEmpty()) {
            directions.delete(directions.length() - 2, directions.length());
            description += " There are doors to the " + directions + ".";
        }

        System.out.println("You are in " + roomName + ". " + description);
    }

    private void displayHelp() {
        System.out.println("Commands:");
        System.out.println("  - take [item]: Pick up an item in the room.");
        System.out.println("  - drop [item]: Drop an item from your inventory.");
        System.out.println("  - go [direction]: Move in the specified direction (e.g., 'go north').");
        System.out.println("  - look: Examine the current room.");
        System.out.println("  - exit: Exit the game.");
        System.out.println("  - turn on light: Turn on a light source (if available).");
        System.out.println("  - turn off light: Turn off a light source (if available).");
        System.out.println("  - see items: View the items in the current room.");
        System.out.println("  - inventory: View your inventory.");
        System.out.println("  - Eat (item): Eats the item prescribed.");
        System.out.println("  - Hp shows player Health Points");
    }

    private void handleTakeCommand(String input) {
        String itemName = input.substring(5).trim();
        Item item = currentRoom.getItemByName(itemName);

        if (item != null) {
            player.addItem(item);
            currentRoom.removeItem(item);
            System.out.println("You picked up " + item.getName() + ".");
            handleEnemyAttacks();//Line to trigger enemy attack
        } else {
            System.out.println("There is no such item here.");
        }
    }

    private void handleDropCommand(String input) {
        String itemName = input.substring(5).trim();
        Item item = player.getItemByName(itemName);

        if (item != null) {
            player.removeItem(item);
            currentRoom.addItem(item);
            System.out.println("You dropped " + item.getName() + ".");
        } else {
            System.out.println("You don't have that item.");
        }
    }

    private void handleExitCommand() {
        System.out.println("Exiting the game. Goodbye!");
        System.exit(0);
    }

    private void handleGoCommand(String input) {
        String direction = input.substring(3).trim();
        Room nextRoom = getNextRoom(direction);

        if (nextRoom != null) {
            currentRoom = nextRoom;
            displayRoomDescription();
        } else {
            System.out.println("You cannot go that way.");
        }
    }

    private Room getNextRoom(String direction) {
        return switch (direction) {
            case "north" -> currentRoom.getNorth();
            case "east" -> currentRoom.getEast();
            case "south" -> currentRoom.getSouth();
            case "west" -> currentRoom.getWest();
            default -> null; // Invalid direction
        };


    }

    public void handleEnemyAttacks() {
        List<Enemy> enemiesInRoom = currentRoom.getEnemies();

        for (Enemy enemy : enemiesInRoom) {
            enemyAttacksPlayer(player, enemy);
        }
    }

    public void restartGame() {
        // Implement the logic to reset the game to its initial state
        // For example, reset player's health, inventory, and return to starting room
        player.reset(); // You can implement a reset method in the Player class

        currentRoom = adventure.getStartingRoom();
        System.out.println("Game restarted. You are in " + currentRoom.getName() + ".");
    }

    public void increaseHealth(int amount) {
        health += amount;
        // Ensure health doesn't exceed a maximum value (e.g., 100)
        if (health > 100) {
            health = 100;
        }
    }

    public void decreaseHealth(int amount) {
        health -= amount;
        // Ensure health doesn't go below 0
        if (health < 0) {
            health = 0;
        }
    }

    private void handleEatCommand(String input) {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            System.out.println("Usage: eat <food_name>");
            return;
        }

        String foodName = parts[1].toLowerCase();

        // Check if the specified food item exists in the player's inventory
        Item foodItem = player.getItemByName(foodName);

        if (foodItem == null) {
            System.out.println("You don't have " + foodName + " in your inventory.");
        } else if (foodItem instanceof Food) {
            Food food = (Food) foodItem; // Cast to Food
            int healthPoints = food.getHealthPoints();

            if (healthPoints <= 0) {
                System.out.println("You can't eat " + foodName + ".");
            } else {
                player.eatFood(food);
                System.out.println("You ate " + foodName + " and gained " + healthPoints + " health.");
            }
        } else {
            System.out.println("You can't eat that.");
        }
    }

    public void eatFood(Food foodToEat) {
        // Check if the specified food item exists in the player's inventory
        Item foodItem = player.getItemByName(foodToEat.getName());

        if (foodItem == null) {
            System.out.println("You don't have " + foodToEat.getName() + " in your inventory.");
        } else if (foodItem instanceof Food) {
            int healthPoints = foodToEat.getHealthPoints();

            if (healthPoints <= 0) {
                System.out.println("You can't eat " + foodToEat.getName() + ".");
            } else {
                player.eatFood(foodToEat);
                System.out.println("You ate " + foodToEat.getName() + " and gained " + healthPoints + " health.");
            }
        } else {
            System.out.println("You can't eat that.");
        }
    }


}

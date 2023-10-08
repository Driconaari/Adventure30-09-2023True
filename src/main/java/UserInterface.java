import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class UserInterface {
    private final Adventure adventure;
    private Player player;
    private final Scanner scanner;

    private Room currentRoom;

    private Room startingRoom;

    public void startProgram() {
        // Start the game
        startGame();
    }


    //setter method


    public UserInterface() {
        // Initialize the game components
        Map map = new Map(); // Create the map
        startingRoom = map.getStartingRoom(); // Get the starting room from the map
        player = new Player(startingRoom, 1000); // Create the player with the starting room and 1000 health

        adventure = new Adventure(map, player); // Create the Adventure instance
        scanner = new Scanner(System.in);
        currentRoom = adventure.getStartingRoom();
    }


    public void enemyAttacksPlayer(Player player, Enemy enemy) {
        int enemyDamage = enemy.attack();
        player.takeDamage(enemyDamage);

        if (player.isDefeated()) {
            handlePlayerDefeat(); // Call the method to handle game over or other actions
        }
    }

    private void handlePlayerAttack(Enemy enemy) {
        // Replace these values with the actual attacker's strength, weapon damage, and modifier
        int attackerStrength = 10;
        int weaponDamage = 8;
        int modifier = 2;

        // Calculate the player's damage using the calculateDamage method
        int damage = player.calculateDamage(attackerStrength, weaponDamage, modifier);
        //int damage = player.calculateDamage();

        // Apply the calculated damage to the enemy
        enemy.takeDamage(damage);

        // Check if the enemy is defeated
        if (enemy.isDefeated()) {
            currentRoom.removeEnemy(enemy); // Remove the defeated enemy from the room
            System.out.println("You have defeated the " + enemy.getName() + "!");
        } else {
            System.out.println("You attack the " + enemy.getName() + " and deal " + damage + " damage.");
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

    public void startGame() {
        System.out.println("Welcome to the Adventure Game! You feel hungry and you are injured and you see a door to the east.");

        // Ensure that currentRoom is initialized to the starting room
        if (currentRoom == null) {
            currentRoom = adventure.getStartingRoom();
        }

        System.out.println("You are in " + currentRoom.getName() + ".");
        System.out.println("Type 'help' for a list of commands.");

        boolean gameRunning = true; // Add a flag to control the game loop

        while (gameRunning) {
            String input = scanner.nextLine().toLowerCase();
            interpretCommand(input); // Update gameRunning based on the result of interpretCommand
        }

        // Game over or exit message
        System.out.println("Game over. Thanks for playing!");
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
        } else if (input.startsWith("attack ")) {
            // Handle player's attack
            String enemyName = input.substring(7).toLowerCase(); // Extract the enemy name from the input

            // Check if the enemy exists in the room
            Enemy enemy = currentRoom.getEnemyByName(enemyName);

            if (enemy != null) {
                // Perform the attack
                handlePlayerAttack(enemy);
            } else {
                System.out.println("There is no such enemy here.");
            }
        } else if (input.equals("sneak")) {
            // Handle player's attempt to sneak
            handleSneak();
        } else {
            System.out.println("Invalid command. Type 'help' for a list of commands.");
            if (input.startsWith("equip ")) {
                String weaponName = input.substring(6); // Extract the weapon name
                player.equipWeaponByName(weaponName);
            }
        }
        if (input.equals("unequip")) {
            player.unequipWeaponByName();
        }
    }

    private void handleSneak() {
        // Generate a random number to determine the success of the sneak attempt
        int diceRoll = rollDice(20); // You can adjust the number of sides as needed
        System.out.println("You attempt to sneak past...");

        if (diceRoll >= 10) {
            System.out.println("You successfully sneak past the enemies!");
            // Implement actions for a successful sneak here, if needed
        } else {
            System.out.println("You failed to sneak past the enemies!");
            // Implement consequences of failing to sneak here, such as enemy attacks or other events
        }
    }


    //check enemies for in room


   /* List<Enemy> enemiesInRoom = currentRoom.getEnemies();

    if(enemiesInRoom !=null&&!enemiesInRoom.isEmpty())

    {
        // Notify the player about the presence of enemies
        System.out.println("You are being attacked by enemies!");
        // Handle enemy attacks (you can implement this logic)
        Combat.enemyAttacksPlayer(player, enemiesInRoom.get(0)); // Assuming there is only one enemy for simplicity
    }

    */


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

        boolean[] triedDirections = new boolean[4]; // Initialize with a length of 4

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
            player.setCurrentRoom(nextRoom); // Update the player's current room
            displayRoomDescription(); // Display the description of the new room

            // Check if there are enemies in the new room
            if (nextRoom.hasEnemies()) {
                enterRoomWithEnemies(nextRoom);
            }
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


//Enemy interaction


    // Inside your code where the player enters a new room
    private void playerEntersNewRoom(Room newRoom) {
        // Change the current room to the new room
        player.setCurrentRoom(newRoom);

        // Check if there are enemies in the new room
        List<Enemy> enemiesInRoom = newRoom.getEnemies();

        if (!enemiesInRoom.isEmpty()) {
            // Notify the player about the presence of enemies
            enterRoomWithEnemies(newRoom);
            System.out.println("You entered a room with enemies!");

            // Display the room description
            displayRoomDescription();

            // Prompt the player to choose an action
            boolean commandSuccessful = false; // Initialize to false
            while (!commandSuccessful) {
                System.out.println("What will you do?");
                System.out.println("1. Attack the enemy");
                System.out.println("2. Try to sneak past");

                // Read the player's choice
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {
                        // Player chooses to attack the enemy
                        int damage = Combat.calculatePlayerDamage(player); // Calculate player's damage using Combat class
                        System.out.println("You attack the enemy and deal " + damage + " damage!");

                        // Get the list of enemies in the current room
                        enemiesInRoom = currentRoom.getEnemies();

                        if (!enemiesInRoom.isEmpty()) {
                            // Apply the damage to the enemy
                            Enemy enemy = enemiesInRoom.get(0); // Assuming there is only one enemy for simplicity
                            enemy.takeDamage(damage);

                            // Check if the enemy is defeated
                            if (enemy.isDefeated()) {
                                // Remove defeated enemy from the room
                                currentRoom.removeEnemy(enemy);
                            }
                        }

                        // Set commandSuccessful to true to exit the loop
                        commandSuccessful = true;
                    }
                    case 2 -> {
                        // Player chooses to try to sneak past
                        boolean sneakingSuccessful = sneakPastEnemy();
                        if (sneakingSuccessful) {
                            System.out.println("You successfully sneak past the enemy!");
                        } else {
                            System.out.println("You failed to sneak past the enemy!");
                            // Implement consequences of failing to sneak here
                        }

                        // Set commandSuccessful to true to exit the loop
                        commandSuccessful = true;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            }
        } else {
            // Handle the case where there are no enemies
            System.out.println("The room is empty.");
            displayRoomDescription(); // You can display the room description for empty rooms
        }

        // You can perform other actions related to entering the room here
    }


    //sneak past enemy option with dice roll
    private boolean sneakPastEnemy() {
        int diceRoll = rollDice(20); // Roll a 20-sided dice
        System.out.println("You roll a 20-sided dice and get: " + diceRoll);

        // Check if the dice roll is 4 or higher
        return diceRoll >= 4;
    }

    private int rollDice(int sides) {
        Random random = new Random();
        return random.nextInt(sides) + 1; // Add 1 to avoid getting a 0
    }

    int attackerStrength = 10; // Replace with the actual attacker's strength
    int weaponDamage = 8;     // Replace with the actual weapon's damage
    int modifier = 2;         // Replace with any applicable modifier


    public void enterRoomWithEnemies(Room room) {
        List<Enemy> enemiesInRoom = room.getEnemies();

        if (!enemiesInRoom.isEmpty()) {
            // Notify the player about the presence of enemies
            System.out.println("You are being attacked by enemies!");

            // Start turn-based combat
            Iterator<Enemy> iterator = enemiesInRoom.iterator();
            while (iterator.hasNext() && player.isAlive()) {
                Enemy currentEnemy = iterator.next();
                int playerDamage = player.calculateDamage(attackerStrength, weaponDamage, modifier); // Calculate player's damage
                currentEnemy.takeDamage(playerDamage); // Apply damage to the enemy
                System.out.println("You attack the enemy and deal " + playerDamage + " damage!");

                // Check if the enemy is defeated
                if (currentEnemy.isDefeated()) {
                    // Remove defeated enemy using the iterator's remove method
                    iterator.remove();
                    System.out.println("You have defeated the enemy!");
                }
            }

// Enemy's turn to attack
            if (!enemiesInRoom.isEmpty() && player.isAlive()) {
                Enemy currentEnemy = enemiesInRoom.get(0); // Get the first enemy in the room

                // Check if there are enemies in the room before attacking
                if (!enemiesInRoom.isEmpty()) {
                    int enemyDamage = currentEnemy.attack(); // Get enemy's damage
                    player.takeDamage(enemyDamage); // Apply damage to the player
                    System.out.println("The enemy attacks you and deals " + enemyDamage + " damage!");

                    // Check if the player is defeated
                    if (player.isDefeated()) {
                        System.out.println("Game Over! You have been defeated.");
                        return; // Exit the method or handle game over logic
                    }

                    // Check if the enemy is defeated
                    if (currentEnemy.isDefeated()) {
                        // Remove defeated enemy from the room
                        room.removeEnemy(currentEnemy);
                        enemiesInRoom.remove(0);
                        System.out.println("You have defeated the enemy!");
                    }
                } else {
                    System.out.println("There are no enemies in the room.");
                }
            }


        }
    }
}


// Enemy's turn to attack
                /*(if (!enemiesInRoom.isEmpty() && player.isAlive()) {
                    Enemy currentEnemy = enemiesInRoom.get(0); // Get the first enemy in the room
                    int enemyDamage = currentEnemy.attack(); // Get enemy's damage
                    player.takeDamage(enemyDamage); // Apply damage to the player
                    System.out.println("The enemy attacks you and deals " + enemyDamage + " damage!");

                    // Check if the player is defeated
                    if (player.isDefeated()) {
                        System.out.println("Game Over! You have been defeated.");
                    }

                 */




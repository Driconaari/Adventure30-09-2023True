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
        currentRoom = adventure.getStartingRoom(); // Set the current room to the starting room
    }

    /*public UserInterface() {
        // Initialize the game components
        Map map = new Map(); // Create the map
        startingRoom = map.getStartingRoom(); // Get the starting room from the map
        player = new Player(startingRoom, 1000); // Create the player with the starting room and 1000 health

        adventure = new Adventure(map, player); // Create the Adventure instance
        scanner = new Scanner(System.in);
        currentRoom = adventure.getStartingRoom();
    }

     */


    public void enemyAttacksPlayer(Player player, Enemy enemy) {
        int enemyDamage = enemy.attack();
        player.takeDamage(enemyDamage);

        if (player.isDefeated()) {
            handlePlayerDefeat(); // Call the method to handle game over or other actions
        }
    }

    private void handlePlayerAttack(String enemyName) {
        // Check if the enemy exists in the current room
        Enemy enemy = currentRoom.getEnemyByName(enemyName.toLowerCase());

        if (enemy != null) {
            // Replace these values with the actual attacker's strength, weapon damage, and modifier
            int attackerStrength = 10;
            int weaponDamage = 8;
            int modifier = 2;

            // Calculate the player's damage using the calculateDamage method
            int damage = player.calculateDamage(attackerStrength, weaponDamage, modifier);

            // Apply the calculated damage to the enemy
            enemy.takeDamage(damage);

            if (enemy.isDefeated()) {
                currentRoom.removeEnemy(enemy); // Remove the defeated enemy from the room
                System.out.println("You have defeated the " + enemy.getName() + "!");
            } else {
                System.out.println("You attack the " + enemy.getName() + " and deal " + damage + " damage.");
            }
        } else {
            System.out.println("There is no such enemy here.");
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
            // Handle player input and update the player's current room
            String input = scanner.nextLine(); // Read player input
            handlePlayerInput(input); // Handle player input and update the player's current room

            // Update newRoom after handling player input
            Room newRoom = player.getCurrentRoom(); // Get the new room the player entered

            // Check if there are enemies in the new room
            if (newRoom != null && !newRoom.getEnemies().isEmpty()) {
                playerEntersNewRoom(newRoom); // Call the method for combat
            }
        }

        // Game over or exit message
        System.out.println("Game over. Thanks for playing!");
    }





    /*private void handlePlayerInput(String input) {
        if (input.equals("go east")) {
            // Handle the "go east" command to move to the next room
            Room nextRoom = currentRoom.getEast(); // Assuming you have a getEast() method in your Room class
            if (nextRoom != null) {
                currentRoom = nextRoom;
                System.out.println("You move to the east.");
            } else {
                System.out.println("You cannot go that way.");
            }
        } else if (input.equals("go west")) {
            // Handle the "go west" command similarly for other directions
        } else if (input.equals("help")) {
            // Handle the "help" command to display a list of commands
            System.out.println("List of available commands:");
            System.out.println("go [direction] - Move to a different room");
            System.out.println("look - Examine your surroundings");
            // Add more commands as needed
        } else {
            // Handle unrecognized commands or display an error message
            System.out.println("Unrecognized command. Type 'help' for a list of commands.");
        }
    }

     */


    private void handlePlayerInput(String input) {
        if (input.equals("go east")) {
            // Handle the "go east" command to move to the next room
            Room nextRoom = currentRoom.getEast(); // Assuming you have a getEast() method in your Room class
            if (nextRoom != null) {
                currentRoom = nextRoom;
                System.out.println("You move to the east.");
            } else {
                System.out.println("You cannot go that way.");
            }
        } else if (input.startsWith("take ") || input.startsWith("pick ")) {
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
        } else if (input.equals("attack")) {
            // Attempt to attack all enemies in the current room
            List<Enemy> enemiesInRoom = currentRoom.getEnemies();

            if (!enemiesInRoom.isEmpty()) {
                for (Enemy enemy : enemiesInRoom) {
                    // Perform the attack on each enemy
                    handlePlayerAttack(enemy.getName()); // Pass the enemy's name as a string
                }
            } else if (input.startsWith("attack ")) {
                // Handle player's attack
                String enemyName = input.substring(7).toLowerCase(); // Extract the enemy name from the input

                // Check if the enemy exists in the room
                Enemy enemy = currentRoom.getEnemyByName(enemyName);

                if (enemy != null) {
                    // Perform the attack
                    handlePlayerAttack(enemy.getName()); // Pass the enemy's name as a string
                } else {
                    System.out.println("There is no such enemy here.");
                }
            }


        } else if (input.equals("sneak")) {
            // Handle player's attempt to sneak
            sneakPastEnemy();
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
        System.out.println("You are in " + currentRoom.getName() + ".");
        System.out.println(currentRoom.getDescription());

        // Check if there are enemies in the room
        List<Enemy> enemiesInRoom = currentRoom.getEnemies();
        if (!enemiesInRoom.isEmpty()) {
            System.out.println("You see the following enemies in the room:");
            for (Enemy enemy : enemiesInRoom) {
                System.out.println("- " + enemy.getName());
            }
        }
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
        System.out.println("  - Eat [item]: Eat the specified item from your inventory.");
        System.out.println("  - health (or hp): Show your current Health Points.");
        System.out.println("  - attack [enemy]: Attack an enemy in the room.");
        System.out.println("  - sneak: Attempt to sneak past enemies.");
        System.out.println("  - equip [weapon]: Equip a weapon from your inventory.");
        System.out.println("  - unequip: Unequip your current weapon.");
        System.out.println("Type 'help [command]' for more details on a specific command.");
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
        Room currentRoom = player.getCurrentRoom(); // Get the current room

        // Check if the current room has a valid connection in the specified direction
        if (currentRoom.hasConnection(direction)) {
            Room nextRoom = currentRoom.getConnectedRoom(direction);
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
            enterRoomWithEnemies(newRoom, "attack");
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


    public void enterRoomWithEnemies(Room room, String input) {
        List<Enemy> enemiesInRoom = room.getEnemies();

        if (!enemiesInRoom.isEmpty()) {
            Iterator<Enemy> iterator = enemiesInRoom.iterator();
            while (iterator.hasNext()) {
                Enemy enemy = iterator.next();
                // Perform the attack on each enemy
                handlePlayerAttack("attack " + enemy.getName()); // Pass the enemy's name as a string

                if (enemy.isDefeated()) {
                    iterator.remove(); // Remove the defeated enemy from the collection
                    System.out.println("You have defeated the " + enemy.getName() + "!");
                }
            }
        } else if (input.startsWith("attack ")) {
            // Handle player's attack
            String enemyName = input.substring(7).toLowerCase(); // Extract the enemy name from the input

            // Check if the enemy exists in the room
            Enemy enemy = room.getEnemyByName(enemyName);

            if (enemy != null) {
                // Perform the attack
                handlePlayerAttack("attack " + enemy.getName()); // Pass the enemy's name as a string

                if (enemy.isDefeated()) {
                    room.removeEnemy(enemy); // Remove the defeated enemy from the room
                    System.out.println("You have defeated the " + enemy.getName() + "!");
                }
            } else {
                System.out.println("There is no such enemy here.");
            }
        }
    }
}







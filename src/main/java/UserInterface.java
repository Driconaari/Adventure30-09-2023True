import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Adventure adventure;
    private final Player player;
    private Scanner scanner;
    private Room currentRoom;
    private boolean[] triedDirections = {false, false, false, false}; // North, East, South, West

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


    public UserInterface(Adventure adventure, Player player) {
        this.adventure = adventure;
        this.player = player;
        scanner = new Scanner(System.in);
        currentRoom = adventure.getStartingRoom();
    }

    public void startGame() {
        System.out.println("Welcome to the Adventure Game!");
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
        } else {
            System.out.println("Invalid command. Type 'help' for a list of commands.");
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

        if (directions.length() > 0) {
            directions.delete(directions.length() - 2, directions.length()); // Remove the trailing comma and space
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
        switch (direction) {
            case "north":
                return currentRoom.getNorth();
            case "east":
                return currentRoom.getEast();
            case "south":
                return currentRoom.getSouth();
            case "west":
                return currentRoom.getWest();
            default:
                return null; // Invalid direction
        }


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

}
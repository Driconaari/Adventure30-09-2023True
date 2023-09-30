public class Main {
    public static void main(String[] args) {
        Map map = new Map(); // Create the map
        Room startingRoom = map.getStartingRoom(); // Get the starting room from the map
        int initialHealth = 100; // Specify the initial health here
        Player player = new Player(startingRoom, initialHealth); // Create the player with the starting room and initial health
        Adventure adventure = new Adventure(map, player); // Create the Adventure instance
        UserInterface ui = new UserInterface(adventure, player); // Pass the player to the UserInterface
        ui.startGame();
    }
}

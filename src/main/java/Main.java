public class Main {
    public static void main(String[] args) {
        Map map = new Map(); // Create the map
        Room startingRoom = map.getStartingRoom(); // Get the starting room from the map
        Player player = new Player(startingRoom); // Create the player with the starting room
        Adventure adventure = new Adventure(map, player); // Create the Adventure instance
        UserInterface ui = new UserInterface(adventure, player); // Pass the player to the UserInterface
        ui.startGame();
    }
}
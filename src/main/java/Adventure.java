public class Adventure {
    private Room currentRoom;
    private static Map map;
    private static Player player; // Add a player field

    // Singleton instance
    private static Adventure instance;

    // Private constructor to prevent direct instantiation
    Adventure(Map map, Player player) {
        this.map = map;
        this.player = player; // Initialize the player field
        currentRoom = map.getStartingRoom(); // Use the map instance to build the game map
    }

    // Public method to obtain the instance (create it if it doesn't exist)
    public static Adventure getInstance() {
        if (instance == null) {
            instance = new Adventure(map, player);
        }
        return instance;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    public Room getStartingRoom() {
        return map.getStartingRoom(); // Return the starting room from the map
    }
}

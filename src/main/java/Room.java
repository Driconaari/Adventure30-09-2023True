import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    private String description;
    private Room north;
    private Room east;
    private Room south;
    private Room west;
    private List<Enemy> enemies;
    private List<Item> items;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        items = new ArrayList<>();
        enemies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Room getNorth() {
        return north;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public Room getEast() {
        return east;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public Room getSouth() {
        return south;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public Room getWest() {
        return west;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    public Item getItemByName(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null; // Item not found
    }

    // Enemy methods

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public boolean hasEnemies() {
        return !enemies.isEmpty();
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public Enemy getEnemyByName(String targetEnemyName) {
        for (Enemy enemy : enemies) {
            if (enemy.getName().equalsIgnoreCase(targetEnemyName)) {
                return enemy;
            }
        }
        return null; // Enemy not found in the room
    }

    public boolean hasConnection(String direction) {
        switch (direction.toLowerCase()) {
            case "north":
                return north != null;
            case "east":
                return east != null;
            case "south":
                return south != null;
            case "west":
                return west != null;
            default:
                return false; // Invalid direction
        }
    }

    public Room getConnectedRoom(String direction) {
        switch (direction.toLowerCase()) {
            case "north":
                return north;
            case "east":
                return east;
            case "south":
                return south;
            case "west":
                return west;
            default:
                return null; // invalid direction or no connection
        }
    }


}

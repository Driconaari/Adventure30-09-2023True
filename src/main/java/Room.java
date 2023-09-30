import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    private String description;
    private Room north;
    private Room east;
    private Room south;
    private Room west;
    private List<Item> items; // List to store items in the room

    private List<Enemy> enemies = new ArrayList<>();


    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        items = new ArrayList<>(); // Initialize the list of items
    }

    public Room(String name, String description, List<Item> items) {
        this.name = name;
        this.description = description;
        this.items = items;
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
        if (items.contains(item)) {
            items.remove(item);
            return true; // Item was found and removed
        }
        return false; // Item was not found
    }

    public Item getItemByName(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null; // Item not found in the room
    }


    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
}

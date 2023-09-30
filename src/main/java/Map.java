public class Map {
    public Room buildMap() {
        // Create room objects and build the game map
        Room room1 = new Room("Room 1", "A room with no distinct features.");
        Room room2 = new Room("Room 2", "A dimly lit room with a musty smell.");
        Room room3 = new Room("Room 3", "A large room with a high ceiling.");
        Room room4 = new Room("Room 4", "A mysterious room filled with strange artifacts.");
        Room room5 = new Room("Room 5", "A hidden chamber with a single exit to the east.");
        Room room6 = new Room("Room 6", "A narrow corridor with flickering torches.");
        Room room7 = new Room("Room 7", "An underground cave with a shimmering pool.");
        Room room8 = new Room("Room 8", "A dusty library with rows of ancient books.");
        Room room9 = new Room("Room 9", "A secret chamber containing a treasure chest.");

        // Create items for each room
        Item sword1 = new Item("Shiny Sword", "A sharp and shiny sword.");
        Item dagger1 = new Item("Dagger", "A small, sharp dagger.");
        Item roboticEye1 = new Item("Robotic Eye", "A weird-looking robotic eye.");

        Item potion = new Item("Health Potion", "A red potion that restores health.");
        Item key = new Item("Golden Key", "A golden key with intricate engravings.");
        Item lantern = new Item("Magic Lantern", "A lantern that emits a magical glow.");

        Item gemstone = new Item("Precious Gemstone", "A glittering gemstone of great value.");
        Item map = new Item("Ancient Map", "An old map with cryptic markings.");
        Item scroll = new Item("Mystic Scroll", "A scroll containing mysterious incantations.");

        //Add enemy
        // Create an Enemy
        Enemy enemy = new Enemy("Goblin", "A menacing goblin with a rusty sword.", 23, 11);

        room1.addEnemy(new Enemy("Goblin", "A small goblin with a rusty sword.", 20, 5));


        // Add items to rooms
        room1.addItem(sword1);
        room2.addItem(dagger1);
        room3.addItem(roboticEye1);

        room4.addItem(potion);
        room5.addItem(key);
        room6.addItem(lantern);

        room7.addItem(gemstone);
        room8.addItem(map);
        room9.addItem(scroll);

        // Connect rooms according to your descriptions
        room1.setNorth(room3);
        room1.setEast(room2);
        room1.setSouth(room4);
        room1.setWest(room6);

        room2.setWest(room1);
        room2.setSouth(room5);

        room3.setSouth(room1);
        room3.setEast(room4);
        room3.setWest(room7);

        room4.setNorth(room1);
        room4.setWest(room3);
        room4.setSouth(room7);
        room4.setEast(room8);

        room5.setNorth(room2);
        room5.setEast(room9);

        room6.setEast(room1);
        room6.setSouth(room9);

        room7.setEast(room8);
        room7.setSouth(room9);

        room8.setWest(room4);
        room8.setWest(room7);

        room9.setNorth(room6);
        room9.setEast(room5);

        return room1;

    }

    public Room getStartingRoom() {
        Room startingRoom = new Room("Starting Room", "This is where your adventure begins.");
        startingRoom.setEast(buildMap()); // Connect the starting room to Room 1
        startingRoom.addItem(new Item("Shiny Sword", "A sharp and shiny sword.")); // Add a new sword to the starting room
        return startingRoom;
    }
}







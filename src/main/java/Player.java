import java.util.ArrayList;
import java.util.List;

public class Player {
    // Other player properties and methods...

    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private Item[] inventory; // Use the Item[] for inventory

    public Player(Room startingRoom) {
        // Initialize the inventory array with a specific size
        inventory = new Item[10]; // Adjust the capacity as needed
        // Other initialization code...
    }

    // Other methods...

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    public void setEquippedArmor(Armor equippedArmor) {
        this.equippedArmor = equippedArmor;
    }

    // Equip a weapon
    public void equipWeapon(Weapon weapon) {
        equippedWeapon = weapon;
    }

    // Unequip the currently equipped weapon
    public void unequipWeapon() {
        equippedWeapon = null;
    }

    // Equip an armor
    public void equipArmor(Armor armor) {
        equippedArmor = armor;
    }

    // Unequip the currently equipped armor
    public void unequipArmor() {
        equippedArmor = null;
    }

    // Find a weapon by name in the player's inventory
    public Weapon findWeaponByName(String weaponName) {
        for (Item item : inventory) {
            if (item != null && item instanceof Weapon && item.getName().equalsIgnoreCase(weaponName)) {
                return (Weapon) item;
            }
        }
        return null; // Weapon not found
    }

    // Assuming the player executes a "equip" command to equip a weapon
    public void equipWeaponByName(String weaponName) {
        Weapon weaponToEquip = findWeaponByName(weaponName);

        if (weaponToEquip != null) {
            equipWeapon(weaponToEquip);
            System.out.println("You have equipped " + weaponToEquip.getName() + ".");
        } else {
            System.out.println("You don't have that weapon in your inventory.");
        }
    }

    // Assuming the player executes an "unequip" command to unequip the current weapon
    public void unequipWeaponByName() {
        if (equippedWeapon != null) {
            unequipWeapon();
            System.out.println("You have unequipped your weapon.");
        } else {
            System.out.println("You don't have any weapon equipped.");
        }
    }
}
public class Combat {
    public static void playerAttacksEnemy(Player player, Enemy enemy) {
        // Calculate player's damage
        int damage = player.getEquippedWeapon().getDamage();
        // Apply damage to the enemy
        enemy.takeDamage(damage);
        System.out.println("You attack the enemy and deal " + damage + " damage!");
        // Check if the enemy is defeated and remove them from the room if necessary
        if (enemy.isDefeated()) {
            enemyDefeated(player, enemy);
        }
    }

    private static void enemyDefeated(Player player, Enemy enemy) {
        // Remove the defeated enemy from the current room
        player.getCurrentRoom().removeEnemy(enemy);

        // Award the player some experience points or rewards for defeating the enemy
        int experiencePointsGained = enemy.getExperiencePoints();
        player.gainExperience(experiencePointsGained);
        System.out.println("You defeated the enemy and gained " + experiencePointsGained + " experience points!");

        // Check if the player has leveled up and perform level-up logic if needed
        if (player.shouldLevelUp()) {
            player.levelUp();
            System.out.println("Congratulations! You have leveled up!");
        }
    }

    public static void enemyAttacksPlayer(Player player, Enemy enemy) {
        // Calculate enemy's damage
        int damage = enemy.getDamage();
        // Apply damage to the player
        player.takeDamage(damage);
        System.out.println("The enemy attacks you and deals " + damage + " damage!");
    }

    private static void handlePlayerDefeat(Player player) {
        System.out.println("Game Over! You have been defeated.");
        System.out.println("Do you want to restart the game? (yes/no)");
    }


    private static void handleEnemyDefeat(Enemy enemy) {
        // Handle enemy defeat (e.g., remove from the room, provide rewards).
        // You can implement your own logic here.
    }

    public static int calculatePlayerDamage(Player player) {
        int baseDamage = 10; // Adjust this value as needed

        // Check if the player has an equipped weapon
        if (player.getEquippedWeapon() != null) {
            baseDamage += player.getEquippedWeapon().getDamage();
        }

        // You can also factor in other player attributes or abilities that affect damage.

        // Calculate the total player damage
        int totalDamage = baseDamage;

        return totalDamage;
    }

}

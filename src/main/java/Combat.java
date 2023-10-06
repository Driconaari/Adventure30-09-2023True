public class Combat {
    public static void playerAttacksEnemy(Player player, Enemy enemy) {
        int playerDamage = calculatePlayerDamage(player);
        enemy.takeDamage(playerDamage);

        if (enemy.isDefeated()) {
            handleEnemyDefeat(enemy);
        } else {
            enemyAttacksPlayer(player, enemy);
        }
    }

    public static void enemyAttacksPlayer(Player player, Enemy enemy) {
        int enemyDamage = enemy.attack();
        player.takeDamage(enemyDamage);

        if (player.isDefeated()) {
            handlePlayerDefeat(player);
        }
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

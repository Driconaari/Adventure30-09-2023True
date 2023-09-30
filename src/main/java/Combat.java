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


    private static int calculatePlayerDamage(Player player) {
        // Calculate player damage based on player's weapon, abilities, etc.
        // You can implement your own logic here.
        return 0;
    }

    private static void handleEnemyDefeat(Enemy enemy) {
        // Handle enemy defeat (e.g., remove from the room, provide rewards).
        // You can implement your own logic here.
    }

}

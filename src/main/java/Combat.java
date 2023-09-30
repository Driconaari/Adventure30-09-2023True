import java.util.List;

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

    private static int calculatePlayerDamage(Player player) {
        // Calculate player damage based on player's weapon, abilities, etc.
        // You can implement your own logic here.
        return /* calculated damage */;
    }

    private static void handleEnemyDefeat(Enemy enemy) {
        // Handle enemy defeat (e.g., remove from the room, provide rewards).
        // You can implement your own logic here.
    }

    private static void handlePlayerDefeat(Player player) {
        // Handle player defeat (e.g., game over, restart).
        // You can implement your own logic here.
    }
}

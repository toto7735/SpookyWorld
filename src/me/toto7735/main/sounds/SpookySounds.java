package me.toto7735.main.sounds;

import me.toto7735.main.SpookyWorld;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpookySounds {

    private Player player;
    public SpookySounds(Player player) {
        this.player = player;
    }

    // todo add more sounds; toto7735 will do it
    public void playScaryZombieEventStartSound() {
        new BukkitRunnable() {
            float a = 0.5F;
            public void run() {
                if (a > 1.5) {
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_AMBIENT, 1, 2);
                    this.cancel();
                }
                player.playSound(player.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, a);
                a += 0.1F;
            }
        }.runTaskTimerAsynchronously(SpookyWorld.getInstance(), 0, 1);
    }

    public void playSpookyZombieSound() {
        new BukkitRunnable() {
            float a = 0.5F;
            public void run() {
                if (a > 1.5) this.cancel();
                player.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_VILLAGER_CONVERTED, 1, a);
                player.playSound(player.getLocation(), Sound.BLOCK_PORTAL_AMBIENT, 1, a);
                a += 0.1F;
            }
        }.runTaskTimerAsynchronously(SpookyWorld.getInstance(), 0, 1);
    }


}

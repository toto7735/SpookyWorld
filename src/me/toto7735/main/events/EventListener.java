package me.toto7735.main.events;

import me.toto7735.main.SpookyWorld;
import me.toto7735.main.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class EventListener implements Listener {

    public EventListener() {
        Bukkit.getPluginManager().registerEvents(this, SpookyWorld.getInstance());
    }

    @EventHandler
    public void onInteractAtEntity(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked().getCustomName() == null || !event.getRightClicked().getCustomName().equals("§0TREAT OR TRICK!")) return;
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand() == null || !player.getInventory().getItemInMainHand().hasItemMeta() ||
                !player.getInventory().getItemInMainHand().getItemMeta().hasDisplayName() || !player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§cS§6p§eo§ao§bk§9y §dC§5a§3n§2d§1y")) return;
        event.getRightClicked().getWorld().playSound(event.getRightClicked().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 10, 1);
        event.getRightClicked().getWorld().spawnParticle(Particle.EXPLOSION_HUGE, event.getRightClicked().getLocation(), 1);
        event.getRightClicked().remove();
        Utils.consumeItemInHand(player, 1);
    }

}

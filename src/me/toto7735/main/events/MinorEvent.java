package me.toto7735.main.events;

import me.toto7735.main.sounds.SpookySounds;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MinorEvent {


    private EventType eventType;

    public MinorEvent(EventType eventType) {
        if (eventType.isMajor) throw new RuntimeException("The event type in MinorEvent instance must be a minor");
        this.eventType = eventType;
    }

    public void fire() {
        // Don't do Event.fireEvent(); by toto7735
        if (this.getEventType().equals(EventType.SCARY_ZOMBIES)) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage("§6[HALLOWEEN] §cTHE TREAT OR TRICK ZOMBIES APPEARED!");
                for (int i = 0; i < 10; ++i) {
                    LivingEntity entity = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
                    entity.setCustomName("§0TREAT OR TRICK!");
                    ((Mob) entity).setTarget(player);
                    entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(3F);
                    entity.setMaxHealth(100);
                    entity.setHealth(100);
                    entity.setCanPickupItems(false);
                    entity.getEquipment().setHelmet(new ItemStack(Material.JACK_O_LANTERN));
                }
                new SpookySounds(player).playScaryZombieEventStartSound();
            }
        }
    }

    public EventType getEventType() {
        return this.eventType;
    }
}

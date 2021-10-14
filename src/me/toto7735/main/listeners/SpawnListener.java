package me.toto7735.main.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;

import me.toto7735.main.SpookyWorld;

public class SpawnListener implements Listener {
	
	public SpawnListener() {
		Bukkit.getPluginManager().registerEvents(this, SpookyWorld.getInstance());
	}
	
	@EventHandler
	public void onSpawn(CreatureSpawnEvent e) {
		e.getEntity().getEquipment().setHelmet(new ItemStack(Material.CARVED_PUMPKIN));
	}

}

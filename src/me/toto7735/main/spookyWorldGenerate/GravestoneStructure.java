package me.toto7735.main.spookyWorldGenerate;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import me.toto7735.main.SpookyWorld;

public class GravestoneStructure implements Listener {

	public GravestoneStructure() {
		Bukkit.getPluginManager().registerEvents(this, SpookyWorld.getInstance());
	}
	
	
	@EventHandler
	public void onLoad(ChunkLoadEvent e) {
		if(!e.isNewChunk()) return;
		Random r = new Random(e.getWorld().getSeed());
		if(r.nextInt(125)!=0) return;
		
		spawnStructure(e.getChunk().getBlock(6, 6, 6).getLocation());
		
	}
	
	private void spawnStructure(Location loc) {
		
		Location l = getHighestDirtBlock(loc.clone()).getLocation();
		//center
		l.getBlock().setType(Material.PODZOL);
		l.subtract(0, 1, 0).getBlock().setType(Material.SPAWNER);
		CreatureSpawner spawner = (CreatureSpawner) l.getBlock().getState();
		spawner.setSpawnedType(EntityType.SKELETON);
		
		
		l = loc.clone().add(1,0,1);
		l = getHighestDirtBlock(l).getLocation();
		//top right
		l.getBlock().setType(Material.PODZOL);
		l.add(0,1,0).getBlock().setType(Material.COBBLESTONE_STAIRS);
		((Stairs)l.getBlock().getState()).setFacing(loc.clone().add(1,1,0).getBlock().getFace(l.getBlock()));
		l.add(0,1,0).getBlock().setType(Material.MOSSY_COBBLESTONE_WALL);
		
		l = loc.clone().add(-1,0,1);
		l = getHighestDirtBlock(l).getLocation();
		//low right
		l.getBlock().setType(Material.PODZOL);
		
		
		
		l = loc.clone().add(-1,0,-1);
		l = getHighestDirtBlock(l).getLocation();
		//low left
		l.getBlock().setType(Material.PODZOL);
		l.add(0,1,0).getBlock().setType(Material.COBBLESTONE_SLAB);
		
		
		l = loc.clone().add(1,0,-1);
		l = getHighestDirtBlock(l).getLocation();
		//top left
		l.getBlock().setType(Material.PODZOL);
		l.add(0,2,0).getBlock().setType(Material.COBBLESTONE_WALL);

		
		l = loc.clone().add(1,0,0);
		l = getHighestDirtBlock(l).getLocation();
		//top
		l.getBlock().setType(Material.PODZOL);
		l.add(0,1,0).getBlock().setType(Material.COBBLESTONE);
		l.add(0,1,0).getBlock().setType(Material.COBBLESTONE);
		
		
		l = loc.clone().add(-1,0,0);
		l = getHighestDirtBlock(l).getLocation();
		//low
		l.getBlock().setType(Material.PODZOL);
		
		
		l = loc.clone().add(0,0,-1);
		l = getHighestDirtBlock(l).getLocation();
		//left
		l.getBlock().setType(Material.PODZOL);
		
		
		
		
		l = loc.clone().add(0,0,1);
		l = getHighestDirtBlock(l).getLocation();
		//right
		l.getBlock().setType(Material.PODZOL);
		l.add(0,1,0).getBlock().setType(Material.COBBLESTONE_SLAB);
		
	}

	public static Block getHighestDirtBlock(Location loc) {
		loc.setY(256.0);
		for(Location l = loc; l.getBlockY() >= 0; l.subtract(0,1,0)) {
			Material mat = l.getBlock().getType();
			if(mat == null) continue;
			if(mat == Material.DIRT || mat == Material.GRASS_BLOCK || mat == Material.PODZOL || mat == Material.COARSE_DIRT) {
				return l.getBlock();
			}
			
		}
		
		return null;
		
	}
	
	
}

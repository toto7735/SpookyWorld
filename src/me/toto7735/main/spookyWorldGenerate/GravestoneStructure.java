package spookyworld;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.TileState;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.persistence.PersistentDataType;
import me.toto7735.main.SpookyWorld;

public class GravestoneStructure implements Listener {
	
	
	@EventHandler
	public void onLoad(ChunkLoadEvent e) {
		if(!e.isNewChunk()) return;
		Random r = new Random(e.getWorld().getSeed());
		if(r.nextInt(125)!=0) return;
		
		spawnStructure(e.getChunk().getBlock(6, 6, 6).getLocation());
		
		
	}
	
	private void spawnStructure(Location loc) {
		
		Location l = loc.getWorld().getHighestBlockAt(loc.clone()).getLocation();
		//center
		l.getBlock().setType(Material.PODZOL);
		l.subtract(0,1,0).getBlock().setType(Material.CHEST);
		TileState state = (TileState) l.getBlock().getState();
		state.getPersistentDataContainer().set(new NamespacedKey(SpookyWorld.getInstance(), "chesttype" )
				, PersistentDataType.STRING, "gravestone");
		state.getPersistentDataContainer().set(new NamespacedKey(SpookyWorld.getInstance(), "generated" )
				, PersistentDataType.STRING, "false");
		
		
		l = loc.clone().add(1,0,1);
		l = loc.getWorld().getHighestBlockAt(l).getLocation();
		//top right
		l.getBlock().setType(Material.PODZOL);
		l.add(0,1,0).getBlock().setType(Material.COBBLESTONE_STAIRS);
		((Stairs)l.getBlock().getState()).setFacing(loc.clone().add(1,1,0).getBlock().getFace(l.getBlock()));
		l.add(0,1,0).getBlock().setType(Material.MOSSY_COBBLESTONE_WALL);
		
		l = loc.clone().add(-1,0,1);
		l = loc.getWorld().getHighestBlockAt(l).getLocation();
		//low right
		l.getBlock().setType(Material.PODZOL);
		
		
		
		l = loc.clone().add(-1,0,-1);
		l = loc.getWorld().getHighestBlockAt(l).getLocation();
		//low left
		l.getBlock().setType(Material.PODZOL);
		l.add(0,1,0).getBlock().setType(Material.COBBLESTONE_SLAB);
		
		
		l = loc.clone().add(1,0,-1);
		l = loc.getWorld().getHighestBlockAt(l).getLocation();
		//top left
		l.getBlock().setType(Material.PODZOL);
		l.add(0,2,0).getBlock().setType(Material.COBBLESTONE_WALL);

		
		l = loc.clone().add(1,0,0);
		l = loc.getWorld().getHighestBlockAt(l).getLocation();
		//top
		l.getBlock().setType(Material.PODZOL);
		l.add(0,1,0).getBlock().setType(Material.COBBLESTONE);
		l.add(0,1,0).getBlock().setType(Material.COBBLESTONE);
		
		
		l = loc.clone().add(-1,0,0);
		l = loc.getWorld().getHighestBlockAt(l).getLocation();
		//low
		l.getBlock().setType(Material.PODZOL);
		
		
		l = loc.clone().add(0,0,-1);
		l = loc.getWorld().getHighestBlockAt(l).getLocation();
		//left
		l.getBlock().setType(Material.PODZOL);
		
		
		
		
		l = loc.clone().add(0,0,1);
		l = loc.getWorld().getHighestBlockAt(l).getLocation();
		//right
		l.getBlock().setType(Material.PODZOL);
		l.add(0,1,0).getBlock().setType(Material.COBBLESTONE_SLAB);
		
	}
	
	@EventHandler
	public void onOpen(PlayerInteractEvent e) {
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		if(e.getClickedBlock() == null)return;
		if(e.getClickedBlock().getType() != Material.CHEST) return;
		if(e.getClickedBlock().getState() == null)return;
		
		
		
		TileState state = (TileState) e.getClickedBlock().getState();
		if(!state.getPersistentDataContainer().has(new NamespacedKey(SpookyWorld.getInstance(), "chesttype" )
				, PersistentDataType.STRING)) return;
		if(!state.getPersistentDataContainer().get(new NamespacedKey(SpookyWorld.getInstance(), "chesttype" )
				, PersistentDataType.STRING).equalsIgnoreCase("gravestone")) return;
		if(state.getPersistentDataContainer().get(new NamespacedKey(SpookyWorld.getInstance(), "generated" )
				, PersistentDataType.STRING).equalsIgnoreCase("true")) return;
		
		//add loot
		
		state.getPersistentDataContainer().set(new NamespacedKey(SpookyWorld.getInstance(), "generated" )
				, PersistentDataType.STRING, "true");
		
		
		
	}
	
	
}

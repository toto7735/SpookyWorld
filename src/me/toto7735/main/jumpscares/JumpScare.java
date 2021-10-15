package me.toto7735.jumpscares;

import java.util.Objects;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.network.protocol.game.PacketPlayOutEntity;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.network.protocol.game.PacketPlayOutEntityHeadRotation;
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;
import me.toto7735.main.SpookyWorld;

public class JumpScare {
	
	public static void run(JumpscareType type) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			Objects.requireNonNull(type);
			if(type == JumpscareType.HEROBRINE) {
				PlayerConnection c = ((CraftPlayer)p).getHandle().b;
				
				MinecraftServer server = ((CraftServer)Bukkit.getServer()).getServer();
				WorldServer world = ((CraftWorld)p.getWorld()).getHandle();
				GameProfile skin = new GameProfile(UUID.fromString("4f6fbd1e-1c77-418c-9e78-7c94e37ed710"), "skinef82c478");
				skin.getProperties().put("textures", new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTYyMjMwNDY0MjU3MSwKICAicHJvZmlsZUlkIiA6ICI0NWY3YTJlNjE3ODE0YjJjODAwODM5MmRmN2IzNWY0ZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJfSnVzdERvSXQiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTUyM2UxZjA2ZDhlMTM5OWMwNjhhYTU3NjhmMDUzMDI1N2FhYjIxYTM0ZTk1NTY5MzNlNjM0NTUxODY0MjVhNiIKICAgIH0KICB9Cn0=", "PAL4UKYIgCe3RwmxfT6p05DDqc0kW2HTvlNNRuP3npXyuL68Hifjtiuu6OiJ/IQ/YGsjKf3Ni219teFFBJqEUJNEskC7CWnJxHWRq/WkSNDJXVKvBW6JjOYN607HSlBhifn4ZAxasol0bqVRURVG+0BwDUHXIGy4LRSu10/SLhsAmvwKhwYJiQ1xgnerqfzeWmPQSDZOo30Fxo6jNRnJ7v0VBGR8awImOfVsKE3SuEeys47U5AqXrHIp88s36aGOsIa0Bs6NHutW/L6Wbg9BHGUs93NGnjP9jxXizhGITF45KBXNl7gXPPjMnK7/CGOTuxuABc+MVAptB4cHCksnfaSMexryluQuwzWzOJTX1NCMGlYFREDq4c3GgVWJ7Yo2xWOwcy+XZxJXNchQ5/vzA3dv+XdU4zcD93OOhuv9PDoUTlmQgLFQLFbxM6Bq6cSQg3Wybi7wO6jQzBIVpZaswrOo/wgHuXbDkA9rv5uONjQQbKHJQjVjgIrbbUpX3+Kh4MkgBEUztxNvbgbt5RK0knwtYhscKDyrP5AAwp9CX6JnmbKXIRVWtx6b3JNPYC9I6FtBCoGxo6OfHl3f/okncszIh+40Sif3IdMrW+H5g3MxMkn5eHP4nOHRL24izlLUM52gS/TUgpG0dJrG6XMOR3w6KqTxcT/4vyxwlmJ+iDg="));				
				
				EntityPlayer ep = new EntityPlayer(server, world, skin);
				
				c.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, ep));
				c.sendPacket(new PacketPlayOutNamedEntitySpawn(ep));
				c.sendPacket(new PacketPlayOutEntityHeadRotation(ep, (byte) (ep.getBukkitYaw() * 256/360)));
				
				Location l = p.getLocation().add(p.getLocation().getDirection().normalize().multiply(10).setY(0));
				ep.setPosition(l.getX(), l.getY(), l.getZ());
				
				Bukkit.getScheduler().runTaskLater(SpookyWorld.getInstance(), new Runnable() {
					
					@Override
					public void run() {
						Location loc = p.getLocation().add(p.getLocation().getDirection().normalize().multiply(5).setY(0));
						ep.setPosition(loc.getX(), loc.getY(), loc.getZ());
						l.setDirection(loc.getDirection().multiply(-1));
						
						c.sendPacket(new PacketPlayOutEntityHeadRotation(ep,(byte) (l.getYaw()*256/360)));
						c.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(ep.getId(),
								(byte) (l.getYaw()*256/360),(byte) (l.getPitch()*256/360), true));
					}
				}
				, 5);
				
				Bukkit.getScheduler().runTaskLater(SpookyWorld.getInstance(), new Runnable() {
					
					@Override
					public void run() {
						Location loc = p.getLocation().add(p.getLocation().getDirection().normalize().multiply(1).setY(0));
						ep.setPosition(loc.getX(), loc.getY(), loc.getZ());
						l.setDirection(loc.getDirection().multiply(-1));
						
						c.sendPacket(new PacketPlayOutEntityHeadRotation(ep,(byte) (l.getYaw()*256/360)));
						c.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(ep.getId(),
								(byte) (l.getYaw()*256/360),(byte) (l.getPitch()*256/360), true));
					}
				}
				, 10);
				
				Bukkit.getScheduler().runTaskLater(SpookyWorld.getInstance(), new Runnable() {
					
					@Override
					public void run() {
						c.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.b, ep));
						c.sendPacket(new PacketPlayOutEntityDestroy(ep.getId()));
					}
				}
				, 100);
				
				l.setDirection(l.getDirection().multiply(1));
				c.sendPacket(new PacketPlayOutEntityHeadRotation(ep,(byte) (l.getYaw()*256/360)));
				c.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(ep.getId(),
						(byte) (l.getYaw()*256/360),(byte) (l.getPitch()*256/360), true));
				
				
			} else if(type == JumpscareType.ELDER_GUARDIAN) {
				p.spawnParticle(Particle.MOB_APPEARANCE, p.getLocation(), 1);
				
			} else if(type == JumpscareType.LIGHTNING) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20*10, 0));
				p.getWorld().strikeLightningEffect(p.getLocation().add(p.getLocation().getDirection().setY(0).normalize()));
				
			}
		}
		
		
		
	}
	
	
	public enum JumpscareType {
		
		HEROBRINE,
		LIGHTNING,
		ELDER_GUARDIAN;
		
		
		
	}

}

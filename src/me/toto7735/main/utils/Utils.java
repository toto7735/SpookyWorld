package me.toto7735.main.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.core.BlockPosition;
import net.minecraft.world.level.block.entity.TileEntitySkull;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

public class Utils {

    // toto7735 got this utils from the plugin of toto7735's server's util

    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception var2) {
            return false;
        }
    }

    public static boolean isLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (Exception var2) {
            return false;
        }
    }

    public static SkullMeta setSkullByValue(String value) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), (String)null);
        profile.getProperties().put("textures", new Property("textures", value));

        try {
            Method mtd = skullMeta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
            mtd.setAccessible(true);
            mtd.invoke(skullMeta, profile);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException var5) {
            var5.printStackTrace();
        }

        return skullMeta;
    }

    public static void setSkullBlockByValue(String value, Block block) {
        block.setType(Material.PLAYER_HEAD);
        Skull skullData = (Skull) block.getState();
        skullData.setType(Material.PLAYER_HEAD);

        TileEntitySkull skullTile = (TileEntitySkull) ((CraftWorld)block.getWorld()).getHandle().getTileEntity(new BlockPosition(block.getX(), block.getY(), block.getZ()));
        GameProfile profile = new GameProfile(UUID.randomUUID(), (String)null);
        profile.getProperties().put("textures", new Property("textures", value));
        skullTile.setGameProfile(profile);
        block.getState().update(true);
    }

    public static void giveItem(Player player, ItemStack itemStack) {
        if (isInventoryFull(player)) player.getWorld().dropItem(player.getLocation(), itemStack);
        else player.getInventory().addItem(itemStack);
    }

    public static boolean isInventoryFull(Player p) {
        return p.getInventory().firstEmpty() == -1;
    }

    public static void consumeItemInHand(Player player, int count) {
        if (player.getInventory().getItemInMainHand().getAmount() >= count) {
            player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - count);
        } else {
            player.setItemInHand(new ItemStack(Material.AIR));
        }
    }

}

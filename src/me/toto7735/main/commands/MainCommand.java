package me.toto7735.main.commands;

import me.toto7735.main.SpookyWorld;
import me.toto7735.main.utils.Utils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {

    public MainCommand() {
        SpookyWorld.getInstance().getCommand("spookyworld").setExecutor(this); // todo add some checks (ex: args == 0) toto7735 will do it
        SpookyWorld.getInstance().getCommand("spookyworld").setTabCompleter(this); // todo add tab completer toto7735 will do it
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("spookyworld")) {
            Player player = (Player) commandSender;
            if (strings[0].equalsIgnoreCase("candy")) {
                ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta skullMeta = Utils.setSkullByValue("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM2MWE3ODQ3YjhjMWQwOGRlMDQzOGQ1OGE1YTMyNDc1ZDYxNzI4YTNkNjUxNDk4ZmU4NjdiZDVkNTk4In19fQ==");
                skullMeta.setDisplayName("§cS§6p§eo§ao§bk§9y §dC§5a§3n§2d§1y");
                skullMeta.setLore(Arrays.asList("§7A sweet candy.", "§eHold this and Right-Click on TREAT OR TRICKS ZOMBIES to defeat it!"));
                itemStack.setItemMeta(skullMeta);
                Utils.giveItem(player, itemStack);
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }

}

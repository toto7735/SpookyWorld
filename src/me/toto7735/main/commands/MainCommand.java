package me.toto7735.main.commands;

import me.toto7735.main.SpookyWorld;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {

    public MainCommand() {
        SpookyWorld.getInstance().getCommand("spookyworld").setExecutor(this);
        SpookyWorld.getInstance().getCommand("spookyworld").setTabCompleter(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }

}

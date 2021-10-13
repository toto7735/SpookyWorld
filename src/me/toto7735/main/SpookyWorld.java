package me.toto7735.main;

import me.toto7735.main.commands.MainCommand;
import me.toto7735.main.spookyWorldGenerate.CustomChunkGenerator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class SpookyWorld extends JavaPlugin {

    // todo add some minigames, custom chunk generate, etc;
    private static SpookyWorld instance;

    public static SpookyWorld getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        new MainCommand();
    }

    public void onDisable() {

    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new CustomChunkGenerator(); // todo add spooky custom chunk; ImTheLion will do it
    }

}
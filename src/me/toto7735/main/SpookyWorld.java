package me.toto7735.main;

import me.toto7735.main.commands.MainCommand;
import me.toto7735.main.events.EventListener;
import me.toto7735.main.events.EventScheduler;
import me.toto7735.main.packetListener.PacketListener;
import me.toto7735.main.spookyWorldGenerate.CustomChunkGenerator;
import me.toto7735.main.spookyWorldGenerate.GravestoneStructure;
import me.toto7735.main.listeners.SpawnListener;
import me.toto7735.main.jumpscares.ScareHandler;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class SpookyWorld extends JavaPlugin {

    // todo add some minigames, custom chunk generate, etc;
    private static SpookyWorld instance;

    public static SpookyWorld getInstance() {
        return instance;
    }
    
    @Override
    public void onEnable() {
        instance = this;
        new MainCommand();
        
        //events
        new GravestoneStructure();
        new PacketListener();
        new EventScheduler();
        new EventListener();
        new SpawnListener();
        new ScareHandler();
    }
    
    @Override
    public void onDisable() {

    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new CustomChunkGenerator(); // todo add spooky custom chunk; ImTheLion will do it
    }

}

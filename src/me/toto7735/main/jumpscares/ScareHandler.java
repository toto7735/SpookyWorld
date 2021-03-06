package me.toto7735.main.jumpscares;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import me.toto7735.main.SpookyWorld;
import me.toto7735.main.jumpscares.JumpScare.JumpscareType;

public class ScareHandler {
	
	public ScareHandler() {
		run();
	}
	
	
	private void run() {
		Random r = new Random();
        new BukkitRunnable() {
            public void run() {
                if (Bukkit.getOnlinePlayers().isEmpty()) return;
                JumpscareType t = JumpscareType.values()[r.nextInt(JumpscareType.values().length)];
                JumpScare.run(t);
                
            }
        }.runTaskTimer(SpookyWorld.getInstance(), 1000, 12000);
    }
	
	
	

}

package me.toto7735.main.events;

import me.toto7735.main.SpookyWorld;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class EventScheduler {

    public EventScheduler() {
        this.run();
    }

    private void run() {
        new BukkitRunnable() {
            public void run() {
                if (Bukkit.getOnlinePlayers().isEmpty() || Event.isEventRunning()) return;
                boolean major = new Random().nextInt(5) == 0;
                if (!major) {
                    EventType eventType = null;
                    do {
                        eventType = EventType.values()[new Random().nextInt(EventType.values().length)];
                    } while (eventType.isMajor);
                    new MinorEvent(eventType).fire();
                }
            }
        }.runTaskTimer(SpookyWorld.getInstance(), 0, 6000); // We can't use Asynchronously because of entity add; runs every 5 minutes; by toto7735
    }

}

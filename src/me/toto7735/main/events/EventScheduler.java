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
                boolean major = new Random().nextInt(5) == 0;
                if (!major) {
                    EventType eventType = null;
                    do {
                        eventType = EventType.values()[new Random().nextInt(EventType.values().length)];
                        System.out.println(eventType.isMajor);
                        System.out.println(Event.isEventRunning(eventType));
                    } while (eventType.isMajor || Event.isEventRunning(eventType));
                    new MinorEvent(eventType).fire();
                }
            }
        }.runTaskTimer(SpookyWorld.getInstance(), 0, 100); // We can't use Asynchronously because of entity add; by toto7735
    }

}

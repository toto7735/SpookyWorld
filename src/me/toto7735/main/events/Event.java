package me.toto7735.main.events;

import java.util.ArrayList;
import java.util.List;

public class Event {

    private static List<EventType> runningEvents = new ArrayList<>();

    public static boolean isEventRunning(EventType eventType) {
        return runningEvents.contains(eventType);
    }

    public static void fireEvent(EventType eventType) {
        runningEvents.add(eventType);
    }

    public static void stopEvent(EventType eventType) {
        runningEvents.remove(eventType);
    }

}

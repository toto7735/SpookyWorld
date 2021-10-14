package me.toto7735.main.events;

public class Event {

    private static boolean isRunning;

    public static boolean isEventRunning() {
        return isRunning;
    }

    public static void fireEvent() {
        isRunning = true;
    }

    public static void stopEvent() {
        isRunning = false;
    }

}

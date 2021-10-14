package me.toto7735.main.events;

public enum EventType {


    SCARY_ZOMBIES(false);

    boolean isMajor;

    EventType(boolean isMajor) {
        this.isMajor = isMajor;
    }

}

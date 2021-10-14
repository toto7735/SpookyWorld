package me.toto7735.main.events;

public enum EventType {


    SCARY_ZOMBIES(false),
	GHOSTLY_CREEPERS(false);

    boolean isMajor;

    EventType(boolean isMajor) {
        this.isMajor = isMajor;
    }

}

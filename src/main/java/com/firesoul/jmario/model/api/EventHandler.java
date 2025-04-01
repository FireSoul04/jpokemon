package com.firesoul.jmario.model.api;

public interface EventHandler {

    void addEvent(String name, Event e);

    boolean getEvent(String name);
}

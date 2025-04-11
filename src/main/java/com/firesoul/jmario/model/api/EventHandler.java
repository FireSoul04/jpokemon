package com.firesoul.jmario.model.api;

public interface EventHandler {

    /**
     * Add an event to this handler and link that to a name.
     * @param name
     * @param e
     */
    void addEvent(String name, Event e);

    /**
     * @param name
     * @return if event attached with name happens
     */
    boolean getEvent(String name);
}

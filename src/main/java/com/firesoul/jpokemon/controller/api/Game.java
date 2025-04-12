package com.firesoul.jpokemon.controller.api;

import com.firesoul.jpokemon.model.api.Room;

public interface Game {

    /**
     * Main loop function.
     */
    void run();

    /**
     * This is called every game logic frame.
     * @param dt time passed between this frame and the last one
     */
    void update(double dt);

    /**
     * What to render every 60 frame
     */
    void render();

    /**
     * @return the current room in the game
     */
    Room getCurrentRoom();

    /**
     * @return if the game state is running
     */
    boolean isRunning();

    /**
     * @return if the game state is on menu
     */
    boolean isOnMenu();

    /**
     * @return if the game state is on over
     */
    boolean isOver();
}

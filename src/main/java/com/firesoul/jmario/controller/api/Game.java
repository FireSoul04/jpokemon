package com.firesoul.jmario.controller.api;

public interface Game {

    void run();

    void update(double dt);

    void render();

    boolean isRunning();

    boolean isOnMenu();

    boolean isOver();
}

package com.firesoul.jpokemon.model.api;

import java.util.List;

public interface Room {

    static final double TILE_SIZE = 20.0;

    /**
     * Update all game objects in this room.
     * @param dt
     */
    void update(double dt);

    /**
     * @param g to add
     * @return if that game object is added
     */
    boolean addGameObject(GameObject gameObject);

    /**
     * @param g to remove
     * @return if that game object is removed
     */
    boolean removeGameObject(GameObject gameObject);

    /**
     * @return game objects inside the room
     */
    List<GameObject> getGameObjects();

    /**
     * @return player
     */
    GameObject getPlayer();
}

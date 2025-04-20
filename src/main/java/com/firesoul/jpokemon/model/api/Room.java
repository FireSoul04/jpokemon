package com.firesoul.jpokemon.model.api;

import java.util.List;
import java.util.Optional;

public interface Room {

    static final double TILE_SIZE = 20.0;

    public record Grid(int x, int y) {
    }

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
     * Search for a game object inside grid. 
     * @param grid
     * @return If present, an optional with it inside, otherwise an empty optional
     */
    Optional<GameObject> findGameObject(Grid grid);

    /**
     * @return game objects inside the room
     */
    List<GameObject> getGameObjects();

    /**
     * @return player
     */
    GameObject getPlayer();
}

package com.firesoul.jpokemon.model.api;

import java.util.function.Supplier;

import com.firesoul.jpokemon.model.impl.Vector2;

public interface GameObjectFactory {

    /**
     * Base of all game objects.
     * @param position
     * @param speed
     * @param movingCriteria that represent how the entity moves, giving delta time as parameter and returning the next position where to go
     * @return a game object that can move based on movingCriteria
     */
    GameObject movingGameObject(Vector2 position, double speed, Supplier<Vector2> movingCriteria);

    /**
     * Game object with static movement and no speed.
     * @param position
     * @return a game object that doesn't move
     */
    GameObject staticGameObject(Vector2 position);
    
    /**
     * Game object with dynamic movement and speed.
     * @param position
     * @param speed
     * @return a game object that can move
     */
    GameObject dynamicGameObject(Vector2 position, double speed);

    /**
     * Game object representing the player.
     * @param position
     * @param speed
     * @param keys
     * @return the player
     */
    GameObject player(Vector2 position, double speed, EventHandler keys);
}

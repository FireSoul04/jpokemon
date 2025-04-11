package com.firesoul.jmario.model.api;

import java.util.function.Function;

import com.firesoul.jmario.model.impl.Vector2;

public interface GameObjectFactory {

    /**
     * Base of all game objects.
     * @param position
     * @param speed
     * @param movingCriteria that represent how the entity moves, giving delta time as parameter and returning the next position where to go
     * @return a game object that can move based on movingCriteria
     */
    GameObject movingGameObject(Vector2 position, Vector2 speed, Function<Double, Vector2> movingCriteria);

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
    GameObject dynamicGameObject(Vector2 position, Vector2 speed);

    /**
     * Game object representing the player.
     * @param position
     * @param speed
     * @param keys
     * @return the player
     */
    GameObject player(Vector2 position, Vector2 speed, EventHandler keys);
}

package com.firesoul.jpokemon.model.api;

import com.firesoul.jpokemon.model.impl.Vector2;

public interface GameObject {
    
    /**
     * This determine what the game object does every frame based on delta time.
     * @param dt the difference between this frame and the last one
     */
    void update(double dt);

    /**
     * @return current position of this game object
     */
    Vector2 getPosition();

    /**
     * @return current speed of this game object
     */
    double getSpeed();

    /**
     * @return collider attached to this game object
     */
    Collider getCollider();

    void pushBack(double dt, Vector2 direction);
}

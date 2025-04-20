package com.firesoul.jpokemon.model.api;

import com.firesoul.jpokemon.model.impl.Vector2;

public interface Collider {

    /**
     * @param other collider colliding with
     * @return is this and other are colliding
     */
    boolean isCollidingWith(Collider other);

    /**
     * @return the position of the collider
     */
    Vector2 getPosition();

    /**
     * @return the size of the collider
     */
    Vector2 getSize();

    /**
     * @return the width of the collider
     */
    double getWidth();

    /**
     * @return the height of the collider
     */
    double getHeight();
}
